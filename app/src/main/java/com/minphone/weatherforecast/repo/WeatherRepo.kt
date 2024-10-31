package com.minphone.weatherforecast.repo

import com.minphone.weatherforecast.model.CurrentWeather
import com.minphone.weatherforecast.network.response.convert
import com.minphone.weatherforecast.network.restClient.IWeatherRestClient
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val restClient: IWeatherRestClient) : IWeatherRepo {
      override suspend fun getWeather(query: String): CurrentWeather {
            return restClient.getWeather(query).convert()
      }
}