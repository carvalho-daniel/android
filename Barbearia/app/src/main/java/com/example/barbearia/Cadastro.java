package com.example.barbearia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.barbearia.TelaPrincipal;
import com.example.barbearia.Usuario;
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
        Cadastro de usuário: método para cadastrar o usuário com as informçaões que ele deseja
        é salvo no sharedpreferences para caso o usuário feche a janela o login dele continue
     */
    private void cadastrarUsuario() {

        cadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText edtNome = findViewById(R.id.nomeCad);
                EditText edtIdade = findViewById(R.id.idadeCad);
                EditText edtEmail = findViewById(R.id.emailCad);
                EditText edtSenha = findViewById(R.id.senhaCad);

                String nome = edtNome.getText().toString();
                // mudar pra number no xml
                String idade = edtIdade.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                if (!email.isEmpty() && !senha.isEmpty() && !nome.isEmpty() && !idade.isEmpty()) {
                    int age = Integer.parseInt(edtIdade.getText().toString());

                    if (senha.length() >= 6) {
                        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    // Acessa o usuario logado
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null)
                                        user.sendEmailVerification();

                                    // Referencia do DB ao cadastro do usuario
                                    DatabaseReference userRef = myRef.child("usuario");

                                    // Cria um objeto usuario com os devidos campos preenchidos
                                    Usuario usr = new Usuario(nome, age, email);

                                    // Coleta o ID do usuario logado
                                    String idUsuario = mAuth.getCurrentUser().getUid();

                                    // Método que registra no DB os dados do objeto usuario
                                    userRef.child(idUsuario).setValue(usr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Cadastro.this, "Verifique seu email para terminar o cadastro.", Toast.LENGTH_SHORT).show();
                                                // Usando Handler para atrasar a execução de outro código, caso precise
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        // Ação a ser realizada após o Toast desaparecer (se necessário)
                                                        // Retorna para a tela inicial (TelaPrincipal)
                                                        Intent it = new Intent(Cadastro.this, MainActivity.class);
                                                        startActivity(it);
                                                        finish();
                                                    }
                                                }, 3000); // 3000ms = 2 segundos
                                            } else {
                                                Log.d("Firebase Realtime DB", "Erro ao tentar salvar usuáiro", task.getException());
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Cadastro.this, "O campo senha precisar conter, pelo menos, 6 caracteres.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Cadastro.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
};