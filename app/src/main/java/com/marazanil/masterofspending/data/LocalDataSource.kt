package com.marazanil.masterofspending.data

import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.data.db.service.ExpenseDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val expenseDao: ExpenseDao
) {

     fun getExpences(): List<ExpenseEntity> {
        return expenseDao.getAllExpenses()
    }

     fun insertExpence(expense: ExpenseEntity){
        expenseDao.insertExpense(expense)
    }

     fun deleteExpence(expense: ExpenseEntity){
        expenseDao.deleteExpenseById(expense.expenseId.toInt())
    }

}