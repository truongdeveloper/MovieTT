package com.example.moviett;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ComingSoon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);

        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                case R.id.navigation_home: {
//                    // Handle home click
//                    Intent intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                    finish();
//                    return true;
//                }
//                case R.id.navigation_search: {
//                    // Handle favorites click
//                    Intent intent = new Intent(this, MovieSearch.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                    finish();
//                    return true;
//                }
//                case R.id.navigation_trending: {
//                    // Handle settings click
//                    Intent intent = new Intent(this, Trending.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                    finish();
//                    return true;
//                }
//                case R.id.navigation_coming: {
//                    // Handle settings click
//                    Intent intent = new Intent(this, ComingSoon.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                    finish();
//                    return true;
//                }
//            }
//                return false;
//            }
//        });
    }
}