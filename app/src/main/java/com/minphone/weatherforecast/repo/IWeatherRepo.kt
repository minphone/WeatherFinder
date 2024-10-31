package com.minphone.weatherforecast.repo

import com.minphone.weatherforecast.model.CurrentWeather

interface IWeatherRepo {
      suspend fun getWeather(query: String): CurrentWeather
}