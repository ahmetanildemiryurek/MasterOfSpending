package com.marazanil.masterofspending.data


import com.marazanil.masterofspending.data.db.ExpenseDatabase
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity

class ExpenseRepository(private val db: ExpenseDatabase) {

     fun getAllExpenses(): List<ExpenseEntity> {
        return db.expenseDao().getAllExpenses()
    }

     fun insertExpense(expense: ExpenseEntity) {
        db.expenseDao().insertExpense(expense)
    }

     fun deleteExpense(expense: ExpenseEntity) {
        db.expenseDao().deleteExpenseById(expense.expenseId.toInt())
    }

}
