package com.example.moviett.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.Fragment.ComingSoonFragment;
import com.example.moviett.Fragment.HomeFragment;
import com.example.moviett.Fragment.MovieSearchFragment;
import com.example.moviett.Fragment.TrendingFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ListMovie mListMovie;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior,  ListMovie listMovie) {
        super(fm, behavior);
        mListMovie = listMovie;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                MovieSearchFragment movieSearchFragment = new MovieSearchFragment();
//                movieSearchFragment.setListMovie(mListMovie);
                return movieSearchFragment;
            case 2:
                TrendingFragment trendingFragment = new TrendingFragment();
                trendingFragment.setListMovie(mListMovie);
                return trendingFragment;
            case 3:
                ComingSoonFragment comingSoonFragment = new ComingSoonFragment();
                comingSoonFragment.setListMovie(mListMovie);
                return comingSoonFragment;
            default:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setListMovie(mListMovie);
                return homeFragment;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
