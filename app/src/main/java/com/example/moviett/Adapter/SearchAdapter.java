package com.example.moviett.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.ApiContainer.SearchMovie;
import com.example.moviett.ApiContainer.SearchMovieItem;
import com.example.moviett.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private List<SearchMovieItem> mListMovieApi;
    private SearchAdapter.OnItemClickListener listener;

    public SearchAdapter(Context mContext, SearchAdapter.OnItemClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(SearchMovieItem movieApi);
    }

    public void setData(List<SearchMovieItem> movie) {
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
        SearchMovieItem movie = mListMovieApi.get(position);
        if(movie == null) {
            return ;
        }
        Picasso.get()
                .load(movie.getPoster_path())
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
