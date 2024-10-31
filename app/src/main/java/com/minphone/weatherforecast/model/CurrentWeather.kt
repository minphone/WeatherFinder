package com.minphone.weatherforecast.model

data class CurrentWeather(val location: Location, val current: Current)

data class Location(val name: String, val region: String, val country: String)

data class Current(val tempC: Int, val tempF: Int, val condition: Condition)

data class Condition(val name: String, val icon: String)