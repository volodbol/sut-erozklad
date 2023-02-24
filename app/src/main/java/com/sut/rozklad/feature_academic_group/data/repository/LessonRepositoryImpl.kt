package com.sut.rozklad.feature_academic_group.data.repository

import com.sut.rozklad.feature_academic_group.data.data_source.LessonDao
import com.sut.rozklad.feature_academic_group.domain.model.Lesson
import com.sut.rozklad.feature_academic_group.domain.repository.LessonRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class LessonRepositoryImpl(
    private val lessonDao: LessonDao
) : LessonRepository {

    override fun getLessons(date1: LocalDate, date2: LocalDate): Flow<List<Lesson>> {
        return lessonDao.getLessons(date1, date2)
    }

    override suspend fun insertLessons(vararg lessons: Lesson) {
        lessonDao.insertLessons(lessons = lessons)
    }

    override suspend fun deleteAllLessons() {
        lessonDao.deleteAllLessons()
    }

}