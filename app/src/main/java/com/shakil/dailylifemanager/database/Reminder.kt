package com.shakil.dailylifemanager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val date: String,
    val time: String,
    val note: String = "",
    val repeatOption: String = "Once",
    val isReminderOn: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)