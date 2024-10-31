package com.minphone.weatherforecast.ui.viewstate

import com.minphone.weatherforecast.model.CurrentWeather

internal sealed class UIEvent {
      data class SearchWeather(val query: String) : UIEvent()
      data class SearchWeatherAPISuccess(val currentWeather: CurrentWeather) : UIEvent()
      data class AddWeather(val currentWeather: CurrentWeather) : UIEvent()
}