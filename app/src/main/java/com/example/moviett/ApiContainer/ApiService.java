package com.example.moviett.ApiContainer;

import android.graphics.Movie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yy").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://movie-pro.onrender.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("home")
    Call<ListMovie> getHomeData(@Query("language") String language);

    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") int id);

}
