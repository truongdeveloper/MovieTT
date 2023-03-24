package com.example.moviett;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> mListMovie;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Movie> movie) {
        this.mListMovie = movie;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mListMovie.get(position);
        if(movie == null) {
            return ;
        }
        Picasso.get()
                .load(movie.getSrcImg())
                .placeholder(R.drawable.loadinganimation)
                .error(R.drawable.loading)
                .into(holder.posterImg);
        holder.mTextView.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mListMovie != null) {
            return mListMovie.size();
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
