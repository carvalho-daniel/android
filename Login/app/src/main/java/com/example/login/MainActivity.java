package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnEntrar;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnEntrar = findViewById(R.id.btnEntrar);
        login();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }

    // Função que conduz o usuário para a tela de cadastro
    public void onClickParaCadastro(View view) {
        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivity(it);
    }

    /*
        Método para realizar login, caso as informações estejam certas o usuário é conduzido para a tela de boas vindas
        caso ele esteja errado é mostrado uma mensagem para reescrever as informações
        caso não exista um login o usuário é conduzido para a tela de cadastro
     */
    public void login() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtEmail = findViewById(R.id.email);
                EditText edtSenha = findViewById(R.id.senha);

                String emailLogin = edtEmail.getText().toString();
                String senhaLogin = edtSenha.getText().toString();

                if(!emailLogin.isEmpty() && !senhaLogin.isEmpty()) {

                    // Uso do firebase para autenticar o usuário com email e senha
                    mAuth.signInWithEmailAndPassword(emailLogin, senhaLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                try {
                                    if(user.isEmailVerified()) {
                                        Intent it3 = new Intent(MainActivity.this, BoasVindas.class);
                                        Toast.makeText(MainActivity.this, "Login realizado com sucesso.", Toast.LENGTH_SHORT).show();
                                        startActivity(it3);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Por favor, verifique seu email para finalizar o cadastro e tento novamente.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Log.v("Login", task.getException().toString());
                                Toast.makeText(MainActivity.this, "Email e ou senha inválido, tente novamente.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}