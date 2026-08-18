package com.shakil.dailylifemanager.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shakil.dailylifemanager.utils.NotificationHelper

class ReminderReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        
        val reminderType = intent?.getStringExtra("reminder_type") ?: return
        val reminderId = intent.getIntExtra("reminder_id", -1)
        val reminderTitle = intent.getStringExtra("reminder_title") ?: "Reminder"
        val reminderMessage = intent.getStringExtra("reminder_message") ?: ""
        
        NotificationHelper.showNotification(
            context = context,
            id = reminderId,
            title = reminderTitle,
            message = reminderMessage,
            type = reminderType
        )
    }
}