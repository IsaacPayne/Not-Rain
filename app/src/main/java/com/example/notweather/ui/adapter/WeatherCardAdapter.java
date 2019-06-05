package com.example.notweather.ui.adapter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.notweather.R;
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
        new LoadIconTask(holder).execute(listItem);
    }

    public void setCardList(List<Forecast> cardList) {
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    private static class LoadIconTask extends AsyncTask<Forecast, Void, Bitmap> {

        private final WeatherViewHolder holder;

        LoadIconTask(WeatherViewHolder holder) {
            this.holder = holder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ImageView iv = holder.itemView.findViewById(R.id.iv_icon);
            iv.setImageBitmap(null);
        }

        @Override
        protected Bitmap doInBackground(Forecast... params) {
            return params[0].getWeather().getIconBitmap();
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView iv = holder.itemView.findViewById(R.id.iv_icon);
            iv.setImageBitmap(result);
        }
    }
}
