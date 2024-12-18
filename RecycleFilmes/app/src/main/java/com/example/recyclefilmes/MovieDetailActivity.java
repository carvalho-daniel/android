package com.example.recyclefilmes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailActivity extends AppCompatActivity {
    TextView titleTextView;
    ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleTextView = findViewById(R.id.movieDetailTitle);
        posterImageView = findViewById(R.id.movieDetailPoster);

        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra("movieTitle");
        int movieImage = intent.getIntExtra("moviePosterImage", -1);

        if(movieTitle != null && movieImage != -1) {
            titleTextView.setText(movieTitle);
            posterImageView.setImageResource(movieImage);
        }

    }

}
