package com.example.smartairqualitymonitor.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartairqualitymonitor.domain.model.AirQuality
import com.example.smartairqualitymonitor.domain.repository.IAirQualityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IAirQualityRepository
) : ViewModel() {

    private val _airQuality = MutableStateFlow<AirQuality?>(null)
    val airQuality: StateFlow<AirQuality?> = _airQuality

    private val _history = MutableStateFlow<List<AirQuality>>(emptyList())
    val history: StateFlow<List<AirQuality>> = _history

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchLatestData()
    }

    fun fetchLatestData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val data = repository.fetchAirQuality()
                if (data.isNotEmpty()) {
                    val newReading = data.last()
                    _airQuality.value = newReading

                    // Add to history (keep last 10 readings)
                    val updatedHistory = _history.value.toMutableList()
                    updatedHistory.add(newReading)
                    if (updatedHistory.size > 10) {
                        updatedHistory.removeAt(0)
                    }
                    _history.value = updatedHistory

                    _error.value = null
                } else {
                    _error.value = "Нет данных от сервера"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка подключения: ${e.localizedMessage}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}