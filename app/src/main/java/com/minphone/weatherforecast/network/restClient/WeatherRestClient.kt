package com.minphone.weatherforecast.network.restClient

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.minphone.weatherforecast.BuildConfig
import com.minphone.weatherforecast.network.WeatherService
import com.minphone.weatherforecast.network.response.CurrentWeatherResponse
import javax.inject.Inject

class WeatherRestClient @Inject constructor(private val service: WeatherService) :
      IWeatherRestClient {
      override suspend fun getWeather(query: String): CurrentWeatherResponse {
            val response = service.getWeather(key = BuildConfig.API_KEY, query, "no")

            return if (response.isSuccessful) {
                  Gson().fromJson(
                        response.body()?.string(),
                        object : TypeToken<CurrentWeatherResponse>() {}.type
                  )
            } else CurrentWeatherResponse(null, null)
      }
}