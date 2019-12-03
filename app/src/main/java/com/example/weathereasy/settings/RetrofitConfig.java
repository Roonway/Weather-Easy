package com.example.weathereasy.settings;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig(String cityName){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public WeatherService getWeatherService() {
        return this.retrofit.create(WeatherService.class);
    }
}
