package com.example.remindersapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(private var reminders: ArrayList<Reminder>,
                      private val listener: OnItemClickListener)
                : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {



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
        holder.isReminderActiveSwitch.isChecked = reminder.isActive

    }

    fun getReminder(position: Int) : Reminder {
        return reminders[position]
    }


    inner class ReminderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val reminderTimeTextView: TextView = itemView.findViewById(R.id.reminderTimeTextView)
        val reminderTypeTextView: TextView = itemView.findViewById(R.id.reminderTypeTextView)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val isReminderActiveSwitch: Switch = itemView.findViewById(R.id.isReminderActiveSwitch)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}