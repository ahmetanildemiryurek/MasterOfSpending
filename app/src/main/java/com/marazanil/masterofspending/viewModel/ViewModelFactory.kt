package com.marazanil.masterofspending.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marazanil.masterofspending.data.ExpenseRepository
import com.marazanil.masterofspending.data.db.service.ExpenseDao

class ExpenseViewModelFactory(private val repository: ExpenseRepository, private val expenseDao: ExpenseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(repository, expenseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}