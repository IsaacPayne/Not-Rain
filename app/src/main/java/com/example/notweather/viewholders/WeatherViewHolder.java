package com.example.notweather.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.notweather.databinding.CardWeatherBinding;
import com.example.notweather.model.Forecast;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private final CardWeatherBinding binding;
    private final View view;

    public WeatherViewHolder(CardWeatherBinding binding) {
        super(binding.getRoot());
        this.view = binding.getRoot();
        this.binding = binding;
    }

    public void bind(final Forecast forecast) {
        // if (weatherModel.isReady()) {
        //    Log.d("WeatherViewHolder", "weatherModel.isReady()");
        //    loadCard(weatherModel);
        //    return;
        // }
        //
        // Log.d("WeatherViewHolder", "weatherModel.isNotReady()");
        // weatherModel.setOnReadyListener(new WeatherModel.OnReadyListener() {
        //    @Override
        //    public void onReady() {
        //        loadCard(weatherModel);
        //    }
        // });
        loadCard(forecast);
    }

    private void loadCard(Forecast forecast) {
        binding.setForecast(forecast);
        binding.executePendingBindings();

        // LinearLayout loadingOverlay = binding.getRoot().findViewById(R.id.loading_overlay);
        // loadingOverlay.setVisibility(View.GONE);
    }
}
