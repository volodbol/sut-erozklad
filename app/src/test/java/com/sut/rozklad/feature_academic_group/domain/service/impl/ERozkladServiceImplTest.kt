package com.sut.rozklad.feature_academic_group.domain.service.impl

import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ERozkladServiceImplTest {

    lateinit var eRozkladService: ERozkladService

    @BeforeEach
    fun setUpTests() {
        eRozkladService = ERozkladServiceImpl(
            Jsoup.connect(ERozkladService.ACADEMIC_GROUP_SCHEDULE_URL)
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testWhenFacultiesFetchedThenTheyMustBeReturned() {
        runTest {
            val fetchFaculties = eRozkladService.fetchFaculties()
            assertFalse(fetchFaculties.isEmpty())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testWhenTokenFetchedThenItMustBeReturned() {
        runTest {
            val csrfToken = eRozkladService.fetchCSRFToken()
            assertFalse(csrfToken.isBlank())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testWhenCoursesFetchedThenTheyMustBeReturned() {
        runTest {
            val faculty = eRozkladService.fetchFaculties()[0]
            val courses = eRozkladService.fetchCourses(faculty)
            assertFalse(courses.isEmpty())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testWhenGroupsFetchedThenTheyMustBeReturned() {
        runTest {
            val faculty = eRozkladService.fetchFaculties()[0]
            val course = eRozkladService.fetchCourses(faculty)[0]
            val groups = eRozkladService.fetchGroups(faculty, course)
            assertFalse(groups.isEmpty())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testWhenLessonsFetchedThenTheyMustBeReturned() {
        runTest {
            val faculty = eRozkladService.fetchFaculties()[0]
            val course = eRozkladService.fetchCourses(faculty)[0]
            val group = eRozkladService.fetchGroups(faculty, course)[0]
            val lessons = eRozkladService.fetchLessons(faculty, course, group)
            assertFalse(lessons.isEmpty())
        }
    }

}