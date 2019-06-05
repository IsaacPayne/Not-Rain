package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @Ignore
    private final String[] directions = {"N","NE","E","SE","S","SW","W","NW","N"};

    @ColumnInfo(name = "wind_speed")
    @SerializedName("speed")
    @Expose
    private double speed;

    @ColumnInfo(name = "wind_deg")
    @SerializedName("deg")
    @Expose
    private double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    public String getDirection() {
        double mod = deg % 360;
        int index = (int)Math.round(mod/45)+1;
        return directions[index];
    }

}
