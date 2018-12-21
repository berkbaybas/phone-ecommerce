package com.example.berq.e_commerce.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.allenliu.badgeview.BadgeFactory;
import com.allenliu.badgeview.BadgeView;
import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.activity.CART.cartActivity;
import com.example.berq.e_commerce.activity.CART.emptyCartActivity;
import com.example.berq.e_commerce.helper.SessionManager;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);



        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.custom_action_item_layout, bottomNavigationMenuView, false);
        final TextView badgeCount = badge.findViewById(R.id.cart_badge);
        badgeCount.setText(String.valueOf(session.getSessionCart().length-1));
        itemView.addView(badge);



        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        badgeCount.setText(String.valueOf(session.getSessionCart().length-1));
                        switch (item.getItemId()) {

                            case R.id.Anasayfa:

                                Intent intent1 = new Intent(MainActivity.this,
                                        firstRecycler.class);
                                startActivity(intent1);

                                break;

                            case R.id.Kategori:

                                Intent intent2 = new Intent(MainActivity.this,
                                        categoryActivity.class);
                                startActivity(intent2);

                                break;

                            case R.id.Sepetim:


                                if (!session.getSessionCartValue().isEmpty()) {
                                    Intent intent5 = new Intent(MainActivity.this,
                                            cartActivity.class);
                                    startActivity(intent5);

                                    break;
                                } else {
                                    Intent intent6 = new Intent(MainActivity.this,
                                            emptyCartActivity.class);
                                    startActivity(intent6);

                                    break;
                                }

                            case R.id.Ben:

                                if (session.isLoggedIn()) {
                                    // User is already logged in. Take him to main activity
                                    Intent intent4 = new Intent(MainActivity.this,
                                            GirisYapılanSayfa.class);
                                    startActivity(intent4);
                                    break;
                                }
                                Intent intent0 = new Intent(
                                        MainActivity.this,
                                        LoginActivity.class);
                                startActivity(intent0);
                                break;

                        }
                        return true;
                    }
                });


    }

}
