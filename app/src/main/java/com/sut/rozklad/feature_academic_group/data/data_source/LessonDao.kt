package com.sut.rozklad.feature_academic_group.data.data_source

import androidx.room.*
import com.sut.rozklad.feature_academic_group.domain.model.Lesson
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface LessonDao {

    @Query("SELECT * FROM lessons WHERE date BETWEEN :date1 AND :date2")
    fun getLessons(date1: LocalDate, date2: LocalDate): Flow<List<Lesson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(vararg lessons: Lesson)

    @Query("DELETE FROM lessons WHERE id > 0")
    suspend fun deleteAllLessons()

}