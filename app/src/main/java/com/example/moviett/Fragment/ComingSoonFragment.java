package com.example.moviett.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviett.Adapter.ComingSoonAdapter;
import com.example.moviett.Adapter.MyAdapter;
import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;

import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComingSoonFragment extends Fragment {

    private RecyclerView mRcvComingSoon;
    private ListMovie mListMovie;
     private Button loadMore;
     private ComingSoonAdapter comingSoonAdapter;
     int page = 1;


    public void setListMovie(ListMovie listMovie) {
        this.mListMovie = listMovie;
    }

    public static ComingSoonFragment getInstance() {
        ComingSoonFragment comingSoonFragment = new ComingSoonFragment();
        return comingSoonFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        mRcvComingSoon = view.findViewById(R.id.rcv_comingSoon);
        loadMore = view.findViewById(R.id.btnLoadMore);

        mRcvComingSoon.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        if (mListMovie != null) {
            // sắp xếp phim
            //Collections.sort(mListMovie.getUpcoming(), new SortByVote());
            //Collections.sort(mListMovie.getUpcoming(), new SortByName());
            comingSoonAdapter = new ComingSoonAdapter(getActivity(), new ComingSoonAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MovieApi movie) {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra("idMovie", movie.getId());
                    getActivity().startActivity(intent);
                }
            });
            comingSoonAdapter.setData(mListMovie.getUpcoming());
            mRcvComingSoon.setAdapter(comingSoonAdapter);
        }

        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                callApigetHome(page);
            }
        });


        return view;
    }

    // Sắp xếp theo điểm
    public class SortByVote implements Comparator<MovieApi> {
        public int compare(MovieApi movieApi1, MovieApi movieApi2) {
            if (movieApi1.getVoteAverage() > movieApi2.getVoteAverage()) {
                return 1;
            }
            return -1;
        }
    }

    // Sắp xếp theo tên
    public class SortByName implements Comparator<MovieApi> {
        public int compare(MovieApi movieApi1, MovieApi movieApi2) {
            return movieApi1.getTitle().compareTo(movieApi2.getTitle());
        }
    }

    public void callApigetHome(int page) {
        ApiService.apiService.getHomeData(page, "en").enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                ListMovie truong;
                truong = response.body();
                for(MovieApi movieApi : truong.getUpcoming()) {
                    mListMovie.getUpcoming().add(movieApi);
                }

                comingSoonAdapter.setData(mListMovie.getUpcoming());
                mRcvComingSoon.setAdapter(comingSoonAdapter);
            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Toast.makeText(getActivity(), "Không lấy được dữ liệu má ơi", Toast.LENGTH_LONG).show();

            }
        });
    }

}

