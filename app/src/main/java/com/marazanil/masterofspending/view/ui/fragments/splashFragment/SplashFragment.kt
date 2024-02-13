package com.marazanil.masterofspending.view.ui.fragments.splashFragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marazanil.masterofspending.R

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.appLoginButton)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_expenseListFragment)
        }
    }
}
