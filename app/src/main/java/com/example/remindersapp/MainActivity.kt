package com.example.remindersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.remindersapp.databinding.ActivityMainBinding
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ReminderViewModelFactory
    lateinit var reminderViewModel: ReminderViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        reminderViewModel = ViewModelProvider(this, factory)[ReminderViewModel::class.java]
        displayAddReminderFragment()

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


