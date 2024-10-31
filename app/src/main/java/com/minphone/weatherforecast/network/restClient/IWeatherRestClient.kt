package com.minphone.weatherforecast.network.restClient

import com.minphone.weatherforecast.network.response.CurrentWeatherResponse

interface IWeatherRestClient {
      suspend fun getWeather(query: String): CurrentWeatherResponse
}