package com.shakil.dailylifemanager.utils

import android.text.format.DateFormat
import java.util.Calendar
import java.util.Date

class DateTimeUtils {
    companion object {
        fun getCurrentDate(): String {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            return String.format("%04d-%02d-%02d", year, month, day)
        }

        fun formatDate(dateString: String): String {
            try {
                val parts = dateString.split("-")
                if (parts.size == 3) {
                    val day = parts[2]
                    val month = parts[1]
                    val year = parts[0]
                    return "$day/${month.toInt()}/$year"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return dateString
        }

        fun getDayOfWeek(dateString: String): String {
            return try {
                val parts = dateString.split("-")
                if (parts.size == 3) {
                    val calendar = Calendar.getInstance()
                    calendar.set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
                    val dayOfWeek =
                        DateFormat.format("EEEE", calendar.time).toString()
                    dayOfWeek
                } else {
                    "Unknown"
                }
            } catch (e: Exception) {
                "Unknown"
            }
        }

        fun getDateFromMillis(millis: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = millis
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            return String.format("%04d-%02d-%02d", year, month, day)
        }

        fun getMillisFromDate(dateString: String): Long {
            return try {
                val parts = dateString.split("-")
                if (parts.size == 3) {
                    val calendar = Calendar.getInstance()
                    calendar.set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
                    calendar.timeInMillis
                } else {
                    System.currentTimeMillis()
                }
            } catch (e: Exception) {
                System.currentTimeMillis()
            }
        }
    }
}