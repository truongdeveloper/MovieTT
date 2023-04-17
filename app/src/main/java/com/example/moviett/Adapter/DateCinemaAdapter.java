package com.example.moviett.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.R;

import java.util.Calendar;
import java.util.List;

public class DateCinemaAdapter extends RecyclerView.Adapter<DateCinemaAdapter.ViewHolder>{

    private List<String> calendars;
    private Context context;

    public DateCinemaAdapter(List<String> calendars, Context context) {
        this.calendars = calendars;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new DateCinemaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String calendar = calendars.get(position);
        if (calendar == null) {
            return;
        }
        holder.mDate.setText(calendar);
        //holder.mWeekdays.setText(calendar.getTime().toString());
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
