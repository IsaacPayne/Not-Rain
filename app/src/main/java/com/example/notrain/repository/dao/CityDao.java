package com.example.notrain.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.notrain.model.City;
import com.example.notrain.model.CityForecast;

@Dao
public abstract class CityDao extends BaseDao<City> {

    @Query("DELETE FROM city")
    public abstract void deleteAll();

    @Transaction
    @Query("SELECT * from city ORDER BY city_name ASC")
    public abstract LiveData<CityForecast> getCityForecasts();
}
