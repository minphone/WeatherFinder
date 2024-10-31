package com.minphone.weatherforecast.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
      @GET("v1/current.json")
      suspend fun getWeather(
            @Query("key") key: String,
            @Query("q") query: String,
            @Query("aqi") aqi: String
      ): Response<ResponseBody>
}