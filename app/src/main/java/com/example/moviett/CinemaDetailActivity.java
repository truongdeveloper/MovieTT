package com.example.moviett;

import static com.example.moviett.ApiContainer.ApiService.apiService;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviett.Adapter.CinemaInfoAdapter;
import com.example.moviett.Adapter.CinemaDateAdapter;
import com.example.moviett.ApiContainer.MovieApi;
import com.example.moviett.ApiMovieDetail.Cast;
import com.example.moviett.ApiMovieDetail.CinemaCalender;
import com.example.moviett.ApiMovieDetail.CinemaMovie;
import com.example.moviett.ApiMovieDetail.Genres;
import com.example.moviett.ApiMovieDetail.MovieDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CinemaDetailActivity extends AppCompatActivity {

    private RecyclerView rcvDate;
    private RecyclerView rcvCinemaInfo;
    private TextView tvDate;
    private ImageView imgBackdropImage;
    private TextView tvMovieTitle, tvIbm, tvGenre, tvReleaseDate, tvDescription, tvActors;
    DecimalFormat df = new DecimalFormat("#.##");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);

        // Nhận movie từ CinemaFragment
        Intent intent = getIntent();
        int idMovie = intent.getIntExtra("idMovie", 1);
        String nameMovie= intent.getStringExtra("nameMovie"); // tên phim
        String releaseDate = intent.getStringExtra("releaseDate"); // ngày phát hành
        String[] release = releaseDate.split("-");

        tvDate = findViewById(R.id.tv_Date);
        imgBackdropImage = findViewById(R.id.backdrop_image);
        tvMovieTitle = findViewById(R.id.movie_title);
        tvIbm = findViewById(R.id.ibm);
        tvGenre = findViewById(R.id.genre);
        tvReleaseDate = findViewById(R.id.release_date);
        tvDescription = findViewById(R.id.movie_description);
        tvActors = findViewById(R.id.cast);

        // ánh xạ view ngày
        rcvDate = findViewById(R.id.rcvDays);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(CinemaDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcvDate.setLayoutManager(layoutManager);
        // ánh xạ view thông tin (tên rạp, thời gian chiếu)
        rcvCinemaInfo = findViewById(R.id.rcv_cinemaInfo);
        rcvCinemaInfo.setLayoutManager(new GridLayoutManager(CinemaDetailActivity.this, 1));

        callApigetHome(idMovie);
        getDayInMonth(release[2], release[1], release[0]);
        //getDayInMonth("release[2]", "10", "05");

        //getNowMovie(); // Phim chiếu rạp (bản phụ)
        getNowMovieList(0, 5); // Phim chiếu rạp (bản chính)
    }

    // danh sách phim chiếu rạp theo ngày (bản chính)
    public void getNowMovieList(int start, int end) {
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
        if (cinemaMovies != null) {
            //Collections.shuffle(cinemaMovies); // đảo vị trí các phần tử
            List<CinemaMovie> result = cinemaMovies.subList(start, end);
            Collections.sort(result, new SortByName());
            CinemaInfoAdapter cinemaAdapter = new CinemaInfoAdapter(CinemaDetailActivity.this, result);
            rcvCinemaInfo.setAdapter(cinemaAdapter);
        }
    }

    // Sắp xếp theo tên
    public class SortByName implements Comparator<CinemaMovie> {
        public int compare(CinemaMovie cinemaMovie1, CinemaMovie cinemaMovie2) {
            return cinemaMovie1.getMovieTheaterName().compareTo(cinemaMovie2.getMovieTheaterName());
        }
    }

    // thông tin phim đang chiếu: tên rạp, thời gian chiếu (bản phụ)
    public List<CinemaMovie> getNowMovie() {
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

        if (cinemaMovies != null) {
            CinemaInfoAdapter cinemaAdapter = new CinemaInfoAdapter(CinemaDetailActivity.this, cinemaMovies);
            rcvCinemaInfo.setAdapter(cinemaAdapter);
        }
        return cinemaMovies;
    }

    // Hiển thị ngày tháng
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getDayInMonth(String dayRelease, String monthRelease, String yearRelease) {
        // Danh sách chứa 2 tháng liên tiếp
        List<Integer> daysInMonth = new ArrayList<>();
        int year = Integer.parseInt(yearRelease);
        int month = Integer.parseInt(monthRelease);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month-1);
        int numDaysInNowMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int day = 1; day <= numDaysInNowMonth; day++) {
            calendar.get(Calendar.DAY_OF_MONTH);
            calendar.set(year, month - 1, day); // Đặt ngày là ngày trong tháng
            daysInMonth.add(day);
        }
        calendar.set(Calendar.MONTH, month);
        int numDaysInNextMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int day = 1; day <= numDaysInNextMonth; day++) {
            calendar.get(Calendar.DAY_OF_MONTH);
            calendar.set(year, month, day); // Đặt ngày là ngày trong tháng
            daysInMonth.add(day);
        }

        // Ngày hiện tại
        String[] dates = LocalDate.now().toString().split("-");
        calendar.set(year, month - 1, Integer.parseInt(dates[2])); // Đặt ngày là ngày trong tháng
        int date = calendar.get(Calendar.DAY_OF_WEEK); // Thứ của ngày
        String now = getDayOfWeek(date) + ", " + dates[2] + "/" + dates[1] + "/" + dates[0];
        tvDate.setText(now);

        // lưu danh sách các ngày trong tháng
        List<CinemaCalender> cinemaCalenders = new ArrayList<>();
        int dem = 0, index = 0;
        Calendar calenderNow = Calendar.getInstance();
        calenderNow.set(Calendar.MONTH, month-1);
        int currentDay = calenderNow.get(Calendar.DAY_OF_MONTH);
        // Lấy ngày bắt đầu từ ngày hiện tại và 13 ngày tiếp theo
        for(int i = daysInMonth.indexOf(currentDay); i <= daysInMonth.size(); i++) {
            if (daysInMonth.get(i) >= currentDay && dem <= 14 && daysInMonth.get(i) <= numDaysInNowMonth) {
                calendar.set(year, month - 1, daysInMonth.get(i));
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                CinemaCalender cinemaCalender = new CinemaCalender(getDayOfWeek(dayOfWeek), daysInMonth.get(i), month, year);
                cinemaCalenders.add(cinemaCalender);
                dem++;
            }
            else {
                index = i;
                break;
            }
        }
        for(int i = index; i <= daysInMonth.size(); i++) {
            if (i >= calendar.get(Calendar.DAY_OF_MONTH) && dem <= 14) {
                calendar.set(year, month, daysInMonth.get(i));
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                CinemaCalender cinemaCalender = new CinemaCalender(getDayOfWeek(dayOfWeek), daysInMonth.get(i), month + 1, year);
                cinemaCalenders.add(cinemaCalender);
                dem++;
            }
        }
        CinemaDateAdapter cinemaDateAdapter = new CinemaDateAdapter(cinemaCalenders, CinemaDetailActivity.this, new CinemaDateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CinemaCalender calendar) {
                String txtDate = calendar.getDayOfWeek() + ", "
                        + calendar.getDay() + "/"
                        + calendar.getMonth() + "/"
                        + calendar.getYear();
                tvDate.setText(txtDate);
                int start = cinemaCalenders.indexOf(calendar);
                int end = start + 5;
                Toast.makeText(CinemaDetailActivity.this, String.valueOf(cinemaCalenders.indexOf(calendar)), Toast.LENGTH_SHORT).show();
                // Danh sách rạp chiếu phim theo ngày
                getNowMovieList(start, end);
            }
        });
        rcvDate.setAdapter(cinemaDateAdapter);
    }

    // Hàm chuyển đổi thành thứ
    public static String getDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "CN";
            case Calendar.MONDAY:
                return "T2";
            case Calendar.TUESDAY:
                return "T3";
            case Calendar.WEDNESDAY:
                return "T4";
            case Calendar.THURSDAY:
                return "T5";
            case Calendar.FRIDAY:
                return "T6";
            case Calendar.SATURDAY:
                return "T7";
            default:
                return "";
        }
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
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Toast.makeText(CinemaDetailActivity.this, "Có thể dữ liệu phim đã bị xoá khỏi Database", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}