package com.xled.weather_on_compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xled.weather_on_compose.models.Prediction
import com.xled.weather_on_compose.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _pred = MutableStateFlow<Prediction?>(null)
    val pred: StateFlow<Prediction?> = _pred.asStateFlow()

    private val _is_loading = MutableStateFlow(false)
    val is_loading: StateFlow<Boolean> = _is_loading.asStateFlow()

    fun loadPred(){
        viewModelScope.launch {
            _is_loading.value = true
            repository.getPrediction().onSuccess { _pred.value = it }

            _is_loading.value = false
        }
    }
}