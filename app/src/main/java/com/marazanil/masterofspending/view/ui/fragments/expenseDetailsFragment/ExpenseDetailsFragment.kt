package com.marazanil.masterofspending.view.ui.fragments.expenseDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marazanil.masterofspending.data.db.ExpenseDatabase
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.databinding.FragmentExpenseDetailsBinding


class ExpenseDetailsFragment : Fragment() {
    private var _binding: FragmentExpenseDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExpenseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expenseId = arguments?.getInt("expenseId") ?: return
        val expenseDao = ExpenseDatabase.getDatabase(requireContext()).expenseDao()
        val expense = expenseDao.getExpenseById(expenseId)

        displayExpenseDetails(expense)
    }

    private fun displayExpenseDetails(expense: ExpenseEntity?) {
        expense?.let {
            binding.expenseDetailsName.text = it.expenseName
            binding.expenseDetailsPrice.text = it.expensePrice
            binding.currencyDetailsType.text = it.currencyType
            binding.expenseDetails.text = it.expenseDetails
            binding.expenseDetailsNumber.text = it.expenseNumber.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
