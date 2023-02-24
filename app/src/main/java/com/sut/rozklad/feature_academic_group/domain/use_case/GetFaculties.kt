package com.sut.rozklad.feature_academic_group.domain.use_case

import com.sut.rozklad.feature_academic_group.domain.model.Faculty
import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFaculties(
    private val eRozkladService: ERozkladService,
) {

    operator fun invoke(): Flow<List<Faculty>> {
        return flow {
            val faculties = eRozkladService.fetchFaculties()
            emit(faculties)
        }
    }

}