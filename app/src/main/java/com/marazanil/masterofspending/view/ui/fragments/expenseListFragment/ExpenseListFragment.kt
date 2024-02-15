package com.marazanil.masterofspending.view.ui.fragments.expenseListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marazanil.masterofspending.R
import com.marazanil.masterofspending.data.db.ExpenseDatabase
import com.marazanil.masterofspending.databinding.FragmentExpenseListBinding
import com.marazanil.masterofspending.view.ui.adapter.ExpenseListAdapter

class ExpenseListFragment : Fragment() {

    private var _binding: FragmentExpenseListBinding? = null
    private var lastCheckedId: Int? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExpenseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadExpenses() // Başlangıçta tüm harcamaları yükleyecek

        val listener = View.OnClickListener { view ->
            val isCheckedAgain = lastCheckedId == view.id
            if (isCheckedAgain) {
                binding.currencyFilterGroup.clearCheck()
                lastCheckedId = null
                loadExpenses() // Filtresiz tüm harcamaları yeniden yükleyecek
            } else {
                lastCheckedId = view.id
                val currencyType = when (view.id) {
                    R.id.radioButtonDolar -> "USD"
                    R.id.radioButtonTL -> "TL"
                    R.id.radioButtonEUR -> "EUR"
                    R.id.radioButtonGBP -> "GBP"
                    else -> null
                }
                loadExpenses(currencyType)
            }
        }

        // Her bir RadioButton için tıklama dinleyicisi atadım
        binding.radioButtonDolar.setOnClickListener(listener)
        binding.radioButtonTL.setOnClickListener(listener)
        binding.radioButtonEUR.setOnClickListener(listener)
        binding.radioButtonGBP.setOnClickListener(listener)

        binding.buttonAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_expenseListFragment_to_expenseAddFragment)
            Toast.makeText(context , "Harcama Ekleme Ekranına Yönlendirildi !" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadExpenses(currencyType: String? = null) {
        val expenseDao = ExpenseDatabase.getDatabase(requireContext())?.expenseDao()
        val expenses = currencyType?.let {
            expenseDao?.getExpensesByCurrencyType(it)
        } ?: expenseDao?.getAllExpenses()

        expenses?.let {
            val adapter = ExpenseListAdapter(it) { expenseId ->
                val bundle = Bundle().apply {
                    putInt("expenseId", expenseId)
                }
                findNavController().navigate(R.id.action_expenseListFragment_to_expenseDetailsFragment, bundle)
            }
            binding.expenseListCardView.adapter = adapter
            binding.expenseListCardView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}