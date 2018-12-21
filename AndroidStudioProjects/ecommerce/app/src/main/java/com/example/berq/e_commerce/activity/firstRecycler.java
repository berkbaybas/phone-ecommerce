package com.example.berq.e_commerce.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.adapter.SlideAdapter;
import com.example.berq.e_commerce.app.AppConfig;
import com.example.berq.e_commerce.adapter.MyAdapter;
import com.example.berq.e_commerce.helper.Product;
import com.example.berq.e_commerce.helper.SessionManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class firstRecycler extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter slideAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Product> ProductList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_recycler);
        loadProducts();

        viewPager = findViewById(R.id.viewpager);
        slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);



        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


    }


    public void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_JSONPRODUCT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray products = new JSONArray(response);

                    for (int i = 0; i < products.length(); i++) {
                        JSONObject productObject = products.getJSONObject(i);

                        int id = productObject.getInt("id");
                        String tittle = productObject.getString("tittle");
                        String shortdesc = productObject.getString("shortdesc");
                        double price = productObject.getDouble("price");
                        String image = productObject.getString("image");

                        Product product = new Product(id, tittle, shortdesc, price, image);
                        ProductList.add(product);


                    }

                    mAdapter = new MyAdapter(firstRecycler.this, ProductList, 0);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(firstRecycler.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(firstRecycler.this).add(stringRequest);
    }


}
