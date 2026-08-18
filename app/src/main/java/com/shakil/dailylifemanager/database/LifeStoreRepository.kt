package com.shakil.dailylifemanager.database

import kotlinx.coroutines.flow.Flow

class LifeStoreRepository(private val database: LifeStoreDatabase) {
    
    // Task Methods
    suspend fun insertTask(task: Task) = database.taskDao().insertTask(task)
    suspend fun updateTask(task: Task) = database.taskDao().updateTask(task)
    suspend fun deleteTask(task: Task) = database.taskDao().deleteTask(task)
    fun getAllTasks(): Flow<List<Task>> = database.taskDao().getAllTasks()
    fun getIncompleteTasks(): Flow<List<Task>> = database.taskDao().getIncompleteTasks()
    fun getCompletedTasks(): Flow<List<Task>> = database.taskDao().getCompletedTasks()
    fun getTasksByDate(date: String): Flow<List<Task>> = database.taskDao().getTasksByDate(date)
    fun getIncompleteTaskCount(): Flow<Int> = database.taskDao().getIncompleteTaskCount()
    fun getCompletedTaskCount(): Flow<Int> = database.taskDao().getCompletedTaskCount()
    suspend fun deleteAllCompletedTasks() = database.taskDao().deleteAllCompletedTasks()
    suspend fun getTasksWithReminders(): List<Task> = database.taskDao().getTasksWithReminders()
    
    // Birthday Methods
    suspend fun insertBirthday(birthday: Birthday) = database.birthdayDao().insertBirthday(birthday)
    suspend fun updateBirthday(birthday: Birthday) = database.birthdayDao().updateBirthday(birthday)
    suspend fun deleteBirthday(birthday: Birthday) = database.birthdayDao().deleteBirthday(birthday)
    fun getAllBirthdays(): Flow<List<Birthday>> = database.birthdayDao().getAllBirthdays()
    suspend fun getBirthdaysWithReminders(): List<Birthday> = database.birthdayDao().getBirthdaysWithReminders()
    
    // Anniversary Methods
    suspend fun insertAnniversary(anniversary: Anniversary) = database.anniversaryDao().insertAnniversary(anniversary)
    suspend fun updateAnniversary(anniversary: Anniversary) = database.anniversaryDao().updateAnniversary(anniversary)
    suspend fun deleteAnniversary(anniversary: Anniversary) = database.anniversaryDao().deleteAnniversary(anniversary)
    fun getAllAnniversaries(): Flow<List<Anniversary>> = database.anniversaryDao().getAllAnniversaries()
    suspend fun getAnniversariesWithReminders(): List<Anniversary> = database.anniversaryDao().getAnniversariesWithReminders()
    
    // Reminder Methods
    suspend fun insertReminder(reminder: Reminder) = database.reminderDao().insertReminder(reminder)
    suspend fun updateReminder(reminder: Reminder) = database.reminderDao().updateReminder(reminder)
    suspend fun deleteReminder(reminder: Reminder) = database.reminderDao().deleteReminder(reminder)
    fun getAllReminders(): Flow<List<Reminder>> = database.reminderDao().getAllReminders()
    suspend fun getRemindersWithNotifications(): List<Reminder> = database.reminderDao().getRemindersWithNotifications()
    fun getRemindersByDate(date: String): Flow<List<Reminder>> = database.reminderDao().getRemindersByDate(date)
    
    // Note Methods
    suspend fun insertNote(note: Note) = database.noteDao().insertNote(note)
    suspend fun updateNote(note: Note) = database.noteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = database.noteDao().deleteNote(note)
    fun getAllNotes(): Flow<List<Note>> = database.noteDao().getAllNotes()
    fun searchNotes(searchQuery: String): Flow<List<Note>> = database.noteDao().searchNotes("%$searchQuery%")
    
    // Expense Methods
    suspend fun insertExpense(expense: Expense) = database.expenseDao().insertExpense(expense)
    suspend fun updateExpense(expense: Expense) = database.expenseDao().updateExpense(expense)
    suspend fun deleteExpense(expense: Expense) = database.expenseDao().deleteExpense(expense)
    fun getAllExpenses(): Flow<List<Expense>> = database.expenseDao().getAllExpenses()
    fun getExpensesByDate(date: String): Flow<List<Expense>> = database.expenseDao().getExpensesByDate(date)
    fun getTodayExpenseSum(date: String): Flow<Double?> = database.expenseDao().getTodayExpenseSum(date)
    fun getMonthExpenseSum(yearMonth: String): Flow<Double?> = database.expenseDao().getMonthExpenseSum(yearMonth)
    
    // Shopping Item Methods
    suspend fun insertShoppingItem(item: ShoppingItem) = database.shoppingItemDao().insertItem(item)
    suspend fun updateShoppingItem(item: ShoppingItem) = database.shoppingItemDao().updateItem(item)
    suspend fun deleteShoppingItem(item: ShoppingItem) = database.shoppingItemDao().deleteItem(item)
    fun getAllShoppingItems(): Flow<List<ShoppingItem>> = database.shoppingItemDao().getAllItems()
    fun getUnpurchasedShoppingItems(): Flow<List<ShoppingItem>> = database.shoppingItemDao().getUnpurchasedItems()
    suspend fun deletePurchasedShoppingItems() = database.shoppingItemDao().deletePurchasedItems()
}