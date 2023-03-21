package com.example.remindersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.remindersapp.databinding.FragmentAddReminderBinding
import com.example.remindersapp.databinding.FragmentUpdateReminderBinding
import java.util.*


class UpdateReminderFragment : Fragment() {

    private lateinit var binding: FragmentUpdateReminderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateReminderBinding.inflate(inflater, container, false )

        setTimePickerCurrentTime()
        modifyTimePicker()

        setSpinnerAdapter(binding.reminderTypeSpinner, R.array.reminder_type)
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

    private fun attachListeners() {
        updateReminder()
    }

    private fun updateReminder() {
        binding.addReminderButton.setOnClickListener {
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute
            val type = binding.reminderTypeSpinner.selectedItem.toString()

            val timeReminder = TimeUtil.parseTimeFromTimePicker(hour, minute)
            val reminder = Reminder(timeReminder, type, true)

            (activity as MainActivity).reminderViewModel.update(reminder)

        }
    }

    private fun setTimePickerCurrentTime() {
        binding.timePicker.hour = Calendar.getInstance().get(Calendar.HOUR)
        binding.timePicker.minute = Calendar.getInstance().get(Calendar.MINUTE)
    }

    private fun modifyTimePicker() {
        binding.timePicker.setIs24HourView(true)
    }

}