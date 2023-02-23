package com.sut.rozklad.feature_academic_group.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey val id: Long = -1,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "post_option_value") val postOptionValue: String
)
