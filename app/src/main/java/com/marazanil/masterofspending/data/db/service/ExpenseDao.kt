package com.marazanil.masterofspending.data.db.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow
/*
@Dao
interface ExpenseDao {

    //db ile iletişim interfacesimiz olacak
    // Sorgular yapmamıza olanak tanır ve bu cevapları bize döndürür

    @Query("SELECT * FROM expenses")
    fun getAllExpences(): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun insertExpence(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpence(expense : ExpenseEntity)
} */