package com.example.moviett;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    // Handle home click
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    finish();
                    return true;
                }
                case R.id.navigation_search: {
                    // Handle favorites click
                    Intent intent = new Intent(this, MovieSearch.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    finish();
                    return true;
                }
                case R.id.navigation_trending: {
                    // Handle settings click
                    Intent intent = new Intent(this, Trending.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    finish();
                    return true;
                }
                case R.id.navigation_coming: {
                    // Handle settings click
                    Intent intent = new Intent(this, ComingSoon.class);
                    startActivity(intent);
                    finish();
                    return true;
            }
            }
            return false;
        });
    }
    public void AnhXa() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
    }
}