package com.sut.rozklad.feature_academic_group.domain.service.impl

import com.sut.rozklad.feature_academic_group.domain.model.Course
import com.sut.rozklad.feature_academic_group.domain.model.Faculty
import com.sut.rozklad.feature_academic_group.domain.model.Group
import com.sut.rozklad.feature_academic_group.domain.model.Lesson
import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService
import org.jsoup.Connection
import org.jsoup.nodes.Node
import org.jsoup.select.Elements
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.function.Predicate
import javax.inject.Inject
import javax.inject.Named
import kotlin.streams.toList

class ERozkladServiceImpl @Inject constructor(
    @Named("groupJsoupClient")
    private val connection: Connection
) : ERozkladService {


    override suspend fun fetchFaculties(): List<Faculty> {
        val doc = connection.get()
        val faculties = doc.getElementById("timetableform-facultyid")

        return faculties?.childNodes()?.stream()
            ?.filter(optionValuePredicate())
            ?.map {
                val postOptionValue = it.attr("value")
                val name = it.firstChild()?.outerHtml() ?: "Faculty name absent"
                Faculty(name = name, postOptionValue = postOptionValue)
            }
            ?.toList() ?: Collections.emptyList()
    }

    override suspend fun fetchCSRFToken(): String {
        val doc = connection.get()
        val token = doc.getElementsByAttributeValue("name", CSRF_TOKEN_ID)

        return token.attr("value")
    }

    override suspend fun fetchCourses(faculty: Faculty): List<Course> {
        val doc = connection
            .data(CSRF_TOKEN_ID, fetchCSRFToken())
            .data(FACULTY_ID_POST_VALUE, faculty.postOptionValue)
            .post()
        val courses = doc.getElementById("timetableform-course")

        return courses?.childNodes()?.stream()
            ?.filter(optionValuePredicate())
            ?.map {
                val postOptionValue = it.attr("value")
                val value = it.firstChild()?.outerHtml() ?: "Unknown"
                Course(value = value, postOptionValue = postOptionValue)
            }
            ?.toList() ?: Collections.emptyList()
    }

    override suspend fun fetchGroups(faculty: Faculty, course: Course): List<Group> {
        val doc = connection
            .data(CSRF_TOKEN_ID, fetchCSRFToken())
            .data(FACULTY_ID_POST_VALUE, faculty.postOptionValue)
            .data(COURSE_ID_POST_VALUE, course.postOptionValue)
            .post()
        val groups = doc.getElementById("timetableform-groupid")

        return groups?.childNodes()?.stream()
            ?.filter(optionValuePredicate())
            ?.map {
                val postOptionValue = it.attr("value")
                val name = it.firstChild()?.outerHtml() ?: "Unknown"
                Group(name = name, postOptionValue = postOptionValue)
            }
            ?.toList() ?: Collections.emptyList()
    }

    override suspend fun fetchLessons(
        faculty: Faculty,
        course: Course,
        group: Group,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<Lesson> {
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val doc = connection
            .data(CSRF_TOKEN_ID, fetchCSRFToken())
            .data(FACULTY_ID_POST_VALUE, faculty.postOptionValue)
            .data(COURSE_ID_POST_VALUE, course.postOptionValue)
            .data(GROUP_ID_POST_VALUE, group.postOptionValue)
            .data(START_DATE_POST_VALUE, startDate.format(dateFormatter))
            .data(END_DATE_POST_VALUE, endDate.format(dateFormatter))
            .post()

        val lessonTimesElements = doc.getElementById("timeTable")
            ?.getElementsByAttributeValue("class", "headcol")
        val timeMap = fetchLessonTimes(lessonTimesElements)

        val timeTable = doc.getElementById("timeTable")
            ?.getElementsByAttributeValue("data-toggle", "popover")

        val lessons = timeTable?.stream()
            ?.map {
                val title = it.attr("title").split(" ")
                val date = LocalDate.from(dateFormatter.parse(title[0]))
                val lessonNumber = title[1].toInt()
                val startEndTime = timeMap[title[1] + " " + title[2]] ?: "No info"
                val content = it.attr("data-content").split("<br>")
                val lessonTitle = content[0]
                val classroom = content[1]
                val groupName = content[2]
                val teacher = content[3]
                val addedDate = content[4]
                Lesson(
                    title = lessonTitle,
                    classroom = classroom,
                    groupName = groupName,
                    teacher = teacher,
                    date = date,
                    startEndTime = startEndTime,
                    lessonNumber = lessonNumber,
                    addedDate = addedDate
                )
            }
            ?.toList() ?: Collections.emptyList()


        return lessons
    }

    private fun fetchLessonTimes(startEndTime: Elements?): Map<String?, String?> {
        val timeMap = startEndTime?.associateBy(
            { col ->
                col.children().stream()
                    .filter { it.attr("class") == "lesson" }
                    .findFirst()
                    .map { it.firstChild()?.outerHtml() }
                    .orElse("0 пара")
            },
            { col ->
                col.children().stream()
                    .filter {
                        it.attr("class") == "start"
                                || it.attr("class") == "end"
                    }
                    .map { it.firstChild()?.outerHtml() }
                    .reduce { startTime, endTime -> "$startTime - $endTime" }
                    .orElse("No info")
            }
        ) ?: Collections.emptyMap()
        return timeMap
    }

    private fun optionValuePredicate(): Predicate<Node> {
        return Predicate {
            it.hasAttr("value") && it.attr("value").isNotBlank()
        }
    }

    companion object {
        const val CSRF_TOKEN_ID = "_csrf-frontend"
        const val FACULTY_ID_POST_VALUE = "TimeTableForm[facultyId]"
        const val COURSE_ID_POST_VALUE = "TimeTableForm[course]"
        const val GROUP_ID_POST_VALUE = "TimeTableForm[groupId]"
        const val START_DATE_POST_VALUE = "TimeTableForm[dateStart]"
        const val END_DATE_POST_VALUE = "TimeTableForm[dateEnd]"
    }


}