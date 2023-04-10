package com.example.moviett.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moviett.Fragment.ComingSoonFragment;
import com.example.moviett.Fragment.HomeFragment;
import com.example.moviett.Fragment.MovieSearchFragment;
import com.example.moviett.Fragment.TrendingFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new MovieSearchFragment();
            case 2:
                return new TrendingFragment();
            case 3:
                return new ComingSoonFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
