package com.minphone.weatherforecast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minphone.weatherforecast.model.CurrentWeather
import com.minphone.weatherforecast.repo.IWeatherRepo
import com.minphone.weatherforecast.ui.viewstate.UIEvent
import com.minphone.weatherforecast.ui.viewstate.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: IWeatherRepo) : ViewModel() {

      private val _searchText = MutableStateFlow("")
      val searchText = _searchText.asStateFlow()

      private val _uiState = MutableStateFlow(UIState())
      val uiState = _uiState.asStateFlow()

      internal fun onEventChange(event: UIEvent) {
            when (event) {
                  is UIEvent.SearchWeather -> {
                        searchWeather(event.query)
                  }

                  is UIEvent.SearchWeatherAPISuccess -> {
                        _uiState.update {
                              it.copy(currentWeather = event.currentWeather)
                        }
                  }

                  is UIEvent.AddWeather -> {
                        val weathers = mutableListOf<CurrentWeather>()
                        weathers.addAll(uiState.value.savedWeather)
                        weathers.add(event.currentWeather)
                        _uiState.update {
                              it.copy(savedWeather = weathers)
                        }
                  }
            }
      }

      private fun searchWeather(query: String) {
            viewModelScope.launch {
                  onEventChange(UIEvent.SearchWeatherAPISuccess(repo.getWeather(query)))
            }
      }

      fun onSearchTextChange(text: String) {
            _searchText.value = text
            onEventChange(UIEvent.SearchWeather(searchText.value))
      }

}