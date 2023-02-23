package com.sut.rozklad.di

import android.app.Application
import androidx.room.Room
import com.sut.rozklad.feature_academic_group.data.data_source.ClassesDatabase
import com.sut.rozklad.feature_academic_group.data.repository.LessonRepositoryImpl
import com.sut.rozklad.feature_academic_group.domain.repository.LessonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideLessonDatabase(app: Application): ClassesDatabase {
        return Room.databaseBuilder(
            app,
            ClassesDatabase::class.java,
            ClassesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLessonRepository(db: ClassesDatabase): LessonRepository {
        return LessonRepositoryImpl(db.lessonDao)
    }

}