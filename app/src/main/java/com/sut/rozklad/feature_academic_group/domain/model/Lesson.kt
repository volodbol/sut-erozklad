package com.sut.rozklad.feature_academic_group.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey val id: Long = -1,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "classroom") val classroom: String,
    @ColumnInfo(name = "group_name") val groupName: String,
    @ColumnInfo(name = "teacher") val teacher: String,
    @ColumnInfo(name = "date") val date: LocalDate,
    @ColumnInfo(name = "lesson_number") val lessonNumber: Int,
    @ColumnInfo(name = "start_end_time") val startEndTime: String,
    @ColumnInfo(name = "added_date") val addedDate: String
)
