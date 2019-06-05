package com.example.notweather.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import com.example.notweather.model.City;
import com.example.notweather.model.CityForecast;
import com.example.notweather.model.Forecast;
import com.example.notweather.repository.dao.CityDao;
import com.example.notweather.repository.dao.ForecastDao;
import com.example.notweather.repository.local.WeatherRoomDatabase;
import com.example.notweather.repository.remote.NetworkingCallback;
import com.example.notweather.repository.remote.RemoteStorage;

public class CityForecastRepository {

    private final CityDao cityDao;
    private final ForecastDao forecastDao;
    private final RemoteStorage remoteStorage;

    public CityForecastRepository(Application application) {
        WeatherRoomDatabase db = WeatherRoomDatabase.getDatabase(application);
        remoteStorage = RemoteStorage.getRemoteStorage();
        cityDao = db.cityDao();
        forecastDao = db.forecastDao();
    }

    public LiveData<Resource.Status> getCurrentWeatherByLatLng(double lat, double lng) {
        final MutableLiveData<Resource.Status> liveData = new MutableLiveData<>();
        liveData.postValue(Resource.Status.LOADING);
        remoteStorage.getCurrentWeatherByLatLng(
                lat,
                lng,
                new NetworkingCallback<CityForecast>() {
                    @Override
                    public void onSuccess(CityForecast response) {
                        insert(response);
                        liveData.postValue(Resource.Status.SUCCESS);
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        liveData.setValue(Resource.Status.ERROR);
                    }
                });
        return liveData;
    }

    public LiveData<CityForecast> getCityForecasts() {
        return cityDao.getCityForecasts();
    }

    private void insert(CityForecast weather) {
        new insertAsyncTask(cityDao, forecastDao).execute(weather);
    }

    private static class insertAsyncTask extends AsyncTask<CityForecast, Void, Void> {

        private final CityDao cityDao;
        private final ForecastDao forecastDao;

        insertAsyncTask(CityDao cityDao, ForecastDao forecastDao) {
            this.cityDao = cityDao;
            this.forecastDao = forecastDao;
        }

        @Override
        protected Void doInBackground(final CityForecast... params) {
            CityForecast cityForecast = params[0];
            City city = cityForecast.getCity();
            // First we clean out the table of any old data
            cityDao.deleteAll();
            // Then we insert the new data
            cityDao.insert(city);
            for (Forecast forecast : params[0].getForecasts()) {
                forecast.setCityId(city.getId());
            }
            forecastDao.insertOrUpdate(params[0].getForecasts());
            return null;
        }
    }
}
