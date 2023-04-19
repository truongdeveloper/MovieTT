package com.example.moviett.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.Adapter.MyAdapter;
import com.example.moviett.Adapter.SearchAdapter;
import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.ApiContainer.SearchMovie;
import com.example.moviett.ApiContainer.SearchMovieItem;
import com.example.moviett.ApiMovieDetail.Similar;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearchFragment extends Fragment {

    public MovieSearchFragment() {
        // Required empty public constructor
    }

    private SearchView mSearchView;
    SearchMovie mListMovie;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_search, container, false);

        mSearchView = view.findViewById(R.id.search_view);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callApiSearchMovie(query);
                hideKeyboard();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý khi nhập liệu vào ô tìm kiếm
                callApiSearchMovie(newText);
                return false;
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchView.clearFocus();
            }
        });


        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard();
                }
            }
        });

        return view;
    }

    public void callApiSearchMovie(String searchText) {
        ApiService.apiService.getSearchMovie(searchText,1 , "vi").enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(Call<SearchMovie> call, Response<SearchMovie> response) {
                mListMovie = response.body();
                if(mListMovie!=null) {
                    SearchAdapter searchAdapter = new SearchAdapter(getActivity(),new SearchAdapter.OnItemClickListener(){
                        @Override
                        public void onItemClick(SearchMovieItem movieApi) {
                             Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                            intent.putExtra("idMovie", movieApi.getId());
                            getActivity().startActivity(intent);

                        }
                    });
                    searchAdapter.setData(mListMovie.getData());
                    recyclerView.setAdapter(searchAdapter);

                }else{
                    Toast.makeText(getActivity(), "Không lấy được dữ liệu", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchMovie> call, Throwable t) {
                Toast.makeText(getActivity(), "Không lấy được dữ liệu", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
    }

}