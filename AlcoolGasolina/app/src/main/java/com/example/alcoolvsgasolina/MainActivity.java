package com.example.alcoolvsgasolina;

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

public class MainActivity extends AppCompatActivity {

    EditText txtGasolina, txtAcool;
    TextView txtResultado;

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

        txtGasolina  = findViewById(R.id.txtGasolina);
        txtAcool     = findViewById(R.id.txtAlcool);
        txtResultado = findViewById(R.id.txtResultado);

        txtGasolina.addTextChangedListener(new Ouvidor(txtGasolina));
        txtAcool.addTextChangedListener(new Ouvidor(txtAcool));


    }

    public void onClick(View view) {
        try {
            String textoG = txtGasolina.getText().toString().replaceAll("[^\\d]", "");
            String textoA = txtAcool.getText().toString().replaceAll("[^\\d]", "");


            double gasolina = Double.parseDouble(textoG);
            double alcool   = Double.parseDouble(textoA);

            if (gasolina == 0 || alcool == 0) {
                Toast.makeText(getApplicationContext(), "Digite um valor!", Toast.LENGTH_SHORT).show();
            } else {
                double melhor   = alcool/gasolina;

                txtResultado.setText(melhor <= 0.7 ? "Álcool" : "Gasolina");
            }

            // vamo ver se esse comentario vai
            // asdasdasd


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Valor inválido: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}