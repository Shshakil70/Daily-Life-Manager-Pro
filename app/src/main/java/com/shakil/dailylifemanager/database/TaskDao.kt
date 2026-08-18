package com.shakil.dailylifemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task): Long
    
    @Update
    suspend fun updateTask(task: Task)
    
    @Delete
    suspend fun deleteTask(task: Task)
    
    @Query("SELECT * FROM tasks ORDER BY date ASC, time ASC")
    fun getAllTasks(): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY date ASC, time ASC")
    fun getIncompleteTasks(): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE isCompleted = 1 ORDER BY date DESC")
    fun getCompletedTasks(): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE date = :date ORDER BY time ASC")
    fun getTasksByDate(date: String): Flow<List<Task>>
    
    @Query("SELECT COUNT(*) FROM tasks WHERE isCompleted = 0")
    fun getIncompleteTaskCount(): Flow<Int>
    
    @Query("SELECT COUNT(*) FROM tasks WHERE isCompleted = 1")
    fun getCompletedTaskCount(): Flow<Int>
    
    @Query("DELETE FROM tasks WHERE isCompleted = 1")
    suspend fun deleteAllCompletedTasks()
    
    @Query("SELECT * FROM tasks WHERE isReminderOn = 1 AND isCompleted = 0")
    suspend fun getTasksWithReminders(): List<Task>
}