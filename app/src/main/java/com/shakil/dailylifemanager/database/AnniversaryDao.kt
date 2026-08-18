package com.shakil.dailylifemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnniversaryDao {
    @Insert
    suspend fun insertAnniversary(anniversary: Anniversary): Long
    
    @Update
    suspend fun updateAnniversary(anniversary: Anniversary)
    
    @Delete
    suspend fun deleteAnniversary(anniversary: Anniversary)
    
    @Query("SELECT * FROM anniversaries ORDER BY date ASC")
    fun getAllAnniversaries(): Flow<List<Anniversary>>
    
    @Query("SELECT * FROM anniversaries WHERE isReminderOn = 1")
    suspend fun getAnniversariesWithReminders(): List<Anniversary>
}