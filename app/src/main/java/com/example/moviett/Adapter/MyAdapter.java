package com.example.moviett.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
//    private List<Movie> mListMovie;
    private List<MovieApi> mListMovieApi;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<MovieApi> movie) {
        this.mListMovieApi = movie;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieApi movie = mListMovieApi.get(position);
        if(movie == null) {
            return ;
        }
        Picasso.get()
                .load(movie.getPosterPath())
                .placeholder(R.drawable.loadinganimation)
                .error(R.drawable.loading)
                .into(holder.posterImg);
        holder.mTextView.setText(movie.getTitle());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("idMovie", movie.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListMovieApi != null) {
            return mListMovieApi.size();
        }
        else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView posterImg;

        public ViewHolder(View itemView) {
            super(itemView);
            posterImg = itemView.findViewById(R.id.movie_cover_image);
            mTextView = itemView.findViewById(R.id.movie_title_text);
        }
    }
}
