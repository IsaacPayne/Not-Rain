
package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class Main {

    @ColumnInfo(name = "main_temp")
    @SerializedName("temp")
    @Expose
    private Double temp;

    @ColumnInfo(name = "main_pressure")
    @SerializedName("pressure")
    @Expose
    private Double pressure;

    @ColumnInfo(name = "main_humidity")
    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    @ColumnInfo(name = "main_sea_level")
    @SerializedName("sea_level")
    @Expose
    private Double seaLevel;

    @ColumnInfo(name = "main_ground_level")
    @SerializedName("ground_level")
    @Expose
    private Double groundLevel;

    @ColumnInfo(name = "main_temp_min")
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;

    @ColumnInfo(name = "main_temp_max")
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;

    @ColumnInfo(name = "main_temp_kf")
    @SerializedName("temp_kf")
    @Expose
    private Double tempKf;

    public Main(Double temp, Double pressure, Integer humidity, Double seaLevel, Double groundLevel, Double tempMin, Double tempMax, Double tempKf) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.seaLevel = seaLevel;
        this.groundLevel = groundLevel;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.tempKf = tempKf;
    }

    public Double getTemp() {
        return temp;
    }

    public String getFormattedTemperature() {
        return String.format(Locale.getDefault(), "%.1f %sC", temp, (char) 0x00B0);
    }

    public Double getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public Double getGroundLevel() {
        return groundLevel;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public Double getTempKf() {
        return tempKf;
    }
}
