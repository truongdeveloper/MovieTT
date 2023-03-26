package com.example.moviett;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Thư viện Slider
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ListMovie mListMovie;
    ImageSlider imageSlider;
    List<MovieApi> mMovieApi = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Slider Image
        imageSlider = view.findViewById(R.id.image_slider);
        mRecyclerView = view.findViewById(R.id.home_recycler_view);


        //Setup layout cho RecycleView
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //data đổ vào Adapter
        callApigetHome();
        return view;
    }

    public void callApigetHome() {
        ApiService.apiService.getHomeData("vi").enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                mListMovie = response.body();
                MyAdapter movieAdapter = new MyAdapter(getActivity());
                movieAdapter.setData(mListMovie.getTopRatedMovies());
                mRecyclerView.setAdapter(movieAdapter);

                //Thêm hình ảnh,title, ScaleTypes(Làm cho hình vừa với view) cho Slider
                List<SlideModel> imageList  = new ArrayList<>();
//                imageList.add(new SlideModel("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg", "The animal population decreased by 58 percent in 42 years.", null));
//                imageList.add(new SlideModel("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg", "Elephants and tigers may become extinct.",null));
//                imageList.add(new SlideModel("https://bit.ly/3fLJf72", "And people do that.", null));


                if (mListMovie != null && mListMovie.getTopRatedMovies() != null) {
                    for(int i = 0; i < 10; i++){
                        imageList.add(new SlideModel(mListMovie.getTopRatedMovies().get(i).getBackdropPath(), mListMovie.getTopRatedMovies().get(i).getTitle(), null));
                    }
                    imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);
                } else {
                    Log.e("ddd", "nos bij rooxng");
                }


            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {

            }
        });
    }


}