package com.example.moviett.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.Fragment.ComingSoonFragment;
import com.example.moviett.Object.ComingSoon;
import com.example.moviett.R;

import java.util.List;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder>{

    private Context mContext;
    private List<ComingSoon> mListComingSoon;

    public ComingSoonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ComingSoonAdapter(ComingSoonFragment comingSoonFragment) {
    }

    public void setData(List<ComingSoon> list) {
        this.mListComingSoon = list;
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
        ComingSoon comingSoon = mListComingSoon.get(position);
        if (comingSoon == null) {
            return;
        }

        holder.imgPosterFilm.setImageResource(comingSoon.getResourceId());
        holder.tvFilmName.setText(comingSoon.getNameFilm());
        holder.tvTrainerContent.setText(comingSoon.getContentFilm());
    }

    @Override
    public int getItemCount() {
        if(mListComingSoon != null) {
            return mListComingSoon.size();
        }
        return 0;
    }

    public class ComingSoonViewHolder extends RecyclerView.ViewHolder {

        // Khai báo thành phần trong item coming soon
        private ImageView imgPosterFilm;
        private TextView tvFilmName;
        private TextView tvTrainerContent;

        public ComingSoonViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPosterFilm = itemView.findViewById(R.id.img_posterFilm);
            tvFilmName = itemView.findViewById(R.id.tv_filmName);
            tvTrainerContent = itemView.findViewById(R.id.tv_trainerContent);
        }
    }
}
