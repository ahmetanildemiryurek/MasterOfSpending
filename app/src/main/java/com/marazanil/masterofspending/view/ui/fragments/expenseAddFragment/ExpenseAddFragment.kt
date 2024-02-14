package com.marazanil.masterofspending.view.ui.fragments.expenseAddFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marazanil.masterofspending.R
import com.marazanil.masterofspending.data.db.ExpenseDatabase
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.databinding.FragmentExpenseAddBinding

class ExpenseAddFragment : Fragment() {

    private var _binding: FragmentExpenseAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentExpenseAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.detailsPageAddButton.setOnClickListener {
            saveExpenseToDatabase()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_expenseAddFragment_to_expenseListFragment)
            Toast.makeText(context, "Harcamalar Ekranına Geri Dönüldü!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveExpenseToDatabase() {
        // Veri girişi alanlarından verileri aldım
        val expenseName = binding.editTextExpense.text.toString()
        val expenseDetails = binding.editTextDetails.text.toString()
        val expensePrice = binding.editTextExpensePrice.text.toString()
        val currencyType = getSelectedCurrency()

        // Veri girişinin doğruluğunu kontrol ettim
        if (expenseName.isBlank() || expensePrice.isBlank()) {
            Toast.makeText(context, "Harcama adı ve fiyatı boş olamaz !", Toast.LENGTH_SHORT).show()
            return
        }

        // ExpenseEntity nesnesi oluşturdum
        val expense = ExpenseEntity(
            expenseId = 0, // otomatik id ataması yapması için sıfır verdim
            expenseName = expenseName,
            expenseDetails = expenseDetails,
            expenseNumber = null,//numarayı id ile eşlemek için null verdim
            expensePrice = expensePrice,
            currencyType = currencyType
        )

        // Veritabanına kaydettik
        val expenseDao = ExpenseDatabase.getDatabase(requireContext())?.expenseDao()
        expenseDao?.insertExpense(expense)
        Toast.makeText(context, "Harcama başarıyla kaydedildi", Toast.LENGTH_SHORT).show()
        logSavedExpenses()
        findNavController().navigate(R.id.action_expenseAddFragment_to_expenseListFragment)

    }

    private fun getSelectedCurrency(): String {
        return when (binding.chipGroupCurrency.checkedChipId) {
            R.id.tlChip -> "TL"
            R.id.dollarChip -> "USD"
            R.id.euroChip -> "EUR"
            R.id.sterlinChip -> "GBP"
            else -> "TL" // Varsayılan değer
        }
    }



    private fun logSavedExpenses() {
        val expenseDao = ExpenseDatabase.getDatabase(requireContext())?.expenseDao()
        val expenses = expenseDao?.getAllExpenses()
        expenses?.forEach { expense ->
            Log.d("Made Expenses", "ID: ${expense.expenseId}, Name: ${expense.expenseName}," +
                    "Details: ${expense.expenseDetails}, Number: ${expense.expenseNumber}, Price: ${expense.expensePrice}, Currency: ${expense.currencyType}")
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
