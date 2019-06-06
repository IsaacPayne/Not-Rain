package com.example.notrain.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.notrain.model.CityForecast;
import com.example.notrain.repository.CityForecastRepository;
import com.example.notrain.repository.Resource;

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
