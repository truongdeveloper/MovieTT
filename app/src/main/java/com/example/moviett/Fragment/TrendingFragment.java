package com.example.moviett.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.moviett.Adapter.MyAdapter;
import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingFragment extends Fragment {

    private RecyclerView recyclerView;
    private static ListMovie mListMovie;

    public void setListMovie(ListMovie listMovie) {
        this.mListMovie = listMovie;
    }
    public static TrendingFragment getInstance() {
        TrendingFragment trendingFragment = new TrendingFragment();
        return trendingFragment;
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle save) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        recyclerView = view.findViewById(R.id.rcv_trendingMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        if (mListMovie != null) {
            MyAdapter movieAdapter = new MyAdapter(getActivity(), new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MovieApi movieApi) {
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                    intent.putExtra("idMovie", movieApi.getId());
                    getActivity().startActivity(intent);
                }
            });
            movieAdapter.setData(mListMovie.getTrandingMovies());
            recyclerView.setAdapter(movieAdapter);
        }

//        callApigetHome();
        return view;
    }

//    public void callApigetHome() {
//        ApiService.apiService.getHomeData("en").enqueue(new Callback<ListMovie>() {
//            @Override
//            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
//                mListMovie = response.body();
//                MyAdapter movieAdapter = new MyAdapter(getActivity(), new MyAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(MovieApi movieApi) {
//                        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
//                        intent.putExtra("idMovie", movieApi.getId());
//                        getActivity().startActivity(intent);
//                    }
//                });
//                movieAdapter.setData(mListMovie.getTrandingMovies());
//                recyclerView.setAdapter(movieAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ListMovie> call, Throwable t) {
//                Toast.makeText(getActivity(), "Không lấy được dữ liệu má ơi", Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
}