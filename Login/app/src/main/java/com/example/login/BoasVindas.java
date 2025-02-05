package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BoasVindas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_boas_vindas);

        /*
            A tela de login passa para essa tela o nome do usuário
            que pega atravéz da intent e escreve na tela uma mengagem de boas vidnas
         */
        Intent it = getIntent();

        TextView mensagem = findViewById(R.id.mensagem);

        String nome = it.getStringExtra("nome");

        mensagem.setText("Boas vindas " + nome + "!");

    }
}