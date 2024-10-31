package com.minphone.weatherforecast.network.response

import com.google.gson.annotations.SerializedName
import com.minphone.weatherforecast.model.Condition
import com.minphone.weatherforecast.model.Current
import com.minphone.weatherforecast.model.CurrentWeather
import com.minphone.weatherforecast.model.Location

class CurrentWeatherResponse(
      val location: LocationResponse?,
      val current: CurrentResponse?
)

class LocationResponse(val name: String?, val region: String?, val country: String?)

class CurrentResponse(
      @SerializedName("temp_c") val tempC: Double?,
      @SerializedName("temp_f") val tempF: Double?,
      val condition: ConditionResponse?
)

class ConditionResponse(val text: String?, val icon: String?)

internal fun CurrentWeatherResponse.convert(): CurrentWeather {
      return CurrentWeather(
            location = location?.let {
                  Location(
                        name = it.name.orEmpty(),
                        region = it.region.orEmpty(),
                        country = it.country.orEmpty()
                  )
            } ?: Location(name = "", region = "", country = ""),
            current = current?.let {
                  Current(
                        tempC = (it.tempC ?: 0.0).toInt(),
                        tempF = (it.tempF ?: 0.0).toInt(),
                        condition = it.condition?.convert() ?: Condition(name = "", icon = "")
                  )
            } ?: Current(tempC = 0, tempF = 0, condition = Condition(name = "", icon = ""))
      )
}

private fun ConditionResponse.convert(): Condition =
      Condition(name = text.orEmpty(), icon = "https:${icon.orEmpty()}")