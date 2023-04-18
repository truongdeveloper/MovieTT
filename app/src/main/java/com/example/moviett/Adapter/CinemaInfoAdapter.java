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
import com.example.moviett.ApiMovieDetail.CinemaMovie;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CinemaInfoAdapter extends RecyclerView.Adapter<CinemaInfoAdapter.ViewHolder>{

    private Context mContext;
    private List<CinemaMovie> cinemaMovies;

    public CinemaInfoAdapter(Context mContext, List<CinemaMovie> cinemaMovies) {
        this.mContext = mContext;
        this.cinemaMovies = cinemaMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cinema_info, parent, false);
        return new CinemaInfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CinemaMovie cinemaMovie = cinemaMovies.get(position);
        if(cinemaMovie == null) {
            return ;
        }
        holder.mImgTheater.setImageResource(cinemaMovie.getImgTheater());
        holder.mTvName.setText(cinemaMovie.getMovieTheaterName());
        holder.mTvTime.setText(cinemaMovie.getTime());
    }

    @Override
    public int getItemCount() {
        if(cinemaMovies != null) {
            return cinemaMovies.size();
        }
        else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgTheater;
        public TextView mTvName;
        public TextView mTvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgTheater = itemView.findViewById(R.id.imgTheater);
            mTvName = itemView.findViewById(R.id.cinema_name);
            mTvTime = itemView.findViewById(R.id.cinema_time_1);
        }
    }
}
