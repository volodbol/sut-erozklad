package com.sut.rozklad.di

import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService
import com.sut.rozklad.feature_academic_group.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScheduleUseCasesModule {

    @Provides
    @Singleton
    fun provideUseCases(eRozkladService: ERozkladService): ScheduleUseCases {
        return ScheduleUseCases(
            getFaculties = GetFaculties(eRozkladService),
            getCourses = GetCourses(eRozkladService),
            getGroups = GetGroups(eRozkladService),
            getLessons = GetLessons(eRozkladService)
        )
    }

}