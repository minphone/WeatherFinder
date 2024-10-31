package com.minphone.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.minphone.weatherforecast.ui.screen.WeatherForecastApp
import com.minphone.weatherforecast.ui.theme.WeatherForecastTheme
import com.minphone.weatherforecast.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

      private val homeViewModel: HomeViewModel by viewModels()

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                  WeatherForecastTheme {
                        WeatherForecastApp(homeViewModel)
                  }
            }
      }
}