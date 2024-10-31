package com.minphone.weatherforecast.ui.viewstate

import com.minphone.weatherforecast.model.CurrentWeather

data class UIState(
      val currentWeather: CurrentWeather? = null,
      val savedWeather: List<CurrentWeather> = emptyList()
)