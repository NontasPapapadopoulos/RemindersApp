package com.example.remindersapp

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelModule::class,
    RepositoryModule::class
])
interface AppComponent {
    fun appComponent(): AppComponent
}