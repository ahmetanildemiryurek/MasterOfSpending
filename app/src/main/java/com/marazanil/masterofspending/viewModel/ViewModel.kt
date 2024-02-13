package com.marazanil.masterofspending.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marazanil.masterofspending.data.ExpenseRepository
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    // LiveData to hold the list of expenses
    private val _expenses = MutableLiveData<List<ExpenseEntity>>()
    val expenses: LiveData<List<ExpenseEntity>> = _expenses

    // LiveData to handle navigation events, like opening the details or add screen
    private val _navigateToAddExpense = MutableLiveData<Boolean>()
    val navigateToAddExpense: LiveData<Boolean> = _navigateToAddExpense

    // LiveData to handle error messages or notifications
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            try {
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
                loadExpenses() // Refresh the list after adding
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add expense: ${e.message}"
            }
        }
    }

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            try {
                repository.deleteExpense(expense)
                loadExpenses() // Refresh the list after deletion
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete expense: ${e.message}"
            }
        }
    }

    // Call this method when the add expense FAB is clicked
    fun onAddExpenseClicked() {
        _navigateToAddExpense.value = true
    }

    // Call this after navigating to the add expense screen
    fun onNavigatedToAddExpense() {
        _navigateToAddExpense.value = false
    }
}
