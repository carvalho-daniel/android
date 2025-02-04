package com.example.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
    static ArrayList<Tarefa> tarefaList = new ArrayList<>();
    RecyclerView recyclerView;
    static TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tarefaList.add(new Tarefa("feita", true));
        tarefaList.add(new Tarefa("para fazer", false));

        tarefaAdapter = new TarefaAdapter(this, tarefaList);
        recyclerView.setAdapter(tarefaAdapter);


    }

    public void onClickAdicionar(View view) {
        TextView titulo = findViewById(R.id.tituloTarefa);
        String tituloTarefa = titulo.getText().toString();

        if(!tituloTarefa.isEmpty()) {
            tarefaList.add(new Tarefa(tituloTarefa, false));
            tarefaAdapter.notifyItemInserted(tarefaList.size() - 1);

            titulo.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "O título da tarefa não pode ser vazio!", Toast.LENGTH_SHORT).show();
        }


    }

    public static void excluir(int p) {
        tarefaList.remove(p);
        tarefaAdapter.notifyItemRemoved(p);
    }
}