package com.marazanil.masterofspending.view.ui.fragments.splashFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marazanil.masterofspending.R

class SplashFragment : Fragment(R.layout.fragment_splash) { // Layout dosyasını burada belirttim.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            // 5 saniye bekledikten sonra expenseListFragment'e yönlendir.
            findNavController().navigate(R.id.action_splashFragment_to_expenseListFragment)
        }, 5000) // 5 saniye bekletme süresi.
    }
}
