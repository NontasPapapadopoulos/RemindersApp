package com.example.remindersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.remindersapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var reminderViewModel: ReminderViewModel
    lateinit var remindersList: ArrayList<Reminder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        reminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        setRemindersList()
    }

    private fun setRemindersList() {
        reminderViewModel.allReminders.observe(this,
            androidx.lifecycle.Observer { list ->
                list?.let {
                    remindersList = list as ArrayList<Reminder>
                    displayAddReminderFragment()

                }
            })
    }


    private fun displayAddReminderFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = AddReminderFragment()

        fragmentTransaction.add(R.id.frame, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }











}


