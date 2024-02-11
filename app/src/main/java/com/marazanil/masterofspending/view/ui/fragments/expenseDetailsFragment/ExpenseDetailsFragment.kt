package com.marazanil.masterofspending.view.ui.fragments.expenseDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marazanil.masterofspending.databinding.FragmentExpenseDetailsBinding


class ExpenseDetailsFragment : Fragment() {


        private var _binding: FragmentExpenseDetailsBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentExpenseDetailsBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.deleteButton.setOnClickListener {

            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null // Clear view binding when the view is destroyed
        }
    }

