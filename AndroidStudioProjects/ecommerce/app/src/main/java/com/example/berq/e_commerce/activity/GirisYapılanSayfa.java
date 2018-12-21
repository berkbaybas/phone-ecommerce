package com.example.berq.e_commerce.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.helper.SQLiteHandler;
import com.example.berq.e_commerce.helper.SessionManager;

import java.util.HashMap;

public class GirisYapılanSayfa extends AppCompatActivity {
    private TextView Name;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satin_al);

        Name = (TextView) findViewById(R.id.Name);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("email");

        Name.setText(name + " " + "hoşgeldiniz");


        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


    }


    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(GirisYapılanSayfa.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
