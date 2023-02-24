package com.sut.rozklad.feature_academic_group.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sut.rozklad.feature_academic_group.domain.model.*

@Database(
    entities = [Lesson::class],
    version = 1
)
@TypeConverters(LocalDateTypeConverter::class)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract val lessonDao: LessonDao

    companion object {
        const val DATABASE_NAME = "schedule_db"
    }

}