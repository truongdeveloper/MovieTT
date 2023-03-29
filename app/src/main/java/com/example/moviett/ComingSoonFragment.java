package com.example.moviett;

import android.graphics.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.moviett.Adapter.ComingSoonAdapter;
import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.MainActivity;
import com.example.moviett.MyAdapter;
import com.example.moviett.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComingSoonFragment extends Fragment {

    private RecyclerView mRcvComingSoon;
    private ListMovie mListMovie;

    public ComingSoonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coming_soon, container, false);

        mRcvComingSoon = view.findViewById(R.id.rcv_comingSoon);
        mRcvComingSoon.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        /*ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getActivity());
        comingSoonAdapter.setData(getListComingSoon());

        mRcvComingSoon.setAdapter(comingSoonAdapter);*/

        callApiComingSoon();


        return view;
    }

    private void callApiComingSoon() {
        ApiService.apiService.getHomeData("en").enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                Log.i("Thong bao", "Success");
                mListMovie = response.body();
                ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getActivity());
                comingSoonAdapter.setData(mListMovie.getTrandingMovies());
                mRcvComingSoon.setAdapter(comingSoonAdapter);

                if (mListMovie != null && mListMovie.isSuccess()) {

                }
            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Log.i("Thong bao", "Success");
            }
        });
    }

    /*private List<ComingSoon> getListComingSoon() {
        List<ComingSoon> list = new ArrayList<>();
        list.add(new ComingSoon(R.drawable.poster_1, "Film Name 1", "Người sói Hugh Jackman vào vai người cha đang tìm cách chữa lành viết thương tâm lý của con trai khi cậu gặp vấn đề nghiêm trọng và không còn cảm thấy hứng thú với cuộc sống từ lúc cha mẹ ly dị."));
        list.add(new ComingSoon(R.drawable.poster_2, "Film Name 2", "Người sói Hugh Jackman vào vai người cha đang tìm cách chữa lành viết thương tâm lý của con trai khi cậu gặp vấn đề nghiêm trọng và không còn cảm thấy hứng thú với cuộc sống từ lúc cha mẹ ly dị."));
        list.add(new ComingSoon(R.drawable.poster_3, "Film Name 3", "Người sói Hugh Jackman vào vai người cha đang tìm cách chữa lành viết thương tâm lý của con trai khi cậu gặp vấn đề nghiêm trọng và không còn cảm thấy hứng thú với cuộc sống từ lúc cha mẹ ly dị."));
        list.add(new ComingSoon(R.drawable.poster_4, "Film Name 4", "Content Film Name 4"));
        list.add(new ComingSoon(R.drawable.poster_5, "Film Name 5", "Content Film Name 5"));
        list.add(new ComingSoon(R.drawable.poster_6, "Film Name 6", "Content Film Name 6"));
        list.add(new ComingSoon(R.drawable.poster_7, "Film Name 7", "Content Film Name 7"));
        list.add(new ComingSoon(R.drawable.poster_8, "Film Name 8", "Content Film Name 8"));
        return list;
    }*/
}