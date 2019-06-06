package com.example.notrain.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.example.notrain.databinding.CardWeatherBinding;
import com.example.notrain.model.Forecast;

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
