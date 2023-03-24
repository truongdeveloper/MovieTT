package com.example.moviett;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
//Thư viện Slider
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    ImageSlider imageSlider;

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

        List<SlideModel> imageList  = new ArrayList<>();

        imageList.add(new SlideModel("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg", "The animal population decreased by 58 percent in 42 years.", null));
        imageList.add(new SlideModel("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg", "Elephants and tigers may become extinct.",null));
        imageList.add(new SlideModel("https://bit.ly/3fLJf72", "And people do that.", null));

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);
        mAdapter = new MyAdapter(getActivity());
        //Seup layout cho RecycleView
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //data đổ vào Apdapter
        mAdapter.setData(getListMovie());
        mRecyclerView.setAdapter(mAdapter);



        return view;
    }


    private List<Movie> getListMovie() {
        List<Movie> movie = new ArrayList<>();
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.5f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.5f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.5f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.7f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.7f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.7f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.7f));
        movie.add(new Movie("https://image.tmdb.org/t/p/w500/gB2qtSWf39MUmZX5rE9IQ6y4bOi.jpg","The Magician's Elephant",7.7f));
        return movie;
    }


}