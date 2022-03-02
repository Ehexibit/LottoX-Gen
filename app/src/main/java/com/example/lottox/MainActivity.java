package com.example.lottox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lottox.model.Lotto;
import com.example.lottox.model.LottoFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    LottoFactory factory;
    FragmentManager fragmentManager;

    private List<MainMenu> menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        recyclerView = findViewById(R.id.mainMenu);
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        factory = LottoFactory.getInstance();
        fragmentManager = getSupportFragmentManager();

        new Thread(() -> factory.init()).start();


        initializeMenu(); //Initialize and Instantiate MainMenu Objects
        initializeMenuAdapter(); //Initialize RecyclerView Adapter Implementation

    }
    private void initializeMenu(){
    menus = new ArrayList<>();
    menus.add(new MainMenu("ULTRA 6/58",R.drawable.ultra_lotto,Lotto.ULTRA));
    menus.add(new MainMenu("GRAND/ 6/55",R.drawable.grand_lotto, Lotto.GRAND));
    menus.add(new MainMenu("SUPER 6/49",R.drawable.super_lotto,Lotto.SUPER));
    menus.add(new MainMenu("MEGA 6/45",R.drawable.mega_lotto,Lotto.MEGA));
    menus.add(new MainMenu("LOTTO 6/42",R.drawable.lotto,Lotto.LOTTO));
    }
    private void initializeMenuAdapter(){
        MainMenuViewAdapter menu = new MainMenuViewAdapter(menus);
        menu.setFragmentManager(fragmentManager);
        recyclerView.setAdapter(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("On Paused","Paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("On Resume","Resumed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("On Destroy","Destroyed");
    }

}