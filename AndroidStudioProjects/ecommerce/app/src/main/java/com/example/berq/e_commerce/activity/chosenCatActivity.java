package com.example.berq.e_commerce.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.adapter.MyAdapter;
import com.example.berq.e_commerce.app.AppConfig;
import com.example.berq.e_commerce.app.AppController;
import com.example.berq.e_commerce.helper.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class chosenCatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Product> ProductList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_cat);
// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Intent intent = getIntent();

        final int category = intent.getExtras().getInt("catNumber");

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppConfig.URL_Chosen_cat_Product, new Response.Listener<String>() {

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

                    mAdapter = new MyAdapter(chosenCatActivity.this, ProductList, 0);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(chosenCatActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("cat", String.valueOf(category));

                return params;
            }

        };

        Volley.newRequestQueue(chosenCatActivity.this).add(stringRequest);
    }

}


