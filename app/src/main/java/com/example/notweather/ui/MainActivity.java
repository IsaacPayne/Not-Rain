package com.example.notweather.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.notweather.R;
import com.example.notweather.ui.adapter.WeatherCardAdapter;
import com.example.notweather.model.CityForecast;
import com.example.notweather.model.Forecast;
import com.example.notweather.repository.Resource;
import com.example.notweather.viewmodel.WeatherViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private final int DEFAULT_CITY_ID = 2172797;
    private final String DEFAULT_CITY_NAME = "Cairns (AU)";

    private WeatherViewModel weatherViewModel;

    private WeatherCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(DEFAULT_CITY_NAME);

        RecyclerView recyclerView = findViewById(R.id.rv_weather_card_list);
        adapter = new WeatherCardAdapter();

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //textView = findViewById(R.id.tv_sample_text);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.getForecastsForCityById(DEFAULT_CITY_ID).observe(this, new Observer<List<Forecast>>() {
            @Override
            public void onChanged(@Nullable final List<Forecast> forecasts) {
                Log.i(TAG, "forecasts changed");
                adapter.setCardList(forecasts);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherById();
            }
        });
    }

    private void getWeatherById() {
        weatherViewModel.getWeatherById(DEFAULT_CITY_ID).observe(this, new Observer<Resource<CityForecast>>() {
            @Override
            public void onChanged(@Nullable Resource<CityForecast> currentWeatherResource) {
                if (currentWeatherResource == null) {
                    return;
                }

                switch (currentWeatherResource.status) {
                    case SUCCESS:
                        Log.i(TAG, "getWeatherById SUCCESS");
                        break;
                    case ERROR:
                        Log.i(TAG, "getWeatherById ERROR");
                        break;
                    case LOADING:
                        Log.i(TAG, "getWeatherById LOADING");
                        break;
                }
            }
        });
    }
}
