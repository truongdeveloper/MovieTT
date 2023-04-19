package com.example.moviett.Fragment;

import android.content.Intent;
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
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComingSoonFragment extends Fragment {

    private RecyclerView mRcvComingSoon;
    private ListMovie mListMovie;


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

        mRcvComingSoon.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        if (mListMovie != null) {
            ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getActivity(), new ComingSoonAdapter.OnItemClickListener() {
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
//        callApiComingSoon();
        return view;
    }

}