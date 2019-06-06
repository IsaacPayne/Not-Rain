package com.example.notrain.repository;

import androidx.annotation.NonNull;
import com.example.notrain.model.CityForecast;
import com.example.notrain.repository.remote.NetworkingCallback;

public interface Storage {

    void getCurrentWeatherByLatLng(
            double lat,
            double lng,
            @NonNull final NetworkingCallback<CityForecast> networkingCallback);
}
