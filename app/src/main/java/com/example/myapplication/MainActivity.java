package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // comentar
    private TextView txtImc, txtTipoPeso;
    private EditText peso, altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // atribuição dos componentes da tela (texto de resultado do IMC e tipo do peso)
        this.txtImc      = findViewById(R.id.txtImc);
        this.txtTipoPeso = findViewById(R.id.tipoPeso);

        // atribuição dos componentes de entrada da tela
        this.peso   = findViewById(R.id.peso);
        this.altura = findViewById(R.id.altura);

        // adiciona a classe de mascara em cada uma das entradas
        this.peso.addTextChangedListener(new Mascara(peso, "PESO"));
        this.altura.addTextChangedListener(new Mascara(altura, "ALTURA"));

    }

    /*
    * O método pega os valores digitados pelo usuário, tira o formato criado pela máscara,
    * cálculo o imc dado as entradas (caso exista exceção sera tratada) e atribui os valores de saída
    * nos TextViews atribuidos na função do create()
    * */
    public void onClick(View view) {
        try {
            String tipoPeso;
            String txtPeso   = this.peso.getText().toString();
            String txtAltura = this.altura.getText().toString();

            // retira a formatação das entradas
            txtPeso   = txtPeso.replaceAll("kg", "");
            txtAltura = txtAltura.replaceAll("m", "");
            txtPeso   = txtPeso.replaceAll(",", "");
            txtAltura = txtAltura.replaceAll(",", "");
            txtPeso   = txtPeso.replaceAll(",", "");
            txtAltura = txtAltura.replaceAll(",", "");

            double peso   = Double.parseDouble(txtPeso);
            double altura = Double.parseDouble(txtAltura);

            // verifica se existe uma variável com zero, se for não é possível fazer o cálculo
            if (peso == 0 || altura == 0) {
                Toast.makeText(getApplicationContext(), "Os valores não podem ser 0", Toast.LENGTH_SHORT).show();
            } else {
                double imc  = peso/(altura*altura);

                // formata a variável de saida para mostrar somente duas casa decimais
                @SuppressLint("DefaultLocale")
                String formatado = String.format("%.2f", imc);

                // Verifica em qual calssificaçao o imc calculado se encontra
                if (imc <= 18.5) {
                    tipoPeso = "Baixo peso";
                } else if (imc > 18.5 && imc <= 24.9) {
                    tipoPeso = "Eutrofia (peso adequado)";
                } else if (imc >= 25 && imc <= 29.9) {
                    tipoPeso = "Sobrepeso";
                } else if (imc >= 30 && imc <= 34.9) {
                    tipoPeso = "Obesidade grau 1";
                } else if (imc >= 35 && imc <= 39.9) {
                    tipoPeso = "Obesidade grau 2";
                } else {
                    tipoPeso = "Obesidade extrema";
                }
                txtImc.setText("IMC: " + formatado);
                txtTipoPeso.setText(tipoPeso);
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Digite as informações certas! Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
