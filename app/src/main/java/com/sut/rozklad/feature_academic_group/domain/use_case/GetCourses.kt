package com.sut.rozklad.feature_academic_group.domain.use_case

import com.sut.rozklad.feature_academic_group.domain.model.Course
import com.sut.rozklad.feature_academic_group.domain.model.Faculty
import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService

class GetCourses(
    private val eRozkladService: ERozkladService
) {

    suspend operator fun invoke(faculty: Faculty): List<Course> {
        return eRozkladService.fetchCourses(faculty)
    }

}