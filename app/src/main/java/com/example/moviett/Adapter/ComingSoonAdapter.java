package com.example.moviett.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiContainer.ListMovie;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.ApiMovieDetail.MovieDetail;
import com.example.moviett.ApiMovieDetail.Result;
import com.example.moviett.MovieDetailActivity;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder>{
    private Context mContext;
    private List<MovieApi> mListMovieApi;

    private ComingSoonAdapter.OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(MovieApi movie);
    }

    public ComingSoonAdapter(Context mContext, ComingSoonAdapter.OnItemClickListener listener) {
        this.mContext = mContext;
        this.listener = (ComingSoonAdapter.OnItemClickListener) listener;
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
        MovieApi movie = mListMovieApi.get(position);
        // Lấy chuỗi ngày tháng năm
        String[] release = movie.getReleaseDate().split("-");
        String date, month, year;
        date = release[2];
        month = release[1];
        year = release[0];

        if(movie == null) {
            return ;
        }
        Picasso.get()
                .load(movie.getBackdropPath())
                .placeholder(R.drawable.loadinganimation)
                .error(R.drawable.loading)
                .into(holder.imgPosterFilm);

        holder.tvFilmName.setText(movie.getTitle());
        holder.tvTrainerContent.setText(movie.getOverview());
        holder.tvTypeFilm.setText(movie.getMediaType());
        holder.tvTitleDate.setText(date);
        holder.tvTitleMonth.setText(month);
        holder.tvYear.setText(year);
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

//          thực hiện ánh xạ view
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
