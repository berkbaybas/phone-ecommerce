package com.example.berq.e_commerce.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.berq.e_commerce.helper.Product;
import com.example.berq.e_commerce.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class paymentActivity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        SessionManager sessionManager = new SessionManager(this);
        final EditText nameEditTxt = findViewById(R.id.nameEditTxt);
        final EditText lastnameEditTxt = findViewById(R.id.lastnameEditTxt);
        final EditText addressEditTxt = findViewById(R.id.addressEditTxt);
        final TextView pay = findViewById(R.id.pay);
        Button btnCheckout = findViewById(R.id.btnCheckout);

        Intent intent = getIntent();
        final String payString =intent.getStringExtra("pay");
        pay.setText(payString+"$");
        final String ProductsSession =  sessionManager.getSessionCartValue();
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditTxt.getText().toString().trim();
                String lastname = lastnameEditTxt.getText().toString().trim();
                String address = addressEditTxt.getText().toString().trim();


                if (!name.isEmpty() && !lastname.isEmpty() && !address.isEmpty()) {
                    postPayment(name, lastname, address, Integer.parseInt(payString) , ProductsSession);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

    public void postPayment(final String name , final String lastname , final String address , final int pay , final String ProductsSession) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_POSTorders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(paymentActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("lastname", lastname);
                params.put("address", address);
                params.put("salary", String.valueOf(pay));
                params.put("products",ProductsSession);

                return params;
            }

        };
        Volley.newRequestQueue(paymentActivity.this).add(stringRequest);
    }

}

