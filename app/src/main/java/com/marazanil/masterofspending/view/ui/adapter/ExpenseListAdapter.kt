package com.marazanil.masterofspending.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.databinding.ExpenseDesignBinding

class ExpenseListAdapter(
    private val expenses: List<ExpenseEntity>
) : RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(val binding: ExpenseDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ExpenseDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        with(holder.binding) {
            expenseName.text = expense.expenseName
            expenseNumber.text = expense.expenseNumber.toString()
            expensePrice.text = expense.expensePrice
            currencyType.text = expense.currencyType
        }
    }

    override fun getItemCount(): Int = expenses.size
}
