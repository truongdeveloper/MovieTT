package com.example.moviett.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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
import com.example.moviett.CinemaDetailActivity;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getNowCinema() {
        List<MovieApi> movieListNew = new ArrayList<>();
        if (mListMovie != null) {
            // Lọc lấy thông tin phim trong tháng
            List<MovieApi> movieInMonth = mListMovie.getNowPlayingMovies();
            if (movieInMonth != null) {
                for(int i = 0; i < movieInMonth.size(); i++) {
                    MovieApi movieApi = movieInMonth.get(i);
                    // Ngày hiện tại
                    LocalDate currentDate = LocalDate.now();
                    // Ngày phát hành của phim
                    String[] release = movieApi.getReleaseDate().split("-");
                    String date, month, year;
                    month = release[1];
                    year = release[0];
                    // Kiểm tra ngày phát hành phim có trong tháng không
                    if (Integer.parseInt(year) == currentDate.getYear() && Integer.parseInt(month) == currentDate.getMonthValue()) {
                        movieListNew.add(movieApi);
                    }
                }
            }
            CinemaAdapter cinemaAdapter = new CinemaAdapter(getActivity(), new CinemaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MovieApi movieApi) {
                    Intent intent = new Intent(getActivity(), CinemaDetailActivity.class);
                    intent.putExtra("idMovie", movieApi.getId());
                    getActivity().startActivity(intent);
                }
            });
            cinemaAdapter.setData(movieListNew);
            mRcvNowCinema.setAdapter(cinemaAdapter);
        }
    }

    public void getUpcomingCinema() {
        if (mListMovie != null) {
            CinemaAdapter cinemaAdapter = new CinemaAdapter(getActivity(), new CinemaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MovieApi movieApi) {
                    Intent intent = new Intent(getActivity(), CinemaDetailActivity.class);
                    intent.putExtra("idMovie", movieApi.getId());
                    getActivity().startActivity(intent);
                }
            });
            cinemaAdapter.setData(mListMovie.getUpcoming());
            mRcvUpcomingCinema.setAdapter(cinemaAdapter);
        }
    }
}