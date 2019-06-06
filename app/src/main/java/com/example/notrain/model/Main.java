package com.example.notrain.model;

import androidx.room.ColumnInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Locale;

public class Main {

    @ColumnInfo(name = "main_temp")
    @SerializedName("temp")
    @Expose
    private final double temp;

    @ColumnInfo(name = "main_pressure")
    @SerializedName("pressure")
    @Expose
    private final double pressure;

    @ColumnInfo(name = "main_humidity")
    @SerializedName("humidity")
    @Expose
    private final int humidity;

    @ColumnInfo(name = "main_sea_level")
    @SerializedName("sea_level")
    @Expose
    private final double seaLevel;

    @ColumnInfo(name = "main_ground_level")
    @SerializedName("grnd_level")
    @Expose
    private final double groundLevel;

    @ColumnInfo(name = "main_temp_min")
    @SerializedName("temp_min")
    @Expose
    private final double tempMin;

    @ColumnInfo(name = "main_temp_max")
    @SerializedName("temp_max")
    @Expose
    private final double tempMax;

    @ColumnInfo(name = "main_temp_kf")
    @SerializedName("temp_kf")
    @Expose
    private final double tempKf;

    public Main(
            double temp,
            double pressure,
            int humidity,
            double seaLevel,
            double groundLevel,
            double tempMin,
            double tempMax,
            double tempKf) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.seaLevel = seaLevel;
        this.groundLevel = groundLevel;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.tempKf = tempKf;
    }

    public double getTemp() {
        return temp;
    }

    public String getFormattedTemperature() {
        return String.format(Locale.getDefault(), "%.1f %sC", temp, (char) 0x00B0);
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempKf() {
        return tempKf;
    }
}
