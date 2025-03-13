package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // cortar cabelo, lavar o cabelo, cortar a barba, completo (barba + cabelo)
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Opcao> opcaoList = new ArrayList<>();

        opcaoList.add(new Opcao("Cortar cabelo", 25.00, "Corta o cabelo do camarada", R.drawable.cabelo));
        opcaoList.add(new Opcao("Lavar o cabelo", 20.00, "Lava o cabelo do camarada", R.drawable.lavagem));
        opcaoList.add(new Opcao("Cortar a barba", 15.00, "Corta a barba do camarada", R.drawable.barba));
        opcaoList.add(new Opcao("Completo ( barba + cabelo )", 40.00, "Corta o cabelo e a barba do camarada", R.drawable.barba_cabelo));

        OpcaoAdapter opcaoAdapter = new OpcaoAdapter(this, opcaoList);
        recyclerView.setAdapter(opcaoAdapter);

        opcaoAdapter.setOnItemClickListener(new OpcaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Opcao opcao) {
                Toast.makeText(getApplicationContext(), "Vai para outra tela", Toast.LENGTH_LONG).show();
            }
        });

    }
}