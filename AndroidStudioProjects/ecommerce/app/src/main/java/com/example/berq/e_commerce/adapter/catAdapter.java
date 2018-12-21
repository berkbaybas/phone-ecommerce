package com.example.berq.e_commerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.activity.chosenCatActivity;
import com.example.berq.e_commerce.helper.catItem;

import java.util.List;

public class catAdapter extends RecyclerView.Adapter<catAdapter.MyViewHolder> {

    Context mContext;
    List<catItem> mData;

    public catAdapter(Context mContext, List<catItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public catAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(mContext)
                .inflate(R.layout.category_card, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        catItem item = mData.get(position);
        holder.iv.setImageResource(item.getBackground());
        holder.category_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, chosenCatActivity.class);
                intent.putExtra("catNumber", mData.get(position).getCatNumber());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        CardView category_card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.catimageView);
            category_card = itemView.findViewById(R.id.category_card);

        }
    }

}
