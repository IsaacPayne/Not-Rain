package com.example.notweather.repository.remote;

import com.example.notweather.model.CityForecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("forecast")
    // api.openweathermap.org/data/2.5/weather?id=2172797
    Call<CityForecast> getCurrentWeatherById(
            @Query("appid") String appId, @Query("units") String units, @Query("id") int id);

    @GET("forecast")
    Call<CityForecast> getCurrentWeatherByLatLng(
            @Query("appid") String appId,
            @Query("units") String units,
            @Query("lat") double lat,
            @Query("lng") double lng);
}
