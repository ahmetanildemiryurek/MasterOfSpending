package com.marazanil.masterofspending.view.ui.fragments.expenseAddFragment

import androidx.fragment.app.Fragment
import com.marazanil.masterofspending.databinding.FragmentExpenseAddBinding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.marazanil.masterofspending.R

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
            Toast.makeText(context,"tıklandı",Toast.LENGTH_SHORT).show()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_expenseAddFragment_to_expenseListFragment)
            Toast.makeText(context , "geri döndük!" , Toast.LENGTH_SHORT).show()        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
