package com.example.notrain;

import static org.junit.Assert.*;

import android.content.Context;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.notrain.model.City;
import com.example.notrain.model.CityForecast;
import com.example.notrain.model.Clouds;
import com.example.notrain.model.Coordinates;
import com.example.notrain.model.Forecast;
import com.example.notrain.model.Main;
import com.example.notrain.model.Weather;
import com.example.notrain.model.Wind;
import com.example.notrain.repository.dao.CityDao;
import com.example.notrain.repository.dao.ForecastDao;
import com.example.notrain.repository.local.WeatherRoomDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RoomTest {
    private CityDao cityDao;
    private ForecastDao forecastDao;
    private WeatherRoomDatabase db;

    // https://stackoverflow.com/a/49840604/1543839
    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context appContext = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(appContext, WeatherRoomDatabase.class).build();
        cityDao = db.cityDao();
        forecastDao = db.forecastDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void writeCityAndReadBack() {
        City city = new City(1, "Wellington", new Coordinates(0.0, 0.0), "NZ", 0);
        cityDao.insert(city);
        LiveData<CityForecast> cityForecasts = cityDao.getCityForecasts();
        City roomCity = LiveDataTestUtil.getValue(cityForecasts).getCity();
        assertEquals(1, roomCity.getId());
        assertEquals("Wellington", roomCity.getName());
        assertEquals("NZ", roomCity.getCountry());
        assertEquals(0, roomCity.getTimezone());
    }

    @Test
    public void writeCitiesAndReadBack() {
        City cityWellington = new City(1, "Wellington", new Coordinates(0.0, 0.0), "NZ", 0);
        cityDao.insert(cityWellington);
        City cityDunedin = new City(2, "Dunedin", new Coordinates(1.0, 2.0), "NZ", 0);
        cityDao.insert(cityDunedin);
        LiveData<CityForecast> cityForecasts = cityDao.getCityForecasts();
        City roomCity = LiveDataTestUtil.getValue(cityForecasts).getCity();
        assertEquals(2, roomCity.getId());
        assertEquals("Dunedin", roomCity.getName());
        assertEquals("NZ", roomCity.getCountry());
        assertEquals(0, roomCity.getTimezone());
    }

    @Test
    public void writeForecastAndReadBack() {
        City cityWellington = new City(1, "Wellington", new Coordinates(0.0, 0.0), "NZ", 0);
        cityDao.insert(cityWellington);

        Wind wind = new Wind(12.0, 357);
        Weather weather = new Weather(200, "Thunderstorm", "thunderstorm with light rain", "11d");
        Main main = new Main(19.678, 0.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Clouds clouds = new Clouds(12);
        Forecast forecast = new Forecast(1, 1, wind, weather, main, clouds, 1560178800000L, "");

        forecastDao.insert(forecast);

        LiveData<CityForecast> cityForecasts = cityDao.getCityForecasts();
        CityForecast roomCityForecast = LiveDataTestUtil.getValue(cityForecasts);

        assertEquals(1, roomCityForecast.getCity().getId());
        assertEquals("Wellington", roomCityForecast.getCity().getName());
        assertEquals("NZ", roomCityForecast.getCity().getCountry());
        assertEquals(0, roomCityForecast.getCity().getTimezone());
        assertEquals(1, roomCityForecast.getForecasts().size());
        assertEquals(forecast.getId(), roomCityForecast.getForecasts().get(0).getId());
    }
}
