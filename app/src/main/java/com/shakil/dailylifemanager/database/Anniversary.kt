package com.shakil.dailylifemanager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anniversaries")
data class Anniversary(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val date: String,
    val year: String = "",
    val note: String = "",
    val isReminderOn: Boolean = true,
    val reminderTime: String = "09:30",
    val createdAt: Long = System.currentTimeMillis()
)