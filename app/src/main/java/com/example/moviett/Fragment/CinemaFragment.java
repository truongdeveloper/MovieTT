package com.example.moviett.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviett.Adapter.CinemaAdapter;
import com.example.moviett.Adapter.MyAdapter;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;

public class CinemaFragment extends Fragment {

    private RecyclerView mRcvNowCinema, mRcvUpcomingCinema;
    private static ListMovie mListMovie;
    private View view;

    public void setListMovie(ListMovie listMovie) {
        this.mListMovie = listMovie;
    }
    public static CinemaFragment getInstance() {
        CinemaFragment cinemaFragment = new CinemaFragment();
        return cinemaFragment;
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cinema, container, false);

        // Phim đang chiếu
        mRcvNowCinema = view.findViewById(R.id.rcvNowComing);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRcvNowCinema.setLayoutManager(layoutManager);
        getNowCinema();

        // Phim sắp chiếu
        mRcvUpcomingCinema = view.findViewById(R.id.rcvUpcoming);
        mRcvUpcomingCinema.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getUpcomingCinema();

        return view;
    }

    public void getNowCinema() {
        if (mListMovie != null) {
            CinemaAdapter cinemaAdapter = new CinemaAdapter(getActivity(), new CinemaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MovieApi movieApi) {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra("idMovie", movieApi.getId());
                    getActivity().startActivity(intent);
                }
            });
            cinemaAdapter.setData(mListMovie.getNowPlayingMovies());
            mRcvNowCinema.setAdapter(cinemaAdapter);
        }
    }

    public void getUpcomingCinema() {
        if (mListMovie != null) {
            CinemaAdapter cinemaAdapter = new CinemaAdapter(getActivity(), new CinemaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MovieApi movieApi) {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra("idMovie", movieApi.getId());
                    getActivity().startActivity(intent);
                }
            });
            cinemaAdapter.setData(mListMovie.getUpcoming());
            mRcvUpcomingCinema.setAdapter(cinemaAdapter);
        }
    }
}