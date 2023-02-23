package com.sut.rozklad.feature_academic_group.domain.repository

import com.sut.rozklad.feature_academic_group.domain.model.Lesson
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface LessonRepository {

    fun getLessons(date1: LocalDate, date2: LocalDate): Flow<List<Lesson>>

    suspend fun insertLessons(vararg lessons: Lesson)

}