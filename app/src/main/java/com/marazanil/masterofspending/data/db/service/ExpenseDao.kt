package com.marazanil.masterofspending.data.db.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity

@Dao
interface ExpenseDao {


    @Insert
     fun insertExpense(expense: ExpenseEntity)

    @Delete
     fun deleteExpense(expense: ExpenseEntity)
    @Query("SELECT * FROM expenses")
     fun getAllExpenses(): List<ExpenseEntity>




}
