package com.shakil.dailylifemanager.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateTimeUtils {
    
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Calendar.getInstance().time)
    }
    
    fun getCurrentTime(): String {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.format(Calendar.getInstance().time)
    }
    
    fun formatDate(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            outputFormat.format(date ?: Calendar.getInstance().time)
        } catch (e: Exception) {
            dateString
        }
    }
    
    fun formatTime(timeString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val time = inputFormat.parse(timeString)
            outputFormat.format(time ?: Calendar.getInstance().time)
        } catch (e: Exception) {
            timeString
        }
    }
    
    fun getDayOfWeek(dateString: String): String {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.parse(dateString)
            val calendar = Calendar.getInstance()
            calendar.time = date ?: Calendar.getInstance().time
            val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            dayFormat.format(calendar.time)
        } catch (e: Exception) {
            ""
        }
    }
    
    fun getMonthYear(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            outputFormat.format(date ?: Calendar.getInstance().time)
        } catch (e: Exception) {
            dateString
        }
    }
    
    fun isDateToday(dateString: String): Boolean {
        val todayDate = getCurrentDate()
        return dateString == todayDate
    }
    
    fun isDatePast(dateString: String): Boolean {
        val todayDate = getCurrentDate()
        return dateString < todayDate
    }
    
    fun isDateFuture(dateString: String): Boolean {
        val todayDate = getCurrentDate()
        return dateString > todayDate
    }
}