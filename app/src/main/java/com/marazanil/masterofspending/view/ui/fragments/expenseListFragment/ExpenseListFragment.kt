package com.marazanil.masterofspending.view.ui.fragments.expenseListFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marazanil.masterofspending.R
import com.marazanil.masterofspending.databinding.FragmentExpenseListBinding

class ExpenseListFragment : Fragment() {

    private var _binding: FragmentExpenseListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExpenseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.buttonAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_expenseListFragment_to_expenseAddFragment)
            Toast.makeText(context , "Harcama Ekleme Ekranına Yönlendirildi !" , Toast.LENGTH_SHORT).show()
           //Log.d("aaa","tıklandı")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
