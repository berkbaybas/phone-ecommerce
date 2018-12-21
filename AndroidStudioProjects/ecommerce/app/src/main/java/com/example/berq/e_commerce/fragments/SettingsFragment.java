package com.example.berq.e_commerce.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.activity.LoginActivity;
import com.example.berq.e_commerce.helper.SQLiteHandler;
import com.example.berq.e_commerce.helper.SessionManager;

import java.util.HashMap;

public class SettingsFragment extends Fragment {

    private TextView Name;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Name = (TextView) view.findViewById(R.id.Name);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getContext());

        // session manager
        session = new SessionManager(getContext());
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);



        }





    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

    }

}
