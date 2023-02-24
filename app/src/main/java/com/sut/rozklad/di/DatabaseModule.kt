package com.sut.rozklad.di

import android.app.Application
import androidx.room.Room
import com.sut.rozklad.feature_academic_group.data.data_source.ScheduleDatabase
import com.sut.rozklad.feature_academic_group.data.repository.LessonRepositoryImpl
import com.sut.rozklad.feature_academic_group.domain.repository.LessonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLessonDatabase(app: Application): ScheduleDatabase {
        return Room.databaseBuilder(
            app,
            ScheduleDatabase::class.java,
            ScheduleDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLessonRepository(db: ScheduleDatabase): LessonRepository {
        return LessonRepositoryImpl(db.lessonDao)
    }

}