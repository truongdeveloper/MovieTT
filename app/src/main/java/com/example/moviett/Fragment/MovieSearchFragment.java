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

import java.util.ArrayList;
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
                // lọc phim
                List<SearchMovieItem> searchMovies = new ArrayList<>();
//                for (int i = 0; i < mListMovie.getData().size(); i++) {
//                    if (mListMovie.getData().get(i).isAdult()) {
//                        continue;
//                    } else {
//                        SearchMovieItem searchMovieItem = new SearchMovieItem(
//                                mListMovie.getData().get(i).isAdult(),
//                                mListMovie.getData().get(i).getBackdrop_path(),
//                                mListMovie.getData().get(i).getGenre_ids(),
//                                mListMovie.getData().get(i).getId(),
//                                mListMovie.getData().get(i).getOriginal_language(),
//                                mListMovie.getData().get(i).getOriginal_title(),
//                                mListMovie.getData().get(i).getOverview(),
//                                mListMovie.getData().get(i).getPopularity(),
//                                mListMovie.getData().get(i).getPoster_path(),
//                                mListMovie.getData().get(i).getRelease_date(),
//                                mListMovie.getData().get(i).getTitle(),
//                                mListMovie.getData().get(i).isVideo(),
//                                mListMovie.getData().get(i).getVote_average(),
//                                mListMovie.getData().get(i).getVote_count()
//                        );
//                        searchMovies.add(searchMovieItem);
//                    }
//                }

                for (SearchMovieItem searchMovieItem : mListMovie.getData()) {
                    if (searchMovieItem.isAdult()) {
                        continue;
                    } else {
                        searchMovies.add(searchMovieItem);
                    }
                }
                SearchMovie searchMovie = new SearchMovie(mListMovie.isSuccess(), searchMovies);

                if(searchMovie!=null) {
                    SearchAdapter searchAdapter = new SearchAdapter(getActivity(),new SearchAdapter.OnItemClickListener(){
                        @Override
                        public void onItemClick(SearchMovieItem movieApi) {
                            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                            intent.putExtra("idMovie", movieApi.getId());
                            getActivity().startActivity(intent);
                        }
                    });
                    searchAdapter.setData(searchMovie.getData());
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