package com.shakil.dailylifemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Insert
    suspend fun insertItem(item: ShoppingItem): Long
    
    @Update
    suspend fun updateItem(item: ShoppingItem)
    
    @Delete
    suspend fun deleteItem(item: ShoppingItem)
    
    @Query("SELECT * FROM shopping_items ORDER BY isPurchased ASC, createdAt DESC")
    fun getAllItems(): Flow<List<ShoppingItem>>
    
    @Query("SELECT * FROM shopping_items WHERE isPurchased = 0 ORDER BY createdAt DESC")
    fun getUnpurchasedItems(): Flow<List<ShoppingItem>>
    
    @Query("DELETE FROM shopping_items WHERE isPurchased = 1")
    suspend fun deletePurchasedItems()
}