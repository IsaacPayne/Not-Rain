package com.example.notweather.ui.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.example.notweather.R;
import com.squareup.picasso.Picasso;

public class PicassoBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(final ImageView view, final String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_image).into(view);
    }
}
