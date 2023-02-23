package com.sut.rozklad.di

import com.sut.rozklad.feature_academic_group.domain.service.ERozkladService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.jsoup.Connection
import org.jsoup.Jsoup
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JsoupClientModule {

    @Provides
    @Singleton
    @Named("groupJsoupClient")
    fun provideGroupJsoupClient(): Connection {
        return Jsoup.connect(ERozkladService.ACADEMIC_GROUP_SCHEDULE_URL)
    }

}