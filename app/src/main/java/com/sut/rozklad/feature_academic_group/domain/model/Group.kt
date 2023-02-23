package com.sut.rozklad.feature_academic_group.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class Group(
    @PrimaryKey val id: Long = -1,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "post_option_value") val postOptionValue: String
)
