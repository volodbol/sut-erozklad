package com.sut.rozklad.feature_academic_group.domain.use_case

import com.sut.rozklad.feature_academic_group.domain.model.Course
import com.sut.rozklad.feature_academic_group.domain.model.Faculty
import com.sut.rozklad.feature_academic_group.domain.model.Group
import com.sut.rozklad.feature_academic_group.domain.model.Lesson
import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService

class GetLessons(
    private val eRozkladService: ERozkladService
) {

    suspend operator fun invoke(faculty: Faculty, course: Course, groups: Group): List<Lesson> {
        return eRozkladService.fetchLessons(
            faculty = faculty,
            course = course,
            group = groups
        )
    }

}