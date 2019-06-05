package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @ColumnInfo(name = "weather_id")
    @SerializedName("id")
    @Expose
    private Integer id;

    @ColumnInfo(name = "weather_main")
    @SerializedName("main")
    @Expose
    private String main;

    @ColumnInfo(name = "weather_description")
    @SerializedName("description")
    @Expose
    private String description;

    @ColumnInfo(name = "weather_icon")
    @SerializedName("icon")
    @Expose
    private String icon;

    public Weather(Integer id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
