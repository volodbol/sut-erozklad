package com.sut.rozklad.feature_academic_group.domain.use_case

import com.sut.rozklad.feature_academic_group.domain.model.Course
import com.sut.rozklad.feature_academic_group.domain.model.Faculty
import com.sut.rozklad.feature_academic_group.domain.model.Group
import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService

class GetGroups(
    private val eERozkladService: ERozkladService
) {

    suspend operator fun invoke(faculty: Faculty, course: Course): List<Group> {
        return eERozkladService.fetchGroups(faculty, course)
    }

}