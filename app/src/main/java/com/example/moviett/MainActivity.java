package com.example.moviett;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.moviett.Adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trang chủ");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                        actionBar.setTitle("Trang chủ");
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.navigation_search).setChecked(true);
                        actionBar.setTitle("Tìm kiếm");
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.navigation_trending).setChecked(true);
                        actionBar.setTitle("Xu hướng");
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.navigation_coming).setChecked(true);
                        actionBar.setTitle("Sắp ra mắt");
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mViewPager.setCurrentItem(0);
                        actionBar.setTitle("Trang chủ");
                        break;
                    case R.id.navigation_search:
                        mViewPager.setCurrentItem(1);
                        actionBar.setTitle("Tìm kiếm");
                        break;
                    case R.id.navigation_trending:
                        mViewPager.setCurrentItem(2);
                        actionBar.setTitle("Xu hướng");
                        break;
                    case R.id.navigation_coming:
                        mViewPager.setCurrentItem(3);
                        actionBar.setTitle("Sắp ra mắt");
                        break;
                }
                return true;
            }
        });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Còn rất nhiều phim hay bạn có muốn thoát?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý các tác vụ trước khi thoát ứng dụng
                        finish();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }
}