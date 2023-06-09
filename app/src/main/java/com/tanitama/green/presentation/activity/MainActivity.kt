package com.tanitama.green.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tanitama.green.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAmbilGambar.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        binding.btnHasil.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.toolbarIcon.setOnClickListener {
            clearSharedPreferences()
        }
    }

    private fun clearSharedPreferences() {
        val sharedPref = getSharedPreferences("credentials", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            clear()
            apply()
        }
        Toast.makeText(this, "Log out successful", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}