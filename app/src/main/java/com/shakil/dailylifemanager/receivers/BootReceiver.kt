package com.shakil.dailylifemanager.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.shakil.dailylifemanager.workers.ReminderWorker
import java.util.concurrent.TimeUnit

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED ||
            intent.action == "android.intent.action.QUICKBOOT_POWERON"
        ) {
            // Schedule reminders on boot
            scheduleReminders(context)
        }
    }

    private fun scheduleReminders(context: Context) {
        val reminderWork = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(5, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork(
            "reminders",
            androidx.work.ExistingWorkPolicy.KEEP,
            reminderWork
        )
    }
}