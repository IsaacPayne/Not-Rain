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
    private final int id;

    @ColumnInfo(name = "city_name")
    @SerializedName("name")
    @Expose
    private final String name;

    @Embedded
    @SerializedName("coord")
    @Expose
    private final Coordinates coordinates;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    @Expose
    private final String country;

    @ColumnInfo(name = "timezone")
    @SerializedName("timezone")
    @Expose
    private final int timezone;

    public City(int id, String name, Coordinates coordinates, String country, int timezone) {
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

    public int getTimezone() {
        return timezone;
    }
}
