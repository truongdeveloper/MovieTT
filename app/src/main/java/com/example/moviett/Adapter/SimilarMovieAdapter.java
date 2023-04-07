package com.example.moviett.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiMovieDetail.Result;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder> {
    private List<Result> similarMovies;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Result similarMovie);
    }

    public SimilarMovieAdapter(List<Result> similarMovies, OnItemClickListener listener) {
        this.similarMovies = similarMovies;
        this.listener = (OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public SimilarMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similar_movie, parent, false);
        return new SimilarMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarMovieViewHolder holder, int position) {
        Result similarMovie = similarMovies.get(position);
        // Load image using Glide library
        Picasso.get()
                .load(similarMovie.getPoster_path())
                .placeholder(R.drawable.loadinganimation)
                .error(R.drawable.loading)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(similarMovie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return similarMovies.size();
    }

    public class SimilarMovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public SimilarMovieViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_posterFilm);
        }
    }
}

