package com.example.notweather.repository;

import android.support.annotation.NonNull;
import com.example.notweather.model.CityForecast;
import com.example.notweather.repository.remote.NetworkingCallback;

public interface Storage {

    void getCurrentWeatherById(
            int id, @NonNull final NetworkingCallback<CityForecast> networkingCallback);

    void getCurrentWeatherByLatLng(
            double lat,
            double lng,
            @NonNull final NetworkingCallback<CityForecast> networkingCallback);
}
