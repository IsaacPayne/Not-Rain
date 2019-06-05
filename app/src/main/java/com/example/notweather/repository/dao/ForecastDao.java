package com.example.notweather.repository.dao;

import android.arch.persistence.room.Dao;
import com.example.notweather.model.Forecast;

@Dao
public abstract class ForecastDao extends BaseDao<Forecast> {}
