package com.example.notrain.repository.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.notrain.model.City;
import com.example.notrain.model.Forecast;
import com.example.notrain.repository.dao.CityDao;
import com.example.notrain.repository.dao.ForecastDao;

@Database(
        entities = {Forecast.class, City.class},
        version = 1)
public abstract class WeatherRoomDatabase extends RoomDatabase {

    public static final String WEATHER_DATABASE_NAME = "weather_database";

    public abstract CityDao cityDao();

    public abstract ForecastDao forecastDao();

    private static volatile WeatherRoomDatabase INSTANCE;

    public static WeatherRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(
                                            context.getApplicationContext(),
                                            WeatherRoomDatabase.class,
                                            WEATHER_DATABASE_NAME)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
