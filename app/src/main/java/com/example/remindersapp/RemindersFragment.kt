package com.example.remindersapp

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RemindersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var deleteButton: Button
    private lateinit var reminderAdapter: ReminderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reminder, container, false)
        this.recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        this.deleteButton = view.findViewById(R.id.deleteButton)

        recyclerView.layoutManager = LinearLayoutManager(context)

        reminderAdapter = ReminderAdapter((activity as MainActivity).remindersList)

        recyclerView.adapter = reminderAdapter



        deleteOneReminder()
        deleteAllReminders()
        return view
    }


    private fun deleteOneReminder () {
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (activity as MainActivity).reminderViewModel.delete(reminderAdapter.getReminder(viewHolder.absoluteAdapterPosition))
                Toast.makeText(context, "Reminder deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(recyclerView)
    }

    private fun deleteAllReminders() {
        deleteButton.setOnClickListener {
            (activity as MainActivity).reminderViewModel.deleteAllReminders()
        }
    }

}