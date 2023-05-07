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
import android.widget.Toast;

public class WatchMovieActivity extends AppCompatActivity {

    WebView webViewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movie);


        Intent intent = getIntent();
        String idMovie = intent.getStringExtra("idMovie");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        webViewMovie = findViewById(R.id.webViewMovie);
//Toàn màn hình
//        webViewMovie.setWebViewClient(new WebViewClient());
//        webViewMovie.setWebChromeClient(new MyChrome());
        webViewMovie.getSettings().setJavaScriptEnabled(true);


        String html = String.format("<html ><body style=\"background-color:\"#000\"><center><iframe width=\"600\" height=\"300\" src=\"https://www.youtube.com/embed/%s\" title=\"Trailer\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></center></body></html>", idMovie);
        webViewMovie.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
// quảng cáo
//        webViewMovie.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                String url = request.getUrl().toString();
//                // Xử lý url ở đây nếu cần thiết
////                view.loadUrl(url);
//                return true;
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
// xử lý toàn màn hình
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