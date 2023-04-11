package com.example.moviett.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewCinemaAdapter extends FragmentPagerAdapter  {
    public ViewCinemaAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                CinemaUpcomingFragment cinemaUpcomingFragment = new CinemaUpcomingFragment();
                return cinemaUpcomingFragment;
            default:
                CinemaNowFragment cinemaNowFragment = new CinemaNowFragment();
                return cinemaNowFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
