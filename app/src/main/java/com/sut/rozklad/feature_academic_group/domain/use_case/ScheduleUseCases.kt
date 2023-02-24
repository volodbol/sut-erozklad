package com.sut.rozklad.feature_academic_group.domain.use_case

data class ScheduleUseCases(
    val getFaculties: GetFaculties,
    val getCourses: GetCourses,
    val getGroups: GetGroups,
    val getLessons: GetLessons
)