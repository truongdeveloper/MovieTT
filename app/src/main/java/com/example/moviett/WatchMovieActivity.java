package com.example.moviett;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

public class WatchMovieActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movie);

        Intent intent = getIntent();
        int idMovie = intent.getIntExtra("idMovie", 1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        WebView webViewMovie = findViewById(R.id.webViewMovie);
        webViewMovie.getSettings().setJavaScriptEnabled(true);
        webViewMovie.getSettings().setDomStorageEnabled(true);
        webViewMovie.getSettings().setAllowFileAccess(true);
        webViewMovie.getSettings().setAllowContentAccess(true);
        webViewMovie.getSettings().setAllowFileAccessFromFileURLs(true);
        webViewMovie.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webViewMovie.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webViewMovie.loadUrl("https://www.2embed.to/embed/tmdb/movie?id=" + idMovie);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}