package com.example.moviett;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviett.Adapter.CinemaInfoAdapter;
import com.example.moviett.Adapter.CinemaDateAdapter;
import com.example.moviett.ApiMovieDetail.CinemaCalender;
import com.example.moviett.ApiMovieDetail.CinemaMovie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CinemaDetailActivity extends AppCompatActivity {

    private RecyclerView rcvDate;
    private RecyclerView rcvCinemaInfo;
    private TextView tvDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);

        // Nhận movie từ CinemaFragment
        Intent intent = getIntent();
        String nameMovie = intent.getStringExtra("nameMovie"); // tên phim
        String releaseDate = intent.getStringExtra("releaseDate"); // ngày phát hành
        String[] release = releaseDate.split("-");

        tvDate = findViewById(R.id.tv_Date);

        // ánh xạ view ngày
        rcvDate = findViewById(R.id.rcvDays);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(CinemaDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcvDate.setLayoutManager(layoutManager);
        // ánh xạ view thông tin (tên rạp, thời gian chiếu)
        rcvCinemaInfo = findViewById(R.id.rcv_cinemaInfo);
        rcvCinemaInfo.setLayoutManager(new GridLayoutManager(CinemaDetailActivity.this, 1));

        getDayInMonth(release[2], release[1], release[0]); // Hiện ngày trong tháng bắt đầu từ ngày hiện tại
        getNowMovie(nameMovie); // Phim đang chiếu

    }

    // thông tin phim đang chiếu: tên rạp, thời gian chiếu
    public void getNowMovie(String nameMovie) {
        // Danh sách thông tin (rạp, thời gian chiếu)
        List<CinemaMovie> cinemaMovies = new ArrayList<>();
        cinemaMovies.add(new CinemaMovie(nameMovie, "CGV Long Biên", "19:00 - 22:00", R.drawable.cgv));
        cinemaMovies.add(new CinemaMovie(nameMovie, "CGV Hồ Gươm", "19:00 - 22:00", R.drawable.cgv));
        cinemaMovies.add(new CinemaMovie(nameMovie, "Lotte", "19:00 - 22:00", R.drawable.lotte));
        cinemaMovies.add(new CinemaMovie(nameMovie, "Lotte", "19:00 - 22:00", R.drawable.lotte));
        cinemaMovies.add(new CinemaMovie(nameMovie, "BHD Cầu Giấy", "19:00 - 22:00", R.drawable.bhd));
        cinemaMovies.add(new CinemaMovie(nameMovie, "BHD Định Công", "19:00 - 22:00", R.drawable.bhd));

        if (cinemaMovies != null) {
            CinemaInfoAdapter cinemaAdapter = new CinemaInfoAdapter(CinemaDetailActivity.this, cinemaMovies);
            rcvCinemaInfo.setAdapter(cinemaAdapter);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getDayInMonth(String dayRelease, String monthRelease, String yearRelease) {
        // lưu danh sách các ngày trong tháng vào danh sách đối tượng CinemaCalender (thứ, ngày, tháng, năm)
        List<CinemaCalender> cinemaCalenders = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(yearRelease);
        int month = Calendar.APRIL+1; // Note: Tháng bắt đầu từ 0 (0 = Tháng 1)
        calendar.set(year, month, 1); // Đặt ngày là ngày đầu tiên của tháng
        int numDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Số ngày trong tháng
        for (int day = 1; day <= numDays; day++) {
            if (day >= Integer.parseInt(dayRelease)) {
                calendar.set(year, month, day); // Đặt ngày là ngày trong tháng
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Thứ của ngày
                CinemaCalender cinemaCalender = new CinemaCalender(getDayOfWeek(dayOfWeek), day, month, year);
                cinemaCalenders.add(cinemaCalender);
            }
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Thứ của ngày
        String[] date = LocalDate.now().toString().split("-");
        String now = getDayOfWeek(dayOfWeek) + ", " + date[2] + "/" + date[1] + "/" + date[0];
        tvDate.setText(now);

        CinemaDateAdapter cinemaDateAdapter = new CinemaDateAdapter(cinemaCalenders, CinemaDetailActivity.this, new CinemaDateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CinemaCalender calendar) {
                String txtDate = calendar.getDayOfWeek() + ", "
                        + calendar.getDay() + "/"
                        + calendar.getMonth() + "/"
                        + calendar.getYear();
                tvDate.setText(txtDate);
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



}