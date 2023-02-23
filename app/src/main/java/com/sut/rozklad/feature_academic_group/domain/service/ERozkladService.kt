package com.sut.rozklad.feature_academic_group.domain.service

import com.sut.rozklad.feature_academic_group.domain.model.Course
import com.sut.rozklad.feature_academic_group.domain.model.Faculty
import com.sut.rozklad.feature_academic_group.domain.model.Group
import com.sut.rozklad.feature_academic_group.domain.model.Lesson
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

interface ERozkladService {

    suspend fun fetchFaculties(): List<Faculty>

    suspend fun fetchCSRFToken(): String

    suspend fun fetchCourses(faculty: Faculty): List<Course>

    suspend fun fetchGroups(faculty: Faculty, course: Course): List<Group>

    suspend fun fetchLessons(
        faculty: Faculty,
        course: Course,
        group: Group,
        startDate: LocalDate = LocalDate.now()
            .with(
                TemporalAdjusters.previousOrSame(
                    WeekFields.of(Locale.getDefault()).firstDayOfWeek
                )
            ),
        endDate: LocalDate = startDate.plusDays(20)
    ): List<Lesson>

    companion object {
        const val ACADEMIC_GROUP_SCHEDULE_URL = "http://e-rozklad.dut.edu.ua/time-table/group"
    }

}