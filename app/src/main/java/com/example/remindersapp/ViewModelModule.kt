package com.example.remindersapp

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideReminderViewModelFactory(application: Application,
                                 reminderRepository: ReminderRepository): ReminderViewModelFactory {
        return ReminderViewModelFactory(application, reminderRepository)
    }
}