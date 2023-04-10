package com.example.remindersapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.example.remindersapp.TimeUtil.Companion.parseTimeFromTimePicker
import com.example.remindersapp.databinding.FragmentAddReminderBinding
import java.util.*


class AddReminderFragment : Fragment() {

    private lateinit var binding: FragmentAddReminderBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddReminderBinding.inflate(inflater, container, false)


        setTimePickerCurrentTime()
        modifyTimePicker()

        setSpinnerAdapter(binding.reminderTypeSpinner, R.array.reminder_type)
        showRemindersButton()
        attachListeners()
        return binding.root

    }


    private fun setSpinnerAdapter(spinner: Spinner, textArrayResId: Int) {
        ArrayAdapter.createFromResource(
            activity as MainActivity,
            textArrayResId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    private fun showRemindersButton() {
        (activity as MainActivity).reminderViewModel.allReminders.observe(
            activity as MainActivity, androidx.lifecycle.Observer{
               if (it.isEmpty())
                   binding.showRemindersButton.visibility = GONE
               else
                   binding.showRemindersButton.visibility = VISIBLE
            })
    }

    private fun attachListeners() {
        showRemindersFragment()
        addReminder()
    }

    private fun addReminder() {
        binding.addReminderButton.setOnClickListener {
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute
            val type = binding.reminderTypeSpinner.selectedItem.toString()

            val timeReminder = parseTimeFromTimePicker(hour, minute)
            val reminder = Reminder(timeReminder, type, true)

            (activity as MainActivity).reminderViewModel.insert(reminder)
            (activity as MainActivity).reminderViewModel.registerNotification(reminder)
        }
    }

    private fun setTimePickerCurrentTime() {
        binding.timePicker.hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        binding.timePicker.minute = Calendar.getInstance().get(Calendar.MINUTE)
    }

    private fun modifyTimePicker() {
        binding.timePicker.setIs24HourView(true)

    }


    private fun showRemindersFragment() {
        binding.showRemindersButton.setOnClickListener {

            val fragmentManager: FragmentManager = parentFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val fragment = RemindersFragment()

            fragmentTransaction.replace(R.id.frame, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}