package com.shakil.dailylifemanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Task::class,
        Birthday::class,
        Anniversary::class,
        Reminder::class,
        Note::class,
        Expense::class,
        ShoppingItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LifeStoreDatabase : RoomDatabase() {
    
    abstract fun taskDao(): TaskDao
    abstract fun birthdayDao(): BirthdayDao
    abstract fun anniversaryDao(): AnniversaryDao
    abstract fun reminderDao(): ReminderDao
    abstract fun noteDao(): NoteDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun shoppingItemDao(): ShoppingItemDao
    
    companion object {
        @Volatile
        private var INSTANCE: LifeStoreDatabase? = null
        
        fun getDatabase(context: Context): LifeStoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LifeStoreDatabase::class.java,
                    "life_store_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}