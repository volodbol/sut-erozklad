package com.sut.rozklad.feature_academic_group.domain.model

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateTypeConverter {

    @TypeConverter
    fun toLocalDate(value: Long): LocalDate {
        return LocalDate.ofEpochDay(value)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate): Long {
        return date.toEpochDay()
    }

}