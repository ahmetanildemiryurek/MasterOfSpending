package com.marazanil.masterofspending.view.ui.fragments.expenseAddFragment

import androidx.fragment.app.Fragment
import com.marazanil.masterofspending.databinding.FragmentExpenseAddBinding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
