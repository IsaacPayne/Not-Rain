package com.example.notrain.model;

import androidx.room.ColumnInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @ColumnInfo(name = "clouds_all")
    @SerializedName("all")
    @Expose
    private final int all;

    public Clouds(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }
}
