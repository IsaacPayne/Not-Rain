package com.example.notweather.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.graphics.Bitmap;
import android.util.Log;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;
import java.io.IOException;

public class Weather {
    @Ignore private final String TAG = Weather.class.getSimpleName();
    @Ignore private final int TARGET_WIDTH_HEIGHT = 75;
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

    /**
     * Not really sure if this should live here, but Picasso seems to break the MVVM pattern
     *
     * @return The Icon Bitmap to be displayed in an ImageView
     */
    public Bitmap getIconBitmap() {
        String url = createIconUrl();
        try {
            return Picasso.get().load(url).resize(TARGET_WIDTH_HEIGHT, TARGET_WIDTH_HEIGHT).get();
        } catch (IOException e) {
            Log.w(TAG, String.format("Failed to load img: %s", url));
            return null;
        }
    }

    private String createIconUrl() {
        return String.format("https://api.openweathermap.org/img/w/%s.png", icon);
    }
}
