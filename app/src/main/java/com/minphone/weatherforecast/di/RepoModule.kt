package com.minphone.weatherforecast.di

import com.minphone.weatherforecast.network.restClient.IWeatherRestClient
import com.minphone.weatherforecast.repo.IWeatherRepo
import com.minphone.weatherforecast.repo.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

      @Provides
      @Singleton
      fun provideWeatherRepo(restClient: IWeatherRestClient): IWeatherRepo = WeatherRepo(restClient)

}