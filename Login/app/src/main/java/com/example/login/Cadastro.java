package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {

    Button cadastrar;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        cadastrar = findViewById(R.id.btnCadastrar);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        cadastrarUsuario();
    }

    /*
        Cadastro de usuário
        método para cadastrar o usuário com as informçaões que ele deseja
        é salvo no sharedpreferences para caso o usuário feche a janela o login dele continue
     */
    private void cadastrarUsuario() {
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtNome  = findViewById(R.id.nomeCad);
                EditText edtIdade = findViewById(R.id.idadeCad);
                EditText edtEmail = findViewById(R.id.emailCad);
                EditText edtSenha = findViewById(R.id.senhaCad);

                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                if(!email.isEmpty() && !senha.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(user != null)
                                    user.sendEmailVerification();

                                DatabaseReference userRef = myRef.child("usuario");
                                Usuario usr = new Usuario(nome, idade, email);

                                String idUsuario = mAuth.getCurrentUser().getUid();

                                userRef.child(idUsuario).setValue(usr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("Firebase", "Usuário salvo com sucesso");
                                        } else {
                                            Log.d("Firebase", "Erro ao tentar salvar usuáiro", task.getException());
                                        }
                                    }
                                });



                            }
                        }
                    });

                    Toast.makeText(Cadastro.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(Cadastro.this, MainActivity.class);
                    startActivity(it);
                    finish();



                } else {
                    Toast.makeText(Cadastro.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


};