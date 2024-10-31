package com.minphone.weatherforecast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.minphone.weatherforecast.model.Condition
import com.minphone.weatherforecast.model.Current
import com.minphone.weatherforecast.model.CurrentWeather
import com.minphone.weatherforecast.model.Location
import com.minphone.weatherforecast.ui.theme.ContentGray700Style
import com.minphone.weatherforecast.ui.theme.TitleBlackStyle
import com.minphone.weatherforecast.ui.theme.TitleGray700Style

@Composable
internal fun WeatherCard(
      weather: CurrentWeather,
      hideAddBtn: Boolean = false,
      addBtnClicked: () -> Unit
) {
      Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp)
      ) {
            Row {
                  Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                              weather.location.name,
                              modifier = Modifier.padding(horizontal = 8.dp),
                              style = TitleBlackStyle
                        )
                        Text(
                              weather.location.region,
                              modifier = Modifier.padding(horizontal = 8.dp),
                              style = ContentGray700Style
                        )
                        Text(
                              weather.location.country,
                              modifier = Modifier.padding(horizontal = 8.dp),
                              style = ContentGray700Style
                        )
                  }
                  Spacer(modifier = Modifier.weight(1f))
                  Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                              weather.current.condition.name,
                              modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .align(Alignment.End),
                              style = TitleGray700Style
                        )

                        Image(
                              painter = rememberAsyncImagePainter(
                                    model = weather.current.condition.icon,
                                    contentScale = ContentScale.Crop,
                              ),
                              contentDescription = "",
                              modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .align(Alignment.End)
                                    .width(50.dp)
                                    .height(50.dp)
                        )

                        if (!hideAddBtn) {
                              Button(
                                    onClick = addBtnClicked,
                                    modifier = Modifier
                                          .padding(horizontal = 8.dp)
                                          .align(Alignment.End)
                              ) {
                                    Text("Add")
                              }
                        }
                  }
            }
      }
}

@Preview
@Composable
private fun PreviewWeatherCard() {
      WeatherCard(
            CurrentWeather(
                  Location(
                        name = "Dallas",
                        region = "Texas",
                        country = "United State"
                  ),
                  current = Current(
                        tempC = 12,
                        tempF = 20,
                        condition = Condition(
                              name = "Clear",
                              icon = "https://cdn.weatherapi.com/weather/64x64/night/113.png"
                        )
                  )
            )
      ) {

      }
}