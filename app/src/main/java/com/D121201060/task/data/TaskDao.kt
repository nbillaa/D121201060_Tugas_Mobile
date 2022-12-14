package com.D121201060.task.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.D121201060.task.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTugas(tugas: Task)

    @Query("SELECT * FROM task WHERE status='Belum Selesai'")
    fun readAllData(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE status='Selesai'")
    fun readAllDataHistory(): LiveData<List<Task>>

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM task WHERE status='Selesai'")
    suspend fun deleteAllHistory()
}