package com.example.notweather.viewholders;

import android.support.v7.widget.RecyclerView;
import com.example.notweather.databinding.CardWeatherBinding;
import com.example.notweather.model.Forecast;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private final CardWeatherBinding binding;

    public WeatherViewHolder(CardWeatherBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(final Forecast forecast) {
        binding.setForecast(forecast);
        binding.executePendingBindings();
    }
}
