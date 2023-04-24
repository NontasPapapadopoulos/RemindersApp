package com.example.remindersapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return Application() as Context
    }


    @Provides
    @Singleton
    fun providesReminderDataBase(app: Application): ReminderDatabase {
        return Room.databaseBuilder(app, ReminderDatabase::class.java, "reminder_database").build()
    }

    @Provides
    @Singleton
    fun provideReminderDao(reminderDatabase: ReminderDatabase): ReminderDAO {
        return reminderDatabase.getReminderDao()
    }

    @Provides
    @Singleton
    fun provideRepository(reminderDAO: ReminderDAO) : ReminderRepository {
        return ReminderRepository(reminderDAO)
    }

}