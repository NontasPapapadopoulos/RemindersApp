//package com.example.remindersapp
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class RemindersActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var deleteButton: Button
//    private lateinit var reminderAdapter: ReminderAdapter
//    private lateinit var reminderViewModel: ReminderViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_reminders)
//
//        this.recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        this.deleteButton = findViewById(R.id.deleteButton)
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//
//        reminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
//
//        setRemindersList()
//
//        deleteOneReminder()
//        deleteAllReminders()
//
//
//    }
//
//    private fun setRemindersList() {
//        reminderViewModel.allReminders.observe(this,
//            androidx.lifecycle.Observer { list ->
//                list?.let {
//                    reminderAdapter = ReminderAdapter(list as ArrayList<Reminder>, activity as MainActivity)
//                    recyclerView.adapter = reminderAdapter
//                }
//            })
//    }
//
//    private fun deleteOneReminder () {
//        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,
//            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                reminderViewModel.delete(reminderAdapter.getReminder(viewHolder.absoluteAdapterPosition))
//               // Toast.makeText(this, "Reminder deleted", Toast.LENGTH_SHORT).show()
//            }
//
//        }).attachToRecyclerView(recyclerView)
//    }
//
//    private fun deleteAllReminders() {
//        deleteButton.setOnClickListener {
//            reminderViewModel.deleteAllReminders()
//        }
//    }
//
//}