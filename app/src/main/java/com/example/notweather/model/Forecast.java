package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.notweather.repository.WeatherTypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "forecast", foreignKeys = @ForeignKey(entity = City.class,
        parentColumns = "id",
        childColumns = "city_id",
        onDelete = CASCADE))
public class Forecast {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "city_id")
    private int cityId;

    @Embedded
    @SerializedName("wind")
    @Expose
    private Wind wind;

    @Embedded
    @SerializedName("weather")
    @Expose
    @JsonAdapter(WeatherTypeAdapter.class)
    private Weather weather;

    @Embedded
    @SerializedName("main")
    @Expose
    private Main main;

    @Embedded
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @ColumnInfo(name = "dt")
    @SerializedName("dt")
    @Expose
    private Long timestamp;

    @ColumnInfo(name = "dt_txt")
    @SerializedName("dt_txt")
    @Expose
    private String datetimeString;

    public Forecast(int id, int cityId, Wind wind, Weather weather, Main main,
                    Clouds clouds, Long timestamp, String datetimeString) {
        this.id = id;
        this.cityId = cityId;
        this.wind = wind;
        this.weather = weather;
        this.main = main;
        this.clouds = clouds;
        this.timestamp = timestamp;
        this.datetimeString = datetimeString;
    }

    public int getId() {
        return id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Wind getWind() {
        return wind;
    }

    public Weather getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getDateAsLocalTime() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(timestamp * 1000);

        String pattern = "yyyy-MM-dd hha";
        DateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        formatter.setTimeZone(TimeZone.getDefault());

        return formatter.format(calendar.getTime());
    }

    public String getDatetimeString() {
        return datetimeString;
    }
}
