package com.minphone.weatherforecast.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minphone.weatherforecast.ui.WeatherCard
import com.minphone.weatherforecast.viewmodel.HomeViewModel

@Composable
fun ListScreen(viewModel: HomeViewModel, addBtnClicked: () -> Unit) {
      Box(
            modifier = Modifier
                  .fillMaxSize()
      ) {
            Column {
                  viewModel.uiState.collectAsState().value.savedWeather.forEach { weather ->
                        WeatherCard(weather, hideAddBtn = true) {

                        }
                  }
            }

            FloatingActionButton(
                  onClick = addBtnClicked, shape = CircleShape,
                  modifier = Modifier
                        .padding(32.dp)
                        .align(Alignment.BottomEnd)
            ) {
                  Image(imageVector = Icons.Filled.Add, contentDescription = "")
            }
      }
}