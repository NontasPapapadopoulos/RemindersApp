package com.example.remindersapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesReminderDataBase(context: Context): RoomDatabase.Builder<ReminderDatabase> {
        return Room.databaseBuilder(context, ReminderDatabase::class.java, "reminder_database")
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