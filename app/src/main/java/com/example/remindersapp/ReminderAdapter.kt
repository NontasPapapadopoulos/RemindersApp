package com.example.remindersapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(private var reminders: ArrayList<Reminder>)
                : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    interface OnReminderClickListener {
        fun onReminderClick(position: Int)
    }

    private lateinit var mListener: OnReminderClickListener

    fun setOnReminderClickListener(listener: OnReminderClickListener) {
        mListener = listener
    }

    class ReminderViewHolder(itemView: View, listener: OnReminderClickListener): RecyclerView.ViewHolder(itemView) {
        val reminderTimeTextView: TextView = itemView.findViewById(R.id.reminderTimeTextView)
        val reminderTypeTextView: TextView = itemView.findViewById(R.id.reminderTypeTextView)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val isReminderActiveSwitch: Switch = itemView.findViewById(R.id.isReminderActiveSwitch)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

        init {
            itemView.setOnClickListener{
                listener.onReminderClick(absoluteAdapterPosition )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.reminder_item,
                                        parent,
                                        false)

        return  ReminderViewHolder(view, mListener)
    }

    override fun getItemCount(): Int {
       return reminders.size
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder: Reminder = reminders[position]
        holder.reminderTimeTextView.text = reminder.time
        holder.reminderTypeTextView.text = reminder.type
        holder.isReminderActiveSwitch.isChecked = reminder.isActive

//        holder.cardView.setOnClickListener {
//            // get adapter position
//            val position = holder.absoluteAdapterPosition
//            // call listener
//            mListener.onReminderClick(position)
//            // update position
//           // selectedPosition = position
//            // notify
//            notifyDataSetChanged()
//        }


        holder.cardView.setOnClickListener {
        }

        holder.isReminderActiveSwitch.setOnClickListener {
           // activity.reminderViewModel.update(reminder)
        }

    }

    fun getReminder(position: Int) : Reminder {
        return reminders[position]
    }



}