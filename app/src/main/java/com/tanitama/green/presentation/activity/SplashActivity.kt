package com.tanitama.green.presentation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.tanitama.green.data.model.request.LoginUserRequestBody
import com.tanitama.green.databinding.ActivitySplashBinding
import com.tanitama.green.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashTime = 4000L
    private val authViewModel: AuthViewModel by viewModel()  // Add this line

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        val sharedPref = getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val email = sharedPref.getString("email", null)
        val password = sharedPref.getString("password", null)

        Handler(Looper.getMainLooper()).postDelayed({
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                authViewModel.loginUser(LoginUserRequestBody(email, password))
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()
        }, splashTime)

        setContentView(binding.root)
    }
}