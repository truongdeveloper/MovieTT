package com.example.moviett;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moviett.Adapter.DateCinemaAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CinemaDetailActivity extends AppCompatActivity {

    private RecyclerView rcvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);

        // Nhận movie từ CinemaFragment
        Intent intent = getIntent();
        int idMovie = intent.getIntExtra("idMovie", 1);

        rcvDate = findViewById(R.id.rcvDays);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(CinemaDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcvDate.setLayoutManager(layoutManager);
        getDayInMonth();
    }

    public void getNowMovie() {

    }

    public void getDayInMonth() {
        Calendar calendar = Calendar.getInstance();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // lấy thứ (1-7: Chủ nhật đến Thứ Bảy)
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // lấy ngày trong tháng (1-31)
        int month = calendar.get(Calendar.MONTH); // lấy tháng (0-11: Tháng 0 là tháng Một)
        int year = calendar.get(Calendar.YEAR); // lấy năm (ví dụ: 2022)

        List<String> daysInMonth = new ArrayList<>();
        int daysInMonthCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysInMonthCount; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            String day = new SimpleDateFormat("dd", Locale.getDefault()).format(calendar.getTime()); //"dd/MM/yyyy"
            daysInMonth.add(day);
        };

        if (daysInMonth != null) {
            DateCinemaAdapter cinemaAdapter = new DateCinemaAdapter(daysInMonth, CinemaDetailActivity.this);
            rcvDate.setAdapter(cinemaAdapter);
        }

    }


}