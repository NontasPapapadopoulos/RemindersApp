package com.example.remindersapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi

import com.example.remindersapp.TimeUtil.Companion.parseTimeFromTimePicker
import com.example.remindersapp.databinding.FragmentUpdateReminderBinding
import java.util.*


class UpdateReminderFragment : Fragment() {

    private lateinit var binding: FragmentUpdateReminderBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateReminderBinding.inflate(inflater, container, false )

        val reminder = arguments?.getSerializable("reminder") as Reminder

        setTimePickerReminderTime(reminder)
        modifyTimePicker()

        setSpinnerAdapter(binding.reminderTypeSpinner, R.array.reminder_type)
        setSpinnerValue(reminder)

        updateReminder(reminder)


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

    private fun setSpinnerValue(reminder: Reminder) {
        if (reminder.type == "Water")
            binding.reminderTypeSpinner.setSelection(0)
        else
            binding.reminderTypeSpinner.setSelection(1)
    }



    private fun updateReminder(reminder: Reminder) {
        binding.addReminderButton.setOnClickListener {
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute
            val type = binding.reminderTypeSpinner.selectedItem.toString()

            val timeReminder = parseTimeFromTimePicker(hour, minute)
            reminder.type = type
            reminder.time = timeReminder


            (activity as MainActivity).reminderViewModel.update(reminder)

        }
    }

    private fun setTimePickerReminderTime(reminder: Reminder) {
        binding.timePicker.hour = reminder.time.split(":")[0].toInt()
        binding.timePicker.minute = reminder.time.split(":")[1].toInt()
    }

    private fun modifyTimePicker() {
        binding.timePicker.setIs24HourView(true)
    }

}