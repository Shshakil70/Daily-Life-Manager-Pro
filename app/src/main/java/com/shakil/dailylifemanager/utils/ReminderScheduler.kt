package com.shakil.dailylifemanager.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.shakil.dailylifemanager.database.Anniversary
import com.shakil.dailylifemanager.database.Birthday
import com.shakil.dailylifemanager.database.Reminder
import com.shakil.dailylifemanager.database.Task
import com.shakil.dailylifemanager.receivers.ReminderReceiver
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object ReminderScheduler {
    
    fun scheduleTaskReminder(context: Context, task: Task) {
        if (!task.isReminderOn) return
        
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = "com.shakil.dailylifemanager.REMINDER_ACTION"
            putExtra("reminder_id", task.id)
            putExtra("reminder_type", "task")
            putExtra("reminder_title", "Task Reminder")
            putExtra("reminder_message", task.title)
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val calendar = getCalendarFromDateAndTime(task.date, task.time)
        
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (PermissionHelper.hasExactAlarmPermission(context)) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
    
    fun scheduleBirthdayReminder(context: Context, birthday: Birthday) {
        if (!birthday.isReminderOn) return
        
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = "com.shakil.dailylifemanager.REMINDER_ACTION"
            putExtra("reminder_id", birthday.id)
            putExtra("reminder_type", "birthday")
            putExtra("reminder_title", "Birthday Reminder")
            putExtra("reminder_message", "${birthday.name}'s birthday is today!")
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            birthday.id + 10000,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val calendar = getCalendarFromDateAndTime(birthday.date, birthday.reminderTime)
        
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (PermissionHelper.hasExactAlarmPermission(context)) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
    
    fun scheduleAnniversaryReminder(context: Context, anniversary: Anniversary) {
        if (!anniversary.isReminderOn) return
        
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = "com.shakil.dailylifemanager.REMINDER_ACTION"
            putExtra("reminder_id", anniversary.id)
            putExtra("reminder_type", "anniversary")
            putExtra("reminder_title", "Anniversary Reminder")
            putExtra("reminder_message", "${anniversary.name}'s anniversary is today!")
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            anniversary.id + 20000,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val calendar = getCalendarFromDateAndTime(anniversary.date, anniversary.reminderTime)
        
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (PermissionHelper.hasExactAlarmPermission(context)) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
    
    fun scheduleReminder(context: Context, reminder: Reminder) {
        if (!reminder.isReminderOn) return
        
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = "com.shakil.dailylifemanager.REMINDER_ACTION"
            putExtra("reminder_id", reminder.id)
            putExtra("reminder_type", "reminder")
            putExtra("reminder_title", reminder.title)
            putExtra("reminder_message", reminder.note)
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminder.id + 30000,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val calendar = getCalendarFromDateAndTime(reminder.date, reminder.time)
        
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (PermissionHelper.hasExactAlarmPermission(context)) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
    
    fun cancelReminder(context: Context, reminderId: Int, offset: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminderId + offset,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
    
    private fun getCalendarFromDateAndTime(date: String, time: String): Calendar {
        val calendar = Calendar.getInstance()
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val parsedDate = dateFormat.parse(date)
            calendar.time = parsedDate ?: Calendar.getInstance().time
            
            val timeParts = time.split(":")
            if (timeParts.size == 2) {
                calendar.set(Calendar.HOUR_OF_DAY, timeParts[0].toInt())
                calendar.set(Calendar.MINUTE, timeParts[1].toInt())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return calendar
    }
}