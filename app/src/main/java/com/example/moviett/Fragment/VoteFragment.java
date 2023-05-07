package com.example.moviett.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviett.Adapter.ComingSoonAdapter;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;


public class VoteFragment extends Fragment {

    private RecyclerView mRcvVote;
    private ListMovie mListMovie;


    public void setListMovie(ListMovie listMovie) {
        this.mListMovie = listMovie;
    }

    public static VoteFragment getInstance() {
        VoteFragment voteFragment = new VoteFragment();
        return voteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote, container, false);
        mRcvVote = view.findViewById(R.id.rcv_vote);

        mRcvVote.setLayoutManager(new GridLayoutManager(getActivity(), 1));

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
            mRcvVote.setAdapter(comingSoonAdapter);
        }
        return view;
    }
}