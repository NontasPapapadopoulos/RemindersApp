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
import com.example.remindersapp.databinding.FragmentReminderBinding


class ReminderFragment : Fragment() {

    private lateinit var binding: FragmentReminderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReminderBinding.inflate(inflater, container, false)
        val view: View = binding.root
        // Inflate the layout for this fragment
        return view
    }


}