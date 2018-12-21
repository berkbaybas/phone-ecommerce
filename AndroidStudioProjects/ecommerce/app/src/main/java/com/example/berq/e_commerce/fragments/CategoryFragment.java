package com.example.berq.e_commerce.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.berq.e_commerce.R;
import com.example.berq.e_commerce.adapter.catAdapter;
import com.example.berq.e_commerce.helper.catItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {


    List<catItem> catList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.catRecyclerView);
        RecyclerView.Adapter adapter = new catAdapter(getActivity(), catList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //  Window w = getWindow();
        //  w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        catList = new ArrayList<>();
        addCat();

        return inflater.inflate(R.layout.fragment_category, container, false);
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
