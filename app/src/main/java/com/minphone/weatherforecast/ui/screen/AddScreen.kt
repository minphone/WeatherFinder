package com.minphone.weatherforecast.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.minphone.weatherforecast.ui.WeatherCard
import com.minphone.weatherforecast.ui.viewstate.UIEvent
import com.minphone.weatherforecast.viewmodel.HomeViewModel

@Composable
fun AddScreen(viewModel: HomeViewModel) {
      val searchText by viewModel.searchText.collectAsState()

      val state = viewModel.uiState.collectAsState()
      val context = LocalContext.current

      Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                  value = searchText,
                  onValueChange = { viewModel.onSearchTextChange(it) },
                  placeholder = { Text("Enter City") },
                  modifier = Modifier.fillMaxWidth()
            )

            state.value.currentWeather?.let {
                  if (it.location.name.isNotEmpty()){
                        WeatherCard(it) {
                              Toast.makeText(context, "Add Successfully.", Toast.LENGTH_LONG).show()
                              viewModel.onEventChange(UIEvent.AddWeather(it))
                        }
                  }
            }
      }
}