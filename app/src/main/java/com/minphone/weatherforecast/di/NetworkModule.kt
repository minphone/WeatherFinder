package com.minphone.weatherforecast.di

import com.minphone.weatherforecast.BuildConfig
import com.minphone.weatherforecast.network.WeatherService
import com.minphone.weatherforecast.network.restClient.IWeatherRestClient
import com.minphone.weatherforecast.network.restClient.WeatherRestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

      @Provides
      @Singleton
      fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

      @Provides
      @Singleton
      fun providesOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                  .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                  )
                  .build()
      }

      @Provides
      @Singleton
      fun provideRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            okHttpClient: OkHttpClient
      ): Retrofit {
            val retrofit =
                  Retrofit.Builder()
                        .baseUrl(BuildConfig.Base_URL)
                        .addConverterFactory(gsonConverterFactory)
                        .client(okHttpClient)
                        .build()
            return retrofit
      }

      @Provides
      @Singleton
      fun provideWeatherService(retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)

      @Provides
      @Singleton
      fun provideWeatherRestClient(service: WeatherService): IWeatherRestClient =
            WeatherRestClient(service)

}