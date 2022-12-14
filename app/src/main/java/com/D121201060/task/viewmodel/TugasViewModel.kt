package com.D121201060.task.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.D121201060.task.data.TaskDatabase
import com.D121201060.task.model.Task
import com.D121201060.task.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TugasViewModel(application: Application):AndroidViewModel(application) {
    val readAllData:LiveData<List<Task>>
    val readAllDataHistory:LiveData<List<Task>>
    private  val repository:TaskRepository

    init{
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllData
        readAllDataHistory = repository.readAllDataHistory
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTask(task)
        }
    }
    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }
    }
    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }
    fun deleteAllHistory(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllHistory()
        }
    }

}