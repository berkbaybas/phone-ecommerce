package com.example.berq.e_commerce.activity;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.fragments.CartFragment;
import com.example.berq.e_commerce.fragments.CategoryFragment;
import com.example.berq.e_commerce.fragments.EmptyCartFragment;
import com.example.berq.e_commerce.fragments.HomeFragment;
import com.example.berq.e_commerce.fragments.SettingsFragment;
import com.example.berq.e_commerce.helper.SessionManager;


public class MainActivity extends AppCompatActivity {

    private SessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);


        session = new SessionManager(getApplicationContext());


        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.custom_action_item_layout, bottomNavigationMenuView, false);
        final TextView badgeCount = badge.findViewById(R.id.cart_badge);
        badgeCount.setText(String.valueOf(session.getSessionCart().length - 1));
        itemView.addView(badge);

        // açılışta direk fragmentı açar
        Fragment selectedFragment;
        selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {

                case R.id.Anasayfa:

                    selectedFragment = new HomeFragment();

                    break;

                case R.id.Kategori:

                    selectedFragment = new CategoryFragment();

                    break;

                case R.id.Sepetim:
                    if (!session.getSessionCartValue().isEmpty()) {
                        selectedFragment = new CartFragment();
                        break;
                    }
                        selectedFragment = new EmptyCartFragment();
                        break;

              /*      if (!session.getSessionCartValue().isEmpty()) {
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
*/
                case R.id.Ben:
                    if (session.isLoggedIn()){
                        selectedFragment = new SettingsFragment();
                        break;
                    }
                    selectedFragment = new HomeFragment();

                    Intent intent0 = new Intent(
                            MainActivity.this,
                            LoginActivity.class);
                    startActivity(intent0);
                    break;

            }
            assert selectedFragment != null;

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;

        }
    };

}
