package com.marazanil.masterofspending.data.db.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import retrofit2.http.GET

@Dao
interface ExpenseDao {
    @Insert
     fun insertExpense(expense: ExpenseEntity)

    @Query("DELETE FROM expenses WHERE expenseId = :expenseId")
    fun deleteExpenseById(expenseId: Int): Int

    @Query("SELECT * FROM expenses")
     fun getAllExpenses(): List<ExpenseEntity>

    @Query("SELECT * FROM expenses WHERE expenseId = :expenseId")
     fun getExpenseById(expenseId: Int): ExpenseEntity?
}


