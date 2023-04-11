package com.example.moviett;

import static com.example.moviett.ApiContainer.ApiService.apiService;

import static java.lang.System.in;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviett.Adapter.ComingSoonAdapter;
import com.example.moviett.Adapter.MyAdapter;
import com.example.moviett.Adapter.SimilarMovieAdapter;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.ApiMovieDetail.Cast;
import com.example.moviett.ApiMovieDetail.Credit;
import com.example.moviett.ApiMovieDetail.Data;
import com.example.moviett.ApiMovieDetail.Genres;
import com.example.moviett.ApiMovieDetail.MovieDetail;
import com.example.moviett.ApiMovieDetail.Result;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView imgBackdropImage;
    private TextView tvMovieTitle, tvIbm, tvGenre, tvReleaseDate, tvDescription, tvActors;
    private Button btnWatch;
    private RecyclerView rcv_similarMovie;
    NestedScrollView scrollView;
    WebView webView;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Thêm nút back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Chuyển Actioon bar thành Chi tiết phim
        actionBar.setTitle("Chi tiết phim");

        imgBackdropImage = findViewById(R.id.backdrop_image);
        tvMovieTitle = findViewById(R.id.movie_title);
        tvIbm = findViewById(R.id.ibm);
        tvGenre = findViewById(R.id.genre);
        tvReleaseDate = findViewById(R.id.release_date);
        tvDescription = findViewById(R.id.movie_description);
        tvActors = findViewById(R.id.cast);
        btnWatch = findViewById(R.id.watch_movie_button);
        rcv_similarMovie = findViewById(R.id.rcv_similar_movies);

        Intent intent = getIntent();
        int idMovie = intent.getIntExtra("idMovie", 1);

        rcv_similarMovie.setLayoutManager(new GridLayoutManager(MovieDetailActivity.this, 2));

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);


        callApigetHome(idMovie);
    }

    public void callApigetHome(int idMovie) {

        Call<MovieDetail> call = apiService.getMovieDetail(idMovie, "vi");
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                MovieDetail movie = response.body();
                if (movie != null && movie.isSuccess()) {
                    Picasso.get()
                            .load(movie.getData().getBackdropPath())
                            .placeholder(R.drawable.loadinganimation)
                            .error(R.drawable.loading)
                            .into(imgBackdropImage);
                    tvMovieTitle.setText(movie.getData().getTitle());
                    tvIbm.setText(String.valueOf(df.format(movie.getData().getVote_average())));
                    // Thể loại phim
                    String genre = "";
                    List<Genres> genres = movie.getData().getGenres();
                    int lengthGenre = genres.size();
                    for(int i = 0; i < lengthGenre; i++) {
                        genre += genres.get(i).getName() + ", ";
                    }
                    if(genre.length() > 2){
                        tvGenre.setText(genre.substring(0, genre.length()-2));
                    }
                    // Ngày phát hành
                    String[] release = movie.getData().getRelease_date().split("-");
                    if(release.length > 1){
                        tvReleaseDate.setText(release[2]+"-"+release[1]+"-"+release[0]);
                    }
                    // Mô tả ngắn
                    tvDescription.setText(movie.getData().getOverview());
                    // Diễn viên
                    String actors = "";
                    List<Cast> castList = movie.getCredits().getCast();
                    int lengthActor = castList.size();
                    for(int i = 0; i < lengthActor; i++) {
                        actors += castList.get(i).getName() + ", ";
                    }
                    if(actors.length() > 2){
                        tvActors.setText(actors.substring(0, actors.length()-2));
                    }
                    //Hiện Trailer Film
                    if(movie.getVideos().getResults().get(0).getKey() !=null){
                        String videoId = movie.getVideos().getResults().get(0).getKey();
                        String html = String.format("<html ><body style=\"background-color:\"#0000\"><center><iframe width=\"320\" height=\"180\" src=\"https://www.youtube.com/embed/%s\" title=\"Trailer\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></center></body></html>", videoId);
                        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
                    }


                    SimilarMovieAdapter similarMovieAdapter = new SimilarMovieAdapter(movie.getSimilar().getResults(), new SimilarMovieAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Result similarMovie) {
                            callApigetHome(similarMovie.getId());
                            scrollView = findViewById(R.id.scrollMovie);
                            scrollView.smoothScrollTo(0, 0);
                        }
                    });
                    rcv_similarMovie.setAdapter(similarMovieAdapter);

                    btnWatch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Dialog dialog = new Dialog(MovieDetailActivity.this);
                            dialog.setContentView(R.layout.dialog_watch_movie);

                            WebView webViewMovie = dialog.findViewById(R.id.webViewMovie);
                            webViewMovie.getSettings().setJavaScriptEnabled(true);
                            webViewMovie.getSettings().setDomStorageEnabled(true);
                            webViewMovie.getSettings().setAllowFileAccess(true);
                            webViewMovie.getSettings().setAllowContentAccess(true);
                            webViewMovie.getSettings().setAllowFileAccessFromFileURLs(true);
                            webViewMovie.getSettings().setAllowUniversalAccessFromFileURLs(true);
                            webViewMovie.getSettings().setMediaPlaybackRequiresUserGesture(false);
                            webViewMovie.loadUrl("https://www.2embed.to/embed/tmdb/movie?id=" + movie.getData().getId());

                            dialog.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, "Có thể dữ liệu phim đã bị xoá khỏi Database", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}