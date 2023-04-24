package com.example.remindersapp

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
class ViewModelModule {

    @Provides
    @ActivityScoped
    fun provideReminderViewModelFactory(application: Application,
                                        reminderRepository: ReminderRepository): ReminderViewModelFactory {
        return ReminderViewModelFactory(application, reminderRepository)
    }
}