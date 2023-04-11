package com.example.moviett.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.Fragment.CinemaFragment;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {
    private Context mContext;
    private List<MovieApi> mListMovieApi;

    private CinemaAdapter.OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(MovieApi movieApi);
    }

    public CinemaAdapter(Context mContext, List<MovieApi> mListMovieApi) {
        this.mContext = mContext;
        this.mListMovieApi = mListMovieApi;
    }

    public void setData(List<MovieApi> movie) {
        this.mListMovieApi = movie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new CinemaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(movie);
                }
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
