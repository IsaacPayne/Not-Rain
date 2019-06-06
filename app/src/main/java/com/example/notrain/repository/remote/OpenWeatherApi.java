package com.example.notrain.repository.remote;

import com.example.notrain.model.CityForecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("forecast")
    Call<CityForecast> getCurrentWeatherByLatLng(
            @Query("appid") String appId,
            @Query("units") String units,
            @Query("lat") double lat,
            @Query("lon") double lng);
}
