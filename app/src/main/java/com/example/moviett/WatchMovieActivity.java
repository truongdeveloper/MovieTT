package com.example.moviett;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class WatchMovieActivity extends AppCompatActivity {

    WebView webViewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movie);

//        webViewMovie.setWebViewClient(new WebViewClient());
//        webViewMovie.setWebChromeClient(new MyChrome());

        Intent intent = getIntent();
        int idMovie = intent.getIntExtra("idMovie", 1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        webViewMovie = findViewById(R.id.webViewMovie);
        webViewMovie.getSettings().setJavaScriptEnabled(true);
        webViewMovie.getSettings().setDomStorageEnabled(true);
        webViewMovie.getSettings().setAllowFileAccess(true);
        webViewMovie.getSettings().setAllowContentAccess(true);
        webViewMovie.getSettings().setAllowFileAccessFromFileURLs(true);
        webViewMovie.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webViewMovie.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webViewMovie.loadUrl("https://www.2embed.to/embed/tmdb/movie?id=" + idMovie);

        webViewMovie.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                // Xử lý url ở đây nếu cần thiết
//                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private class MyChrome extends WebChromeClient {
//        private View mCustomView;
//        private WebChromeClient.CustomViewCallback mCustomViewCallback;
//        protected FrameLayout mFullscreenContainer;
//        private int mOriginalOrientation;
//        private int mOriginalSystemUiVisibility;
//
//        MyChrome() {}
//
//        public Bitmap getDefaultVideoPoster()
//        {
//            if (mCustomView == null) {
//                return null;
//            }
//            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
//        }
//
//        public void onHideCustomView()
//        {
//            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
//            this.mCustomView = null;
//            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
//            setRequestedOrientation(this.mOriginalOrientation);
//            this.mCustomViewCallback.onCustomViewHidden();
//            this.mCustomViewCallback = null;
//        }
//
//        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
//        {
//            if (this.mCustomView != null)
//            {
//                onHideCustomView();
//                return;
//            }
//            this.mCustomView = paramView;
//            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
//            this.mOriginalOrientation = getRequestedOrientation();
//            this.mCustomViewCallback = paramCustomViewCallback;
//            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
//            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        }
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        webViewMovie.saveState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        webViewMovie.restoreState(savedInstanceState);
//    }
}