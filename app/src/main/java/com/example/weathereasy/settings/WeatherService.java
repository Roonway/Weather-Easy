package com.example.weathereasy.settings;

import com.example.weathereasy.models.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<City> getCityWeather(@Query("q") String nameCity,
                                 @Query("units") String unit,
                                 @Query("appid") String KEY);


}
