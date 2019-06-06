package com.example.notweather.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notweather.databinding.CardWeatherBinding;
import com.example.notweather.model.Forecast;
import com.example.notweather.viewholders.WeatherViewHolder;
import java.util.ArrayList;
import java.util.List;

public class WeatherCardAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private List<Forecast> cardList;

    public WeatherCardAdapter() {
        this.cardList = new ArrayList<>();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        CardWeatherBinding itemBinding = CardWeatherBinding.inflate(layoutInflater, parent, false);
        return new WeatherViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final WeatherViewHolder holder, int position) {
        Forecast listItem = cardList.get(position);
        holder.bind(listItem);
    }

    public void setCardList(List<Forecast> cardList) {
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
