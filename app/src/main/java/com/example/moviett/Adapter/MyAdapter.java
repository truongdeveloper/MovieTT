package com.example.moviett.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.ApiMovieDetail.Result;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieApi> mListMovieApi;
    private MyAdapter.OnItemClickListener listener;

    private OnItemLongClickListener longClickListener;
    public interface OnItemClickListener {
        void onItemClick(MovieApi movieApi);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(MovieApi movieApi);
    }

    public MyAdapter(Context mContext, MyAdapter.OnItemClickListener listener, MyAdapter.OnItemLongClickListener listenerQuickView ) {
        this.mContext = mContext;
        this.listener = listener;
        this.longClickListener = listenerQuickView;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(movie);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickListener != null) {
                    longClickListener.onItemLongClick(movie);
                }
                return true;
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
