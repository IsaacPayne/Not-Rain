package com.example.notweather.ui;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.example.notweather.R;
import com.example.notweather.model.CityForecast;
import com.example.notweather.repository.Resource;
import com.example.notweather.ui.adapter.WeatherCardAdapter;
import com.example.notweather.viewmodel.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_COARSE_LOCATION = 7689;

    private final String TAG = MainActivity.class.getSimpleName();

    private WeatherViewModel weatherViewModel;
    private WeatherCardAdapter adapter;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.rv_weather_card_list);
        adapter = new WeatherCardAdapter();

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // textView = findViewById(R.id.tv_sample_text);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel
                .getCityForecasts()
                .observe(
                        this,
                        new Observer<CityForecast>() {
                            @Override
                            public void onChanged(@Nullable final CityForecast cityForecast) {
                                if (cityForecast == null) {
                                    return;
                                }
                                Log.i(TAG, "forecasts changed");
                                setTitle(
                                        String.format(
                                                "%s (%s)",
                                                cityForecast.getCity().getName(),
                                                cityForecast.getCity().getCountry()));
                                adapter.setCardList(cityForecast.getForecasts());
                            }
                        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getUsersLocation();
                    }
                });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void getUsersLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.location_required)
                        .setMessage(
                                R.string.location_require_rationale)
                        .setPositiveButton(
                                android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        requestLocationPermission();
                                    }
                                });
                builder.create().show();

            } else {
                // No explanation needed; request the permission
                requestLocationPermission();
            }
            return;
        }

        fusedLocationClient
                .getLastLocation()
                .addOnSuccessListener(
                        this,
                        new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be
                                // null.
                                if (location != null) {
                                    Log.i(TAG, "Location! " + location.toString());
                                    // Logic to handle location object
                                    getCurrentWeatherByLatLng(
                                            location.getLatitude(), location.getLongitude());
                                }
                            }
                        });
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                PERMISSIONS_REQUEST_COARSE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (requestCode != PERMISSIONS_REQUEST_COARSE_LOCATION) {
            return;
        }

        if (grantResults.length == 0) {
            return;
        }

        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        getUsersLocation();
    }

    private void getCurrentWeatherByLatLng(double lat, double lng) {
        weatherViewModel
                .getCurrentWeatherByLatLng(lat, lng)
                .observe(
                        this,
                        new Observer<Resource<CityForecast>>() {
                            @Override
                            public void onChanged(
                                    @Nullable Resource<CityForecast> currentWeatherResource) {
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
