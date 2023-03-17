package com.example.remindersapp

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor (
    private val repository: ReminderRepository): ViewModel() {


    val allReminders: LiveData<List<Reminder>> = repository.allReminders.asLiveData()

    fun insert(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(reminder)
    }

    fun delete(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(reminder)
    }

    fun update(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(reminder)
    }

    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllReminders()
    }
}

//class ReminderViewModelFactory(private val repository: ReminderRepository): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ReminderViewModel::class.java))
//            return ReminderViewModel(repository) as T
//        else
//            throw java.lang.IllegalArgumentException("uknown view model")
//    }

//}