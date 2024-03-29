package com.example.notrain.ui.bindingadapter;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.example.notrain.R;
import com.squareup.picasso.Picasso;

public class PicassoBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(final ImageView view, final String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_image).into(view);
    }
}
