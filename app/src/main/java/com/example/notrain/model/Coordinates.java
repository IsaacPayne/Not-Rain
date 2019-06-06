package com.example.notrain.model;

import androidx.room.ColumnInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @ColumnInfo(name = "coordinates_lat")
    @SerializedName("lat")
    @Expose
    private final double lat;

    @ColumnInfo(name = "coordinates_lng")
    @SerializedName("lon")
    @Expose
    private final double lng;

    public Coordinates(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
