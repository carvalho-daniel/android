package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BoasVindas extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_boas_vindas);


        TextView mensagem = findViewById(R.id.mensagem);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        // id do usuário vindo do firebase
        String idUsuario = mAuth.getCurrentUser().getUid();

        DatabaseReference userRef = myRef.child("usuario").child(idUsuario);


        // usando o id do usuário que o próprio firebase utiliza pode-se retornar o nome do usuário e atribuir ao texto da tela
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nome = snapshot.child("nome").getValue().toString();

                if(!nome.isEmpty())
                    mensagem.setText("Boas vindas, " + nome +  " !");
                else
                    mensagem.setText("Boas vindas!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}