package com.example.notweather.repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import com.example.notweather.model.Forecast;
import java.util.List;

@Dao
public abstract class ForecastDao extends BaseDao<Forecast> {

    // @Delete
    // public abstract void deleteAll();

    @Query("SELECT * from forecast where city_id = :cityId ORDER BY dt ASC")
    public abstract LiveData<List<Forecast>> getForecastsForCityById(int cityId);
}
