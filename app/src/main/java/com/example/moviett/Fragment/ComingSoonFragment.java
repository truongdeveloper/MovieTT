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

import com.example.moviett.Adapter.ComingSoonAdapter;
import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.MainActivity;
import com.example.moviett.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComingSoonFragment extends Fragment {

    private RecyclerView mRcvComingSoon;
    private ListMovie mListMovie;

    private MainActivity mMainActivity;

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
                //Log.i("Thong bao", "Success");
                mListMovie = response.body();
                ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getActivity(),
                        new ComingSoonAdapter.ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {

                            }
                        });
                comingSoonAdapter.setData(mListMovie.getUpcoming());
                mRcvComingSoon.setAdapter(comingSoonAdapter);
                if (mListMovie != null && mListMovie.isSuccess()) {

                }
            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {
                Log.i("Thong bao", "False");
            }
        });
    }
}