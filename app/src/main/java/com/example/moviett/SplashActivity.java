package com.example.moviett;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.MovieDetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash1);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        // Delay to show splash screen

        ApiService.apiService.getHomeData("en").enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                ListMovie mListMovie = response.body();
                if (mListMovie != null) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("mListMovie", mListMovie);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(SplashActivity.this, "Dữ liệu trả về bị null", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "Không lấy được dữ liệu má ơi", Toast.LENGTH_LONG).show();
            }
        });
        //Thời gian Loading
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Start main activity after delay
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_DELAY);
    }


}
