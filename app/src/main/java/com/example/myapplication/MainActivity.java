package com.example.myapplication;

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
            double peso   = Double.parseDouble(((EditText)findViewById(R.id.peso)).getText().toString());
            double altura = Double.parseDouble(((EditText)findViewById(R.id.altura)).getText().toString());

            double imc  = peso/(altura*altura);
            imc *= 100;



            txtImc.setText("IMC: " + imc);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Digite as informações certas! Erro: " + e.getMessage(), 0).show();
        }



    }
}