package com.example.notrain.model;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @Ignore private final String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};

    @ColumnInfo(name = "wind_speed")
    @SerializedName("speed")
    @Expose
    private final double speed;

    @ColumnInfo(name = "wind_deg")
    @SerializedName("deg")
    @Expose
    private final double deg;

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
        int index = (int) Math.round(mod / 45);
        return directions[index];
    }
}
