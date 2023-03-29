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
import com.example.moviett.ComingSoonFragment;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder>{

    private Context mContext;
    // private List<ComingSoon> mListComingSoon;

    private List<MovieApi> mListMovieApi;

    public ComingSoonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ComingSoonAdapter(ComingSoonFragment comingSoonFragment) {
    }

    public void setData(List<MovieApi> list) {
        this.mListMovieApi = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComingSoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coming_soon, parent, false);
        return new ComingSoonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonViewHolder holder, int position) {
//        ComingSoon comingSoon = mListComingSoon.get(position);
//        if (comingSoon == null) {
//            return;
//        }
//        holder.imgPosterFilm.setImageResource(comingSoon.getResourceId());
//        holder.tvFilmName.setText(comingSoon.getNameFilm());
//        holder.tvTrainerContent.setText(comingSoon.getContentFilm());


        MovieApi movie = mListMovieApi.get(position);

        // Lấy chuỗi ngày tháng năm
        String[] release = movie.getReleaseDate().split("-");
        String date, month, year;
        date = release[2];
        month = release[1];
        year = release[0];
        // Từ ngày tháng năm => thứ
//        Date datetime = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
//        datetime.getTime();

        if(movie == null) {
            return ;
        }
        Picasso.get()
                .load(movie.getPosterPath())
                .placeholder(R.drawable.loadinganimation)
                .error(R.drawable.loading)
                .into(holder.imgPosterFilm);

        holder.tvFilmName.setText(movie.getTitle());
        holder.tvTrainerContent.setText(movie.getOverview());
        holder.tvTypeFilm.setText(movie.getMediaType());
        holder.tvTitleDate.setText(date);
        holder.tvTitleMonth.setText(month);
        holder.tvYear.setText(year);
    }

    @Override
    public int getItemCount() {
       /* if(mListComingSoon != null) {
            return mListComingSoon.size();
        }
        return 0;*/

        if(mListMovieApi != null) {
            return mListMovieApi.size();
        }
        else {
            return 0;
        }
    }

    public class ComingSoonViewHolder extends RecyclerView.ViewHolder {

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
