package com.example.berq.e_commerce.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.activity.LoginActivity;
import com.example.berq.e_commerce.helper.Product;
import com.example.berq.e_commerce.helper.SessionManager;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Product> mDataset;
    private Context c;
    private SessionManager sessionManager;
    private int cardId;


    public MyAdapter(Context c, List<Product> mDataset, int cardId) {
        this.c = c;
        this.mDataset = mDataset;
        this.cardId = cardId;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        public TextView productTittle;
        public TextView productDesc;
        public TextView productPrice;
        public ImageView productImageView;
        public CardView cardviewproduct;
        public Button btnDeleteCart;
        public Button btnaddCart;



        public MyViewHolder(View v) {
            super(v);
            btnaddCart = (Button) v.findViewById(R.id.btnaddCart);
            btnDeleteCart = v.findViewById(R.id.btnDeleteCart);

            productTittle = (TextView) v.findViewById(R.id.productTittle);
            productDesc = (TextView) v.findViewById(R.id.productDesc);
            productPrice = (TextView) v.findViewById(R.id.productPrice);
            productImageView = (ImageView) v.findViewById(R.id.ProductImageView);
            cardviewproduct = (CardView) v.findViewById(R.id.cardviewproduct);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Product> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v;
        MyViewHolder vh = null;
        switch (cardId) {
            case 0:
                v = (View) LayoutInflater.from(c)
                        .inflate(R.layout.cardviewproduct, parent, false);
                vh = new MyViewHolder(v);

                break;
            case 1:
                v = (View) LayoutInflater.from(c)
                        .inflate(R.layout.cardviewproductincart, parent, false);
                vh = new MyViewHolder(v);

                break;
        }

        return vh;


    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Product product;
        switch (cardId) {
            // buton ekle
            case 0:
                product = (Product) mDataset.get(position);
                holder.productDesc.setText(product.getShortdesc());
                holder.productTittle.setText(product.getTittle());
                holder.productPrice.setText(String.valueOf(product.getPrice() + "$"));
                holder.btnaddCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sessionManager = new SessionManager(c);
                        if (sessionManager.isLoggedIn()) {
                            int chosenProductid = product.getId();
                            sessionManager.addSessionCart(chosenProductid);
                            Button btnaddCart = v.findViewById(R.id.btnaddCart);
                            btnaddCart.setText("Eklendi..");
                            btnaddCart.setEnabled(false);

                        } else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(c);
                            alertDialog.setTitle("SEPET");
                            alertDialog.setMessage("Ürün alımı için giriş yapmanız gerekmektedir");
                            alertDialog.setPositiveButton("Giriş yap", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(c, LoginActivity.class);
                                    c.startActivity(intent);

                                }
                            });
                            alertDialog.setNegativeButton("Çık", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.cancel();

                                }
                            });

                            AlertDialog dialog = alertDialog.create();
                            dialog.show();
                        }


                    }
                });

                Glide.with(c)
                        .load(product.getImage())
                        .into(holder.productImageView);

                break;
            case 1:
                //buton sil olan
                product = (Product) mDataset.get(position);
                holder.productDesc.setText(product.getShortdesc());
                holder.productTittle.setText(product.getTittle());
                holder.productPrice.setText(String.valueOf(product.getPrice() + "$"));

                holder.btnDeleteCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sessionManager = new SessionManager(c);
                        int productId = mDataset.get(position).getId();
                        sessionManager.silbirini(productId);
                        notifyItemRemoved(position);
                        mDataset.remove(position);
                        notifyDataSetChanged();
                        TextView totalPrice = (TextView) ((Activity)c).findViewById(R.id.totalPrice);
                        totalPrice.setText(String.valueOf(grandTotalPrice() + "$"));


                    }
                });
                Glide.with(c)
                        .load(product.getImage())
                        .into(holder.productImageView);
                break;
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public int grandTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < mDataset.size(); i++) {
            totalPrice += mDataset.get(i).getPrice();
        }
        return totalPrice;
    }
}

