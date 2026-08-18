package com.shakil.dailylifemanager.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shakil.dailylifemanager.database.LifeStoreDatabase
import com.shakil.dailylifemanager.database.LifeStoreRepository
import com.shakil.dailylifemanager.utils.ReminderScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED ||
            intent?.action == "android.intent.action.QUICKBOOT_POWERON") {
            
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    val database = LifeStoreDatabase.getDatabase(context)
                    val repository = LifeStoreRepository(database)
                    
                    val tasks = repository.getTasksWithReminders()
                    for (task in tasks) {
                        ReminderScheduler.scheduleTaskReminder(context, task)
                    }
                    
                    val birthdays = repository.getBirthdaysWithReminders()
                    for (birthday in birthdays) {
                        ReminderScheduler.scheduleBirthdayReminder(context, birthday)
                    }
                    
                    val anniversaries = repository.getAnniversariesWithReminders()
                    for (anniversary in anniversaries) {
                        ReminderScheduler.scheduleAnniversaryReminder(context, anniversary)
                    }
                    
                    val reminders = repository.getRemindersWithNotifications()
                    for (reminder in reminders) {
                        ReminderScheduler.scheduleReminder(context, reminder)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}