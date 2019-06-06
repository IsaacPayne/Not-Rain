package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @Ignore private final String ICON_URL_FORMAT = "https://api.openweathermap.org/img/w/%s.png";
    @Ignore private final String RAIN = "Rain";
    @Ignore private final String NOT_RAIN = "Not Rain";

    @ColumnInfo(name = "weather_id")
    @SerializedName("id")
    @Expose
    private final Integer id;

    @ColumnInfo(name = "weather_main")
    @SerializedName("main")
    @Expose
    private final String main;

    @ColumnInfo(name = "weather_description")
    @SerializedName("description")
    @Expose
    private final String description;

    @ColumnInfo(name = "weather_icon")
    @SerializedName("icon")
    @Expose
    private final String icon;

    public Weather(Integer id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getRainNotRain() {
        if (RAIN.equals(main)) {
            return RAIN;
        }

        return NOT_RAIN;
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

    public String getIconUrl() {
        return String.format(ICON_URL_FORMAT, icon);
    }
}
