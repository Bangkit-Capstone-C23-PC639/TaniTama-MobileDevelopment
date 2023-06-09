package com.tanitama.green.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tanitama.green.data.model.response.detections.DetectionsResponse
import com.tanitama.green.databinding.ActivityHasilDetectionBinding

class HasilDetectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilDetectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val intent = intent
        val detectionsResponse: DetectionsResponse? = intent.getParcelableExtra("detectionsResponse")


        Glide.with(this)
            .load(detectionsResponse?.data?.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivPenyakit)

        binding.tvDescription.text = detectionsResponse?.data?.result?.name
        binding.tvDeskripsiPenyakit.text = detectionsResponse?.data?.result?.description


        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}