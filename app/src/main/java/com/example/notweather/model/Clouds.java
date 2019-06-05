
package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @ColumnInfo(name = "clouds_all")
    @SerializedName("all")
    @Expose
    private Integer all;

    public Clouds(Integer all) {
        this.all = all;
    }

    public Integer getAll() {
        return all;
    }
}
