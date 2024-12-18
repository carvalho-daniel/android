package com.example.recyclefilmes;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Todo poderoso", R.drawable.movie1, "2019-04-26", 10, "AAAAAAA"));
        movieList.add(new Movie("O segredo dos animais", R.drawable.movie2, "2019-04-26", 1000, "Otis o boi resenha"));
        movieList.add(new Movie("Deu a louca na chapeuzinho", R.drawable.movie3, "2019-04-26", 10, "AAAAAAA"));

        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("movieTitle", movie.getTitle());
                intent.putExtra("moviePosterImage", movie.getPosterImage());
                startActivity(intent);
            }
        });

    }
}