package com.example.remindersapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return Application() as Context
    }


    @Provides
    @Singleton
    fun providesReminderDataBase(context: Context): ReminderDatabase {
        return Room.databaseBuilder(context, ReminderDatabase::class.java, "reminder_database").build()
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