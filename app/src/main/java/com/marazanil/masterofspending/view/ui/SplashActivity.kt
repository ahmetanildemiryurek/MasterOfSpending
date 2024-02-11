package com.marazanil.masterofspending.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.marazanil.masterofspending.R
import com.marazanil.masterofspending.databinding.FragmentSplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var appLoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appLoginButton = findViewById(R.id.appLoginButton)
        appLoginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}