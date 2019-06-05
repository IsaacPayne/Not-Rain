package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "city")
public class City {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private int id;

    @ColumnInfo(name = "city_name")
    @SerializedName("name")
    @Expose
    private String name;

    @Embedded
    @SerializedName("coord")
    @Expose
    private Coordinates coordinates;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    @Expose
    private String country;

    @ColumnInfo(name = "timezone")
    @SerializedName("timezone")
    @Expose
    private Integer timezone;

    public City(int id, String name, Coordinates coordinates, String country, Integer timezone) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.country = country;
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountry() {
        return country;
    }

    public Integer getTimezone() {
        return timezone;
    }
}
