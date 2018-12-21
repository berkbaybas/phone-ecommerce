package com.example.berq.e_commerce.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.app.AppConfig;
import com.example.berq.e_commerce.helper.Product;

import java.util.List;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;


    public SlideAdapter(Context context) {
        this.context = context;

    }

    public int[] list_images = {
            R.drawable.indirim1,
            R.drawable.indirim2,
            R.drawable.indirim3
    };


    @Override
    public int getCount() {
        return list_images.length;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide, container, false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slideLinearlayout);
        ImageView imageslide = view.findViewById(R.id.sliderImage);

        imageslide.setImageResource(list_images[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (LinearLayout) o);
    }
}
