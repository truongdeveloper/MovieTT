package com.example.moviett.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiMovieDetail.CinemaCalender;
import com.example.moviett.ApiMovieDetail.CinemaMovie;
import com.example.moviett.CinemaDetailActivity;
import com.example.moviett.Fragment.CinemaFragment;
import com.example.moviett.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CinemaDateAdapter extends RecyclerView.Adapter<CinemaDateAdapter.ViewHolder>{

    private List<CinemaCalender> calendars;
    private Context context;

    public List<CinemaMovie> ListCinemaMovie() {
        // Danh sách thông tin (rạp, thời gian chiếu)
        List<CinemaMovie> cinemaMovies = new ArrayList<>();
        cinemaMovies.add(new CinemaMovie(1, "CGV Aeon Long Biên", "19:00 - 20:30 - 21:30", R.drawable.cgv));
        cinemaMovies.add(new CinemaMovie(2, "CGV Rice City", "18:00 - 19:00 - 20:30", R.drawable.cgv));
        cinemaMovies.add(new CinemaMovie(3, "CGV Vincom Nguyễn Chí Thanh", "11:00 - 12:00 - 13:30", R.drawable.cgv));
        cinemaMovies.add(new CinemaMovie(4, "CGV Skylake Phạm Hùng", "17:00 - 18:00 - 19:00 - 20:30", R.drawable.cgv));
        cinemaMovies.add(new CinemaMovie(5, "CGV Vincom Bắc Từ Liêm", "10:50 - 13:00 - 20:30", R.drawable.cgv));
        cinemaMovies.add(new CinemaMovie(6, "Lotte Hà Đông", "19:00 - 21:00 - 22:30", R.drawable.lotte));
        cinemaMovies.add(new CinemaMovie(7, "Lotte Cinema Thăng Long", "19:00 - 22:00", R.drawable.lotte));
        cinemaMovies.add(new CinemaMovie(8, "Lotte Cinema Long Biên", "19:00 - 22:00", R.drawable.lotte));
        cinemaMovies.add(new CinemaMovie(9, "Lotte West Lake", "19:00 - 22:00", R.drawable.lotte));
        cinemaMovies.add(new CinemaMovie(10, "Lotte KosMo", "19:00 - 22:00", R.drawable.lotte));
        cinemaMovies.add(new CinemaMovie(11, "BHD Star The Garden", "19:30 - 21:00 - 23:00", R.drawable.bhd));
        cinemaMovies.add(new CinemaMovie(12, "BHD Star Phạm Ngọc Thạch", "19:00 - 20:00 - 21:30", R.drawable.bhd));
        cinemaMovies.add(new CinemaMovie(13, "BHD Star Discovery", "19:00 - 21:00 - 22:30", R.drawable.bhd));
        return cinemaMovies;
    }
    private CinemaDateAdapter.OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(CinemaCalender calendar);
    }

    public CinemaDateAdapter(List<CinemaCalender> calendars, Context context, CinemaDateAdapter.OnItemClickListener listener) {
        this.calendars = calendars;
        this.context = context;
        this.listener = (CinemaDateAdapter.OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new CinemaDateAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CinemaCalender cinemaCalender = calendars.get(position);
        if (cinemaCalender == null) {
            return;
        }
        holder.mDate.setText(String.valueOf(cinemaCalender.getDay()));
        holder.mWeekdays.setText(cinemaCalender.getDayOfWeek());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(cinemaCalender);
                    Intent intent = new Intent(context, CinemaDetailActivity.class);
                    intent.putExtra("my_list", (Serializable) ListCinemaMovie());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(calendars != null) {
            return calendars.size();
        }
        else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate, mWeekdays;

        public ViewHolder(View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.tvDate);
            mWeekdays = itemView.findViewById(R.id.tvWeekDays);
        }
    }
}
