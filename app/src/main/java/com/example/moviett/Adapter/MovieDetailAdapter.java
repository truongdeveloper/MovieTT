package com.example.moviett.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.R;

public class MovieDetailAdapter {
    public static class ComingSoonViewHolder extends RecyclerView.ViewHolder {

        // Khai báo thành phần trong item coming soon
        private ImageView imgPosterFilm;
        private TextView tvFilmName;
        private TextView tvTrainerContent;

        private TextView tvTypeFilm;
        private TextView tvTitleDate;
        private TextView tvTitleMonth;

        private TextView tvYear;
        public ComingSoonViewHolder(@NonNull View itemView) {
            super(itemView);

//            thực hiện ánh xạ view
            imgPosterFilm = itemView.findViewById(R.id.img_posterFilm);
            tvFilmName = itemView.findViewById(R.id.tv_filmName);
            tvTrainerContent = itemView.findViewById(R.id.tv_trainerContent);
            tvTypeFilm = itemView.findViewById(R.id.tv_typeFilm);
            tvTitleDate = itemView.findViewById(R.id.tv_titleDate);
            tvTitleMonth = itemView.findViewById(R.id.tv_titleMonth);
            tvYear = itemView.findViewById(R.id.tv_year);
        }
    }
}
