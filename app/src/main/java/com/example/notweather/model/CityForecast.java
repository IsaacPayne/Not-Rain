package com.example.notweather.model;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CityForecast {

    @Embedded
    @SerializedName("city")
    @Expose
    private final City city;

    @Relation(parentColumn = "id", entityColumn = "city_id", entity = Forecast.class)
    @SerializedName("list")
    @Expose
    private List<Forecast> forecasts;

    public CityForecast(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
