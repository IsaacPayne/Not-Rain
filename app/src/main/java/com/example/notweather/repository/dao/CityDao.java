package com.example.notweather.repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import com.example.notweather.model.City;
import com.example.notweather.model.CityForecast;

@Dao
public abstract class CityDao extends BaseDao<City> {

    @Query("DELETE FROM city")
    public abstract void deleteAll();

    @Transaction
    @Query("SELECT * from city ORDER BY city_name ASC")
    public abstract LiveData<CityForecast> getCityForecasts();
}
