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

    TextView txtImc, txtTipoPeso;
    EditText peso, altura;

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

        txtImc      = findViewById(R.id.txtImc);
        txtTipoPeso = findViewById(R.id.tipoPeso);

        peso   = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);

        peso.addTextChangedListener(new Mascara(peso, "PESO"));
        altura.addTextChangedListener(new Mascara(altura, "ALTURA"));

    }

    public void onClick(View view) {
        //  dividindo o peso (em quilogramas) pela altura ao quadrado (em metros)

        try {
            String tipoPeso;

            String txtPeso   = peso.getText().toString();
            String txtAltura = altura.getText().toString();

            txtPeso   = txtPeso.replaceAll("[^\\d]", "");
            txtAltura = txtAltura.replaceAll("[^\\d]", "");


            double peso   = Double.parseDouble(txtPeso);
            double altura = Double.parseDouble(txtAltura);

            double imc  = peso/(altura*altura);
            imc *= 100;

            @SuppressLint("DefaultLocale")
            String formatado = String.format("%.2f", imc);
            txtImc.setText("IMC: " + formatado);

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

            txtTipoPeso.setText(tipoPeso);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Digite as informações certas! Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }
}