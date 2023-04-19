package com.example.moviett;

import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
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

        ApiService.apiService.getHomeData("vi").enqueue(new Callback<ListMovie>() {
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
                new AlertDialog.Builder(SplashActivity.this)
                        .setTitle("Thông báo")
                        .setMessage("Không lấy được dữ liệu, vui lòng kiểm tra mạng có ổn không?")
                        .setPositiveButton("Load lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        })
                        .setNegativeButton("Thoát ứng dụng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
                SplashActivity.super.onPause();
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
