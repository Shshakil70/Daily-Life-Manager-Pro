package com.shakil.dailylifemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BirthdayDao {
    @Insert
    suspend fun insertBirthday(birthday: Birthday): Long
    
    @Update
    suspend fun updateBirthday(birthday: Birthday)
    
    @Delete
    suspend fun deleteBirthday(birthday: Birthday)
    
    @Query("SELECT * FROM birthdays ORDER BY date ASC")
    fun getAllBirthdays(): Flow<List<Birthday>>
    
    @Query("SELECT * FROM birthdays WHERE isReminderOn = 1")
    suspend fun getBirthdaysWithReminders(): List<Birthday>
}