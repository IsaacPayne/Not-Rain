package com.example.notweather.repository.remote;

import androidx.annotation.NonNull;
import com.example.notweather.BuildConfig;
import com.example.notweather.model.CityForecast;
import com.example.notweather.repository.Storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteStorage implements Storage {
    private final String DEFAULT_UNITS = "metric";

    private final OpenWeatherApi openWeatherApi;

    private static volatile RemoteStorage INSTANCE;

    public static RemoteStorage getRemoteStorage() {
        if (INSTANCE == null) {
            synchronized (RemoteStorage.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteStorage();
                }
            }
        }
        return INSTANCE;
    }

    private RemoteStorage() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
        }

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BuildConfig.BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build();

        openWeatherApi = retrofit.create(OpenWeatherApi.class);
    }

    @Override
    public void getCurrentWeatherByLatLng(
            double lat,
            double lng,
            @NonNull final NetworkingCallback<CityForecast> networkingCallback) {
        openWeatherApi
                .getCurrentWeatherByLatLng(BuildConfig.ApiKey, DEFAULT_UNITS, lat, lng)
                .enqueue(createCallback(networkingCallback));
    }

    private <T> Callback<T> createCallback(
            @NonNull final NetworkingCallback<T> networkingCallback) {
        return new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                networkingCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                networkingCallback.onFailure(t);
            }
        };
    }
}
