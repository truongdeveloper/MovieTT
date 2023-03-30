package com.example.moviett.ApiMovieDetail;

import com.example.moviett.ApiContainer.ApiService;
import com.example.moviett.ApiContainer.ListMovie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMovieDetailService {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yy").create();

    // https://movie-pro.onrender.com/api/v1/movie/19995?language=vi
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://movie-pro.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/v1/movie/19995")
    Call<ListMovie> getHomeData(@Query("language") String language);
}
