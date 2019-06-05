package com.example.notweather.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.notweather.model.CityForecast;
import com.example.notweather.model.Forecast;
import com.example.notweather.repository.CityForecastRepository;
import com.example.notweather.repository.Resource;
import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private CityForecastRepository repository;

    // private LiveData<List<City>> allCities;

    public WeatherViewModel(Application application) {
        super(application);
        repository = new CityForecastRepository(application);
        // allCities = repository.getAllCityForecasts();
    }

    public LiveData<CityForecast> getCityForecasts() {
        return repository.getCityForecasts();
    }

    public LiveData<List<Forecast>> getForecastsForCityById(int cityId) {
        return repository.getForecastsForCityById(cityId);
    }

    public LiveData<Resource<CityForecast>> getCurrentWeatherByLatLng(double lat, double lng) {
        return repository.getCurrentWeatherByLatLng(lat, lng);
    }
}
