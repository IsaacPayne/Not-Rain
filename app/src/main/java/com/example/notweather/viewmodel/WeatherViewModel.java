package com.example.notweather.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.notweather.model.CityForecast;
import com.example.notweather.repository.CityForecastRepository;
import com.example.notweather.repository.Resource;

public class WeatherViewModel extends AndroidViewModel {

    private final CityForecastRepository repository;

    public WeatherViewModel(Application application) {
        super(application);
        repository = new CityForecastRepository(application);
    }

    public LiveData<CityForecast> getCityForecasts() {
        return repository.getCityForecasts();
    }

    public LiveData<Resource.Status> getCurrentWeatherByLatLng(double lat, double lng) {
        return repository.getCurrentWeatherByLatLng(lat, lng);
    }
}
