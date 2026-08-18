package com.shakil.dailylifemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense): Long
    
    @Update
    suspend fun updateExpense(expense: Expense)
    
    @Delete
    suspend fun deleteExpense(expense: Expense)
    
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>
    
    @Query("SELECT * FROM expenses WHERE date = :date ORDER BY createdAt DESC")
    fun getExpensesByDate(date: String): Flow<List<Expense>>
    
    @Query("SELECT SUM(amount) FROM expenses WHERE date = :date")
    fun getTodayExpenseSum(date: String): Flow<Double?>
    
    @Query("SELECT SUM(amount) FROM expenses WHERE substr(date, 1, 7) = :yearMonth")
    fun getMonthExpenseSum(yearMonth: String): Flow<Double?>
}