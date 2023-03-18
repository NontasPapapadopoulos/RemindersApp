package com.example.remindersapp

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(private var reminders: ArrayList<Reminder>)
    : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    var remindersToBeDeleted: ArrayList<Reminder> = ArrayList()

    class ReminderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val reminderTimeTextView: TextView = itemView.findViewById(R.id.reminderTimeTextView)
        val reminderTypeTextView: TextView = itemView.findViewById(R.id.reminderTypeTextView)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val deactivateReminderSwitch: Switch = itemView.findViewById(R.id.deactivateReminderSwitch)
        val deleteCheckBox: CheckBox = itemView.findViewById(R.id.deleteCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.reminder_item,
                                        parent,
                                        false)



        return  ReminderViewHolder(view)
    }

    override fun getItemCount(): Int {
       return reminders.size
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder: Reminder = reminders[position]
        holder.reminderTimeTextView.text = reminder.time
        holder.reminderTypeTextView.text = reminder.type



    }

    fun getReminder(position: Int) : Reminder {
        return reminders[position]
    }

}