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

        val splash = object : Thread() {
            override fun run() {
                try {
                    //thread 5000 ms yani 5 saniye beklesin
                    Thread.sleep(5000)
                    //fragment.navigate ile splash ekranından sonra MainActivity ekranı açılsın diyoruz
                    findNavController().navigate(R.id.action_splashFragment_to_expenseListFragment)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        splash.start()
    }
}

