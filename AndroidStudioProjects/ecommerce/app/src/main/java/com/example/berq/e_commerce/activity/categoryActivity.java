package com.example.berq.e_commerce.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.adapter.catAdapter;
import com.example.berq.e_commerce.helper.catItem;

import java.util.ArrayList;
import java.util.List;

public class categoryActivity extends AppCompatActivity {
    List<catItem> catList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

      //  Window w = getWindow();
      //  w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        catList = new ArrayList<>();
        addCat();
        RecyclerView recyclerView = findViewById(R.id.catRecyclerView);
        RecyclerView.Adapter adapter = new catAdapter(this, catList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addCat() {

        catList.add(new catItem(R.drawable.catiphone, 0));
        catList.add(new catItem(R.drawable.catsamsung, 1));
        catList.add(new catItem(R.drawable.cathuwaei, 2));
        catList.add(new catItem(R.drawable.catlg, 3));
        catList.add(new catItem(R.drawable.catasus, 4));
        catList.add(new catItem(R.drawable.catgeneral, 5));
        catList.add(new catItem(R.drawable.cathonor, 6));
        catList.add(new catItem(R.drawable.catvestel, 7));
        catList.add(new catItem(R.drawable.catxaomi, 8));

    }


}
