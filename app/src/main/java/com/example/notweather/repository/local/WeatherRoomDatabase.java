package com.example.notweather.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.notweather.model.City;
import com.example.notweather.model.Forecast;
import com.example.notweather.repository.dao.CityDao;
import com.example.notweather.repository.dao.ForecastDao;

@Database(entities = {Forecast.class, City.class}, version = 1)
public abstract class WeatherRoomDatabase extends RoomDatabase {

    public abstract CityDao cityDao();
    public abstract ForecastDao forecastDao();

    private static volatile WeatherRoomDatabase INSTANCE;

    public static WeatherRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherRoomDatabase.class, "weather_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}