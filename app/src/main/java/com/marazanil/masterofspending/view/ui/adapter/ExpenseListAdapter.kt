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
            // Assume you have TextViews with IDs: expenseName, expensePrice, etc.
            // Set their text or click listeners here.
        }
    }

    override fun getItemCount(): Int = expenses.size
}
