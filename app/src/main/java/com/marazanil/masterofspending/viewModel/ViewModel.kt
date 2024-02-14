package com.marazanil.masterofspending.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazanil.masterofspending.data.ExpenseRepository
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.data.db.service.ExpenseDao
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository , private val expenseDao: ExpenseDao) : ViewModel() {

    private val _expenses = MutableLiveData<List<ExpenseEntity>>()
    val expenses: LiveData<List<ExpenseEntity>> = _expenses

    private val _navigateToAddExpense = MutableLiveData<Boolean>()
    val navigateToAddExpense: LiveData<Boolean> = _navigateToAddExpense

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            try {
                val expenses = expenseDao.getAllExpenses()
                val expenseList = repository.getAllExpenses()
                _expenses.value = expenseList
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load expenses: ${e.message}"
            }
        }
    }

    fun addExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            try {
                repository.insertExpense(expense)
                loadExpenses()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add expense: ${e.message}"
            }
        }
    }

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            try {
                repository.deleteExpense(expense)
                loadExpenses()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete expense: ${e.message}"
            }
        }
    }

    fun onAddExpenseClicked() {
        _navigateToAddExpense.value = true
    }

    fun onNavigatedToAddExpense() {
        _navigateToAddExpense.value = false
    }
}
