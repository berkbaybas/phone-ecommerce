package com.example.berq.e_commerce.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.adapter.MyAdapter;
import com.example.berq.e_commerce.adapter.SlideAdapter;
import com.example.berq.e_commerce.app.AppConfig;
import com.example.berq.e_commerce.helper.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private SlideAdapter slideAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Product> ProductList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewpager);
        slideAdapter = new SlideAdapter(getActivity());
        viewPager.setAdapter(slideAdapter);


        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadProducts();
        return inflater.inflate(R.layout.fragment_home, container, false);
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

                    mAdapter = new MyAdapter(getActivity(), ProductList, 0);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }


}


