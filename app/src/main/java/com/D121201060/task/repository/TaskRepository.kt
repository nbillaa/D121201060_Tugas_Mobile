package com.D121201060.task.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.D121201060.task.data.TaskDao
import com.D121201060.task.model.Task

class TaskRepository (private val taskDao: TaskDao){
    val readAllData:LiveData<List<Task>> = taskDao.readAllData()
    val readAllDataHistory:LiveData<List<Task>> = taskDao.readAllDataHistory()
    suspend fun addTask(task: Task){
        taskDao.addTugas(task)
    }
    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }
    suspend fun deleteAllHistory(){
        taskDao.deleteAllHistory()
    }
}