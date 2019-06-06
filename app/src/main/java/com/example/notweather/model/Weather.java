package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.regex.Pattern;

public class Weather {

    @Ignore private final String RAIN_NOT_RAIN_PATTERN_MATCHER = ".*rain.*|.*drizzle.*";
    @Ignore private final String ICON_URL_FORMAT = "https://api.openweathermap.org/img/w/%s.png";
    @Ignore static final String RAIN = "Rain";
    @Ignore static final String DRIZZLE = "Drizzle";
    @Ignore static final String THUNDERSTORM = "Thunderstorm";
    @Ignore static final String NOT_RAIN = "Not Rain";

    @ColumnInfo(name = "weather_id")
    @SerializedName("id")
    @Expose
    private final int id;

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

    public Weather(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getRainNotRain() {
        if (RAIN.equals(main)) {
            return RAIN;
        }

        if (DRIZZLE.equals(main)) {
            return RAIN;
        }

        if (THUNDERSTORM.equals(main)) {
            Pattern pattern =
                    Pattern.compile(RAIN_NOT_RAIN_PATTERN_MATCHER, Pattern.CASE_INSENSITIVE);
            if (pattern.matcher(description).find()) {
                return RAIN;
            } else {
                return NOT_RAIN;
            }
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
