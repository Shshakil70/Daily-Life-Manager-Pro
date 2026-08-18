package com.shakil.dailylifemanager.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.shakil.dailylifemanager.R

object NotificationHelper {
    
    private const val TASK_CHANNEL_ID = "task_reminders"
    private const val BIRTHDAY_CHANNEL_ID = "birthday_reminders"
    private const val ANNIVERSARY_CHANNEL_ID = "anniversary_reminders"
    private const val REMINDER_CHANNEL_ID = "general_reminders"
    
    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            
            val taskChannel = NotificationChannel(
                TASK_CHANNEL_ID,
                "Task Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for task reminders"
                enableVibration(true)
            }
            notificationManager?.createNotificationChannel(taskChannel)
            
            val birthdayChannel = NotificationChannel(
                BIRTHDAY_CHANNEL_ID,
                "Birthday Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for birthday reminders"
                enableVibration(true)
            }
            notificationManager?.createNotificationChannel(birthdayChannel)
            
            val anniversaryChannel = NotificationChannel(
                ANNIVERSARY_CHANNEL_ID,
                "Anniversary Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for anniversary reminders"
                enableVibration(true)
            }
            notificationManager?.createNotificationChannel(anniversaryChannel)
            
            val reminderChannel = NotificationChannel(
                REMINDER_CHANNEL_ID,
                "General Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for general reminders"
                enableVibration(true)
            }
            notificationManager?.createNotificationChannel(reminderChannel)
        }
    }
    
    fun showNotification(
        context: Context,
        id: Int,
        title: String,
        message: String,
        type: String = "task"
    ) {
        val channelId = when (type) {
            "birthday" -> BIRTHDAY_CHANNEL_ID
            "anniversary" -> ANNIVERSARY_CHANNEL_ID
            "reminder" -> REMINDER_CHANNEL_ID
            else -> TASK_CHANNEL_ID
        }
        
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 500))
        
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager?.notify(id, builder.build())
    }
}