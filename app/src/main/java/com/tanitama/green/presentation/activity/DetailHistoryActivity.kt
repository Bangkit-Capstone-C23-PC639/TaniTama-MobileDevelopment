package com.tanitama.green.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tanitama.green.databinding.ActivityDetailHistoryBinding
import com.tanitama.green.presentation.viewmodel.DiseaseDetectionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding
    private val diseaseDetectionViewModel: DiseaseDetectionViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModelObservers()

        val idHistory = intent.getIntExtra("idHistory", 0)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.btnHapus.setOnClickListener {
            diseaseDetectionViewModel.deleteHistory(idHistory)
            Toast.makeText(this, "History deleted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    private fun setupViewModelObservers() {

        val id = intent.getIntExtra("id", 0)
        val imageLink = intent.getStringExtra("imageLink")

        diseaseDetectionViewModel.getDiseaseById(id)

        diseaseDetectionViewModel.diseaseByIdResult.observe(this) { disease ->
            binding.tvNamaPenyakit.text = disease?.name
            binding.tvDeskripsiPenyakit.text = disease?.description

            binding.tvRekomendasiPenanganan.text = disease?.recomendation

            Glide.with(this)
                .load(imageLink)
                .into(binding.ivPenyakit)

        }
    }
}
