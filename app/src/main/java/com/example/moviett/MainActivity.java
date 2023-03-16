package com.example.moviett;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_home: {
//                        // Handle home click
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                        finish();
//                        return true;
//                    }
//                    case R.id.navigation_search: {
//                        // Handle favorites click
//                        Intent intent = new Intent(getApplicationContext(), MovieSearch.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                        finish();
//                        return true;
//                    }
//                    case R.id.navigation_trending: {
//                        // Handle settings click
//                        Intent intent = new Intent(getApplicationContext(), Trending.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                        finish();
//                        return true;
//                    }
//                    case R.id.navigation_coming: {
//                        // Handle settings click
//                        Intent intent = new Intent(getApplicationContext(), ComingSoon.class);
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                        finish();
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });

        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

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
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.navigation_search).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.navigation_trending).setChecked(true);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.navigation_coming).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_search:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_trending:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.navigation_coming:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }
}