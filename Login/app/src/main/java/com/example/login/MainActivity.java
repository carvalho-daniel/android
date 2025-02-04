package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnEntrar = findViewById(R.id.btnEntrar);
        login();
    }

    // função que conduz o usuário para a tela de cadastro
    public void onClickParaCadastro(View view) {
        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivity(it);

    }

    private void login() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared = getSharedPreferences("acessoUsuario", MODE_PRIVATE);
                Intent it2;
                EditText edtNome = findViewById(R.id.nome);
                EditText edtSenha = findViewById(R.id.senha);

                String nomeLogin = edtNome.getText().toString();
                String senhaLogin = edtSenha.getText().toString();

                if(!nomeLogin.isEmpty() && !senhaLogin.isEmpty()) {
                    String nomeSalvo = shared.getString("nome", "");
                    String senhaSalva = shared.getString("senha", "");

                    if (nomeSalvo.isEmpty() && senhaSalva.isEmpty()) {
                        it2 =  new Intent(MainActivity.this, Cadastro.class);
                        Toast.makeText(MainActivity.this, "Usuário não existe, indo para cadastro!", Toast.LENGTH_SHORT).show();
                        startActivity(it2);
                    } else {
                        if(nomeLogin.equals(nomeSalvo) && senhaLogin.equals(senhaSalva)) {
                            Toast.makeText(MainActivity.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                            it2 = new Intent(MainActivity.this, BoasVindas.class);
                            it2.putExtra("nome", nomeLogin);
                            startActivity(it2);
                        } else {
                            Toast.makeText(MainActivity.this, "Senha ou nome errados! Digite novamente", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}