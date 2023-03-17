package com.example.remindersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.remindersapp.databinding.FragmentAddReminderBinding
import com.example.remindersapp.databinding.FragmentReminderBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

//@AndroidEntryPoint
class AddReminderFragment : Fragment() {

    private lateinit var binding: FragmentAddReminderBinding
   // val reminderViewModel: ReminderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddReminderBinding.inflate(inflater, container, false )



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
//        val reminders = reminderViewModel.allReminders.value
//        if (reminders!!.isEmpty())
//            binding.showRemindersButton.visibility = View.GONE
//        else
//            binding.showRemindersButton.visibility = View.VISIBLE
    }

    private fun attachListeners() {
        binding.showRemindersButton.setOnClickListener {
            val fragmentManager: FragmentManager = parentFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ReminderFragment()

            fragmentTransaction.replace(R.id.frame, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}