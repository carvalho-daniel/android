package com.example.recyclefilmes;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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
        // alita, astroboy, chappie, circulo de fogo, eu robo, ghost in the shell, gigantes de aço, homem bicentenario, mecanimais, megas xlr, robo simbionico, robocop, transformers, walle
        movieList.add(new Movie("Alita Anjo de Combate", R.drawable.alita, "2019-02-14", 8.5, "No futuro, Alita, uma jovem ciborgue, tenta descobrir seu passado enquanto luta para proteger a cidade de Iron City."));
        movieList.add(new Movie("AstroBoy", R.drawable.astroboy, "2009-10-23", 6.3, "Astro Boy é um robô criado com as habilidades de seu criador falecido e começa uma jornada para encontrar seu lugar no mundo."));
        movieList.add(new Movie("Chappie", R.drawable.chappie, "2015-03-06", 6.8, "Chappie é um robô que é sequestrado por criminosos e ensinado a pensar e agir como um humano, enfrentando dilemas morais."));
        movieList.add(new Movie("Circulo de Fogo", R.drawable.circulo_de_fogo, "2013-07-12", 7.0, "Gigantescas criaturas saem de uma fenda no fundo do oceano e heróis humanos enfrentam esses monstros em batalhas épicas."));
        movieList.add(new Movie("Eu Robo", R.drawable.eu_robo, "2004-07-16", 7.1, "Em um futuro onde robôs são parte da sociedade, um detetive investiga um crime envolvendo uma máquina que não deveria ser capaz de cometer homicídios."));
        movieList.add(new Movie("Ghost in the Shell", R.drawable.ghost_in_the_shell, "2017-03-31", 6.3, "Em um futuro cibernético, a Major é uma ciborgue policial que combate o crime enquanto busca descobrir sua própria identidade."));
        movieList.add(new Movie("Gigantes de Aço", R.drawable.gigantes_de_aco, "2011-10-07", 7.1, "Em um futuro onde lutas de robôs são populares, um ex-boxeador e seu filho tentam se reerguer ao treinar um robô de combate."));
        movieList.add(new Movie("Homem bicentenario", R.drawable.homem_bicentenario, "1999-12-17", 6.9, "Andrew é um robô doméstico que evolui ao longo de 200 anos, desenvolvendo emoções e buscando sua humanidade."));
        movieList.add(new Movie("Mecanimais", R.drawable.mecanimais, "2005-06-10", 5.8, "Num futuro distópico, um cientista tenta criar seres humanos modificados geneticamente para serem mais resistentes à guerra."));
        movieList.add(new Movie("Megas XLR", R.drawable.megas_xlr, "2004-09-18", 8.0, "Um adolescente encontra um robô gigante de outro planeta e o pilota para combater ameaças intergalácticas."));
        movieList.add(new Movie("Robo Simbionico", R.drawable.robo_simbionico, "2008-09-15", 7.5, "Em um mundo onde humanos e robôs coexistem, um novo tipo de robô simbiótico é criado para resolver questões ambientais e sociais."));
        movieList.add(new Movie("Robocop", R.drawable.robocop, "1987-07-17", 7.6, "Após ser gravemente ferido, um policial de Detroit é transformado em Robocop, um ciborgue com a missão de combater o crime."));
        movieList.add(new Movie("Transformers", R.drawable.transformers, "2007-07-03", 7.0, "Robôs alienígenas travam uma guerra em segredo na Terra, com jovens humanos sendo pegos no meio da batalha."));
        movieList.add(new Movie("Wall-e", R.drawable.walle, "2008-06-27", 8.0, "Wall-E é um pequeno robô que vive sozinho na Terra, anos após a humanidade deixar o planeta. Ele embarca em uma aventura que pode salvar o futuro da Terra."));

        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("movieTitle", movie.getTitle());
                intent.putExtra("moviePosterImage", movie.getPosterImage());
                intent.putExtra("movieDetailOverview", movie.getOverview());
                intent.putExtra("movieRating", movie.getRating());
                startActivity(intent);
            }
        });
    }
}