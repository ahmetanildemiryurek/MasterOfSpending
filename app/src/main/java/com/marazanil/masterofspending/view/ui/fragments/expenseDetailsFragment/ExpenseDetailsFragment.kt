package com.marazanil.masterofspending.view.ui.fragments.expenseDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.marazanil.masterofspending.R
import com.marazanil.masterofspending.data.db.ExpenseDatabase
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.databinding.FragmentExpenseDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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

        viewLifecycleOwner.lifecycleScope.launch {
            val expense = withContext(Dispatchers.IO) {
                ExpenseDatabase.getDatabase(requireContext())?.expenseDao()?.getExpenseById(expenseId)
            }
            expense?.let {
                displayExpenseDetails(it)
                setupDeleteButton(it.expenseId.toInt())
            }
        }
        backToListFragment()
    }
    private fun setupDeleteButton(expenseId: Int) {
        binding.detailsDeleteButton.setOnClickListener {
            deleteExpense(expenseId)
        }
    }

    private fun backToListFragment() {
        binding.detailsPageBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_expenseDetailsFragment_to_expenseListFragment)
            Toast.makeText(context, "Harcamalar Ekranına Geri Dönüldü!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun displayExpenseDetails(expense: ExpenseEntity?) {
        expense?.let {
            binding.expenseDetailsName.text = it.expenseName
            binding.expenseDetailsPrice.text = it.expensePrice
            binding.currencyDetailsType.text = it.currencyType
            binding.expenseDetails.text = it.expenseDetails
            binding.expenseDetailsNumber.text= it.expenseId.toString()
        }
    }
    private fun deleteExpense(expenseId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                ExpenseDatabase.getDatabase(requireContext())?.expenseDao()?.deleteExpenseById(expenseId)
            }
            if (result != null && result > 0) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Harcama Silindi!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_expenseDetailsFragment_to_expenseListFragment)
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Harcama Silinemedi!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
