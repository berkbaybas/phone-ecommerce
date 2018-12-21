package com.example.berq.e_commerce.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.activity.paymentActivity;
import com.example.berq.e_commerce.adapter.MyAdapter;
import com.example.berq.e_commerce.app.AppConfig;
import com.example.berq.e_commerce.helper.Product;
import com.example.berq.e_commerce.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    TextView totalPrice;
    private List<Product> ProductList = new ArrayList<>();

    String[] strArray;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button checkout = view.findViewById(R.id.Checkout);
        Button deleteCart = view.findViewById(R.id.deleteCart);
        totalPrice = view.findViewById(R.id.totalPrice);

        mRecyclerView = view.findViewById(R.id.cartrecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final SessionManager sessionManager = new SessionManager(getContext());
        strArray = sessionManager.getSessionCart();




        deleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.sil();
              //  finish();
              //  startActivity(getIntent());
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),
                        paymentActivity.class);
                intent.putExtra("pay", String.valueOf(mAdapter.grandTotalPrice()));
                intent.putExtra("productsArray", strArray);
                startActivity(intent);
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadProducts();


        return inflater.inflate(R.layout.fragment_cart, container, false);

    }






    public void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_getProductforcart, new Response.Listener<String>() {
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

                    mAdapter = new MyAdapter(getActivity(), ProductList, 1);

                    mRecyclerView.setAdapter(mAdapter);

                    totalPrice.setText(String.valueOf(mAdapter.grandTotalPrice() + "$"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>(strArray.length);
                for (int i = 0; i < strArray.length; i++) {
                    params.put("idProduct" + i, String.valueOf(strArray[i]));
                }


                return params;
            }

        };
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}
