package com.tanitama.green.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.tanitama.green.data.repository.adapter.HistoryAdapter
import com.tanitama.green.databinding.ActivityHistoryBinding
import com.tanitama.green.presentation.viewmodel.DiseaseDetectionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity(), HistoryAdapter.OnItemClickListener {


    private lateinit var binding: ActivityHistoryBinding
    private val diseaseDetectionViewModel: DiseaseDetectionViewModel by viewModel()
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupViewModelObservers()

        binding.btnBeranda.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(this)
        binding.rvHasil.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = historyAdapter
        }
    }

    private fun setupViewModelObservers() {

        diseaseDetectionViewModel.getHistory()

        diseaseDetectionViewModel.historyResult.observe(this) {
            historyAdapter.setData(it ?: emptyList())
        }
    }

    override fun onItemUserListClicked(id: Int, imageLink: String, idHistory: Int) {
        val intent = Intent(this, DetailHistoryActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("idHistory", idHistory)
        intent.putExtra("imageLink", imageLink)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}