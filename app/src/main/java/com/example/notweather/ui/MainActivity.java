package com.example.notweather.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import com.example.notweather.R;
import com.example.notweather.databinding.ActivityMainBinding;
import com.example.notweather.model.City;
import com.example.notweather.model.Coordinates;
import com.example.notweather.ui.adapter.WeatherCardAdapter;
import com.example.notweather.viewmodel.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 7689;

    private final String TAG = MainActivity.class.getSimpleName();

    private WeatherViewModel weatherViewModel;
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        final WeatherCardAdapter adapter = new WeatherCardAdapter();
        binding.rvWeatherCardList.setAdapter(adapter);

        binding.srRefreshCardList.setOnRefreshListener(
                () -> {
                    if (binding.getCity() == null) {
                        return;
                    }

                    Coordinates coordinates = binding.getCity().getCoordinates();
                    getCurrentWeatherByLatLng(coordinates.getLat(), coordinates.getLng());
                });

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel
                .getCityForecasts()
                .observe(
                        this,
                        cityForecast -> {
                            if (cityForecast == null) {
                                return;
                            }

                            City city = cityForecast.getCity();
                            binding.setCity(city);
                            adapter.setCardList(cityForecast.getForecasts());
                        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> getUsersLocation());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void getUsersLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.location_required)
                        .setMessage(R.string.location_require_rationale)
                        .setPositiveButton(
                                android.R.string.ok, (dialog, id) -> requestLocationPermission());
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
                        location -> {
                            // Got last known location. In some rare situations this can be null.
                            if (location == null) {
                                showSnackBar(R.string.unable_to_get_location);
                                return;
                            }
                            // Logic to handle location object
                            getCurrentWeatherByLatLng(
                                    location.getLatitude(), location.getLongitude());
                        });
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (requestCode != PERMISSIONS_REQUEST_FINE_LOCATION) {
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
                        status -> {
                            if (status == null) {
                                return;
                            }

                            switch (status) {
                                case SUCCESS:
                                    binding.srRefreshCardList.setRefreshing(false);
                                    showSnackBar(R.string.successfully_loaded_forecast);
                                    break;
                                case ERROR:
                                    binding.srRefreshCardList.setRefreshing(false);
                                    showSnackBar(R.string.failed_to_load_forecast);
                                    break;
                                case LOADING:
                                    binding.srRefreshCardList.setRefreshing(true);
                                    showSnackBar(R.string.loading_forecast);
                                    break;
                            }
                        });
    }

    private void showSnackBar(@StringRes int msg) {
        Snackbar.make(findViewById(R.id.cl_content), msg, Snackbar.LENGTH_SHORT).show();
    }
}
