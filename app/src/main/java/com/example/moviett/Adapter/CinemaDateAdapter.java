package com.example.moviett.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.ApiMovieDetail.CinemaCalender;
import com.example.moviett.R;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class CinemaDateAdapter extends RecyclerView.Adapter<CinemaDateAdapter.ViewHolder>{

    private List<CinemaCalender> calendars;
    private Context context;
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
