package com.example.moviett.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//Thư viện Slider
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.moviett.Adapter.MyAdapter;
import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.R;

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
        ApiService.apiService.getHomeData("en").enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                mListMovie = response.body();
                MyAdapter movieAdapter = new MyAdapter(getActivity());
                movieAdapter.setData(mListMovie.getTopRatedMovies());
                mRecyclerView.setAdapter(movieAdapter);

                //Thêm hình ảnh,title, ScaleTypes(Làm cho hình vừa với view) cho Slider
                List<SlideModel> imageList  = new ArrayList<>();

                if (mListMovie != null && mListMovie.getTopRatedMovies() != null) {
                    for(int i = 0; i < 10; i++){
                        imageList.add(new SlideModel(mListMovie.getTopRatedMovies().get(i).getBackdropPath(), mListMovie.getTopRatedMovies().get(i).getTitle(), null));
                    }
                    imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);
                } else {

                }
            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Toast.makeText(getActivity(), "Không lấy được dữ liệu má ơi", Toast.LENGTH_LONG).show();

            }
        });
    }
}