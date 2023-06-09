package com.tanitama.green.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanitama.green.data.model.response.detections.DetectionsResponse
import com.tanitama.green.data.model.response.diseasebyid.DiseaseByIdResponse
import com.tanitama.green.data.model.response.history.Data
import com.tanitama.green.data.repository.DetectionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class DiseaseDetectionViewModel(
    private val detectionsRepository: DetectionsRepository
) : ViewModel() {

    private val _detectionResult = MutableLiveData<DetectionsResponse?>()
    val detectionResult: LiveData<DetectionsResponse?> = _detectionResult

    private val _diseaseById = MutableLiveData<DiseaseByIdResponse?>()
    val diseaseByIdResult: LiveData<DiseaseByIdResponse?> = _diseaseById

    private val _deleteHistory = MutableLiveData<Unit?>()
    val deleteHistoryResult: LiveData<Unit?> = _deleteHistory

    private val _historyResult = MutableLiveData<List<Data>?>()
    val historyResult: LiveData<List<Data>?> = _historyResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun detectDisease(file: File) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = detectionsRepository.detectDisease(file)
                response.body()
            }.onSuccess { result ->
                withContext(Dispatchers.Main) {
                    if (result != null) {
                        _detectionResult.postValue(result)
                    } else {
                        _errorMessage.postValue("Null response received")
                    }
                    _isLoading.postValue(false)
                }
            }.onFailure { throwable ->
                withContext(Dispatchers.Main) {
                    _errorMessage.postValue(throwable.message)
                    _isLoading.postValue(false)
                }
            }
        }
    }

    fun getHistory() {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = detectionsRepository.getHistory()
                response.body()
            }.onSuccess { history ->
                withContext(Dispatchers.Main) {
                    _historyResult.value = history?.data
                }
            }.onFailure { throwable ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = throwable.message
                }
            }.also {
                withContext(Dispatchers.Main) {
                    _isLoading.postValue(false)
                }
            }
        }
    }

    fun deleteHistory(id: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = detectionsRepository.deletDiseaseById(id)
                response.body()
            }.onSuccess { deletedData ->
                withContext(Dispatchers.Main) {
                    _deleteHistory.value = deletedData
                }
            }.onFailure { throwable ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = throwable.message
                }
            }.also {
                withContext(Dispatchers.Main) {
                    _isLoading.postValue(false)
                }
            }
        }
    }

    fun getDiseaseById(id: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = detectionsRepository.getDiseaseById(id)
                response.body()
            }.onSuccess { disease ->
                withContext(Dispatchers.Main) {
                    _diseaseById.value = disease
                }
            }.onFailure { throwable ->
                withContext(Dispatchers.Main) {
                    _errorMessage.value = throwable.message
                }
            }.also {
                withContext(Dispatchers.Main) {
                    _isLoading.postValue(false)
                }
            }
        }
    }


}