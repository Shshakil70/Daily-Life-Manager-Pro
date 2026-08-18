package com.shakil.dailylifemanager.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationHelper {
    companion object {
        private const val TASK_CHANNEL_ID = "task_reminders"
        private const val EXPENSE_CHANNEL_ID = "expense_alerts"
        private const val GENERAL_CHANNEL_ID = "general_notifications"

        fun createNotificationChannels(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                // Task Reminders Channel
                val taskChannel = NotificationChannel(
                    TASK_CHANNEL_ID,
                    "Task Reminders",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Notifications for task reminders"
                }

                // Expense Alerts Channel
                val expenseChannel = NotificationChannel(
                    EXPENSE_CHANNEL_ID,
                    "Expense Alerts",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Notifications for expense tracking"
                }

                // General Notifications Channel
                val generalChannel = NotificationChannel(
                    GENERAL_CHANNEL_ID,
                    "General Notifications",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "General app notifications"
                }

                notificationManager.createNotificationChannels(
                    listOf(taskChannel, expenseChannel, generalChannel)
                )
            }
        }
    }
}