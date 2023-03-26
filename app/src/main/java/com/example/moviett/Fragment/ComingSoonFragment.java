package com.example.moviett.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.moviett.Adapter.ComingSoonAdapter;
import com.example.moviett.Object.ComingSoon;
import com.example.moviett.R;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ComingSoonFragment extends Fragment {

    private RecyclerView mRcvComingSoon;
    private List<ComingSoon> list;

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

        ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getActivity());
        comingSoonAdapter.setData(getListComingSoon());

        mRcvComingSoon.setAdapter(comingSoonAdapter);


        return view;
    }

    private List<ComingSoon> getListComingSoon() {
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
    }
}