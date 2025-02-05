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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cadastro extends AppCompatActivity {

    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        cadastrar = findViewById(R.id.btnCadastrar);

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
                EditText edtNome = findViewById(R.id.nomeCad);
                EditText edtSenha = findViewById(R.id.senhaCad);

                String nome = edtNome.getText().toString();
                String senha = edtSenha.getText().toString();

                if(!nome.isEmpty() && !senha.isEmpty()) {
                    SharedPreferences shared = getSharedPreferences("acessoUsuario", MODE_PRIVATE);
                    SharedPreferences.Editor editor =  shared.edit();

                    editor.putString("nome", nome);
                    editor.putString("senha", senha);
                    editor.apply();

                    Toast.makeText(Cadastro.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(Cadastro.this, MainActivity.class);
                    startActivity(it);

                } else {
                    Toast.makeText(Cadastro.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}