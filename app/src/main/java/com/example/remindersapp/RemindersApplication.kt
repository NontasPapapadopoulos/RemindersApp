package com.example.remindersapp

import android.app.Application

class RemindersApplication: Application() {
   lateinit var remindersComponent: RemindersComponent

    override fun onCreate() {
        remindersComponent = initDagger()
        super.onCreate()
    }

    private fun initDagger(): RemindersComponent =
        DaggerRemindersComponent.builder().build()
}