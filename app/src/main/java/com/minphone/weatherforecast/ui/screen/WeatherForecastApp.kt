package com.minphone.weatherforecast.ui.screen

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.minphone.weatherforecast.R
import com.minphone.weatherforecast.viewmodel.HomeViewModel

enum class WeatherForecastScreen(@StringRes val title: Int) {
      ListScreen(title = R.string.app_name),
      DetailScreen(title = R.string.app_name),
      AddScreen(title = R.string.app_name)
}

@Composable
internal fun WeatherForecastApp(
      viewModel: HomeViewModel,
      navController: NavHostController = rememberNavController()
) {
      val backStackEntry by navController.currentBackStackEntryAsState()
      val currentScreen = WeatherForecastScreen.valueOf(
            backStackEntry?.destination?.route ?: WeatherForecastScreen.ListScreen.name
      )

      Scaffold(
            topBar = {
                  AppBar(
                        title = getScreenName(currentScreen, LocalContext.current),
                        canNavigationBack = navController.previousBackStackEntry != null,
                        backBtnClicked = { navController.navigateUp() })
            }
      ) { paddingValue ->
            NavHost(
                  navController = navController,
                  startDestination = WeatherForecastScreen.ListScreen.name,
                  modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(paddingValue)
            ) {
                  composable(route = WeatherForecastScreen.ListScreen.name) {
                        ListScreen(viewModel, addBtnClicked = {
                              navController.navigate(WeatherForecastScreen.AddScreen.name)
                        })
                  }

                  composable(route = WeatherForecastScreen.AddScreen.name) {
                        AddScreen(viewModel = viewModel)
                  }
            }
      }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
      title: String,
      canNavigationBack: Boolean,
      backBtnClicked: () -> Unit
) {
      TopAppBar(
            title = { Text(title) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            navigationIcon = {
                  if (canNavigationBack) {
                        IconButton(onClick = backBtnClicked) {
                              Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = ""
                              )
                        }
                  }
            }
      )
}

private fun getScreenName(
      currentScreen: WeatherForecastScreen,
      context: Context,
      cityName: String = ""
): String {
      return when (currentScreen) {
            WeatherForecastScreen.ListScreen -> context.getString(R.string.app_name)
            WeatherForecastScreen.DetailScreen -> cityName
            WeatherForecastScreen.AddScreen -> context.getString(R.string.add_weather)
      }
}