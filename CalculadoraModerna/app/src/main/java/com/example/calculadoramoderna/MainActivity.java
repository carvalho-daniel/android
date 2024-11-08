package com.example.calculadoramoderna;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    TextView resultado, subResultado;

    Button[] botoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main);

        botoes = new Button[10];

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        resultado    = findViewById(R.id.txtResultado);
        subResultado = findViewById(R.id.txtSubResultado);

        int p = 0;
        botoes[p++] = btn0;
        botoes[p++] = btn1;
        botoes[p++] = btn2;
        botoes[p++] = btn3;
        botoes[p++] = btn4;
        botoes[p++] = btn5;
        botoes[p++] = btn6;
        botoes[p++] = btn7;
        botoes[p++] = btn8;
        botoes[p]   = btn9;

        for(Button btn : botoes) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numero(btn);
                }
            });
        }

    }

    public void onClickLimpar(View view) {
        // limpar as variaveis auxiliares de conta se precisar
        resultado.setText("");
        subResultado.setText("");

    }

    public void numero(Button btn) {
        String num = btn.getText().toString();
        String subR = subResultado.getText().toString();

        subR += num;

        this.subResultado.setText(subR);

    }

    public void operacoes(final String OP) {
        String subR = subResultado.getText().toString();

        if (subR.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Digite um valor primeiro", Toast.LENGTH_SHORT).show();
        } else {
            subR += OP;
            subResultado.setText(subR);
        }
    }

    public void onClickMais(View view) { operacoes("+"); }

    public void onClickMenos(View view) { operacoes("-"); }

    public void onClickMult(View view) { operacoes("x"); }

    public void onClickDivisao(View view) { operacoes("/"); }

    public void onClickPorcentagem(View view) { operacoes("%"); }

    public void onClickVirgula(View view) { operacoes(","); }




}