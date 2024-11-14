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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    TextView txtResultado, txtSubResultado;

    Button[] botoes;

    Double resultado;

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

        txtResultado    = findViewById(R.id.txtResultado);
        txtSubResultado = findViewById(R.id.txtSubResultado);

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
        txtResultado.setText("");
        txtSubResultado.setText("");

    }

    public void numero(Button btn) {
        String num = btn.getText().toString();
        String subR = txtSubResultado.getText().toString();

        subR += num;

        this.txtSubResultado.setText(subR);

    }

    public void operacoes(String OP) {

        String subR   = this.txtSubResultado.getText().toString();
        String txtRes = this.txtResultado.getText().toString();

        if (subR.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Digite um valor primeiro", Toast.LENGTH_SHORT).show();
        } else {

            double numSub = Double.parseDouble(subR);

            String r = "";

            if (txtRes.isEmpty())
                r = String.valueOf(numSub) + " " + OP;
            else {
                char ult = txtRes.charAt(txtRes.length()-1);

                char o = OP.charAt(0);

                int ate;

                if (isOperacao(ult))
                    ate = txtRes.length()-1;
                else
                    ate = txtRes.length();

                if (txtRes.charAt(txtRes.length()-1) != o) {
                    r = "";
                    for (int i = 0; i < ate; i++) {
                        r += txtRes.charAt(i);
                    }
                    if (!isOperacao(ult))
                        r += " ";
                    r += o;
                }

            }

            this.txtResultado.setText(r);
            if (txtRes.isEmpty())
                this.txtSubResultado.setText("");

        }

    }

    public void onClickIgual(View view) {
        String txtNum = "";
        String txtRes = this.txtResultado.getText().toString();

        if (!txtRes.isEmpty() && !this.txtSubResultado.getText().toString().isEmpty()) {
            double num1, num2;
            char op = txtRes.charAt(txtRes.length()-1);

            for (int i = 0; i < txtRes.length()-1; i++) {
                txtNum += txtRes.charAt(i);
            }

            num1 = Double.parseDouble(txtNum);
            num2 = Double.parseDouble(this.txtSubResultado.getText().toString());

            switch (op) {
                case '+':
                    num1 += num2;
                    break;
                case '-':
                    num1 -= num2;
                    break;
                case 'x':
                    num1 *= num2;
                    break;
                case '/':
                    if (num2 != 0)
                        num1 /= num2;
                    break;
                case '%':
                    break;
                default:
                    break;
            }

            this.txtResultado.setText(String.valueOf(num1));
            this.txtSubResultado.setText("");

        }


    }


    public void onClickPorcentagem(View view) {
        String subR = this.txtSubResultado.getText().toString();

        if (!subR.isEmpty()) {
            double num = Double.parseDouble(subR);


            num /= 100;

            this.txtSubResultado.setText(String.valueOf(num));
        }

    }

    public void onClickVirgula(View view) {
        boolean jaTem = false;
        String txtSubr = this.txtSubResultado.getText().toString();

        if (!txtSubr.isEmpty()) {
            for (int i = 0; i < txtSubr.length(); i++ ) {
                if (txtSubr.charAt(i) == '.')
                    jaTem = true;
            }

            if (!jaTem) {
                txtSubr += ".";
                this.txtSubResultado.setText(txtSubr);
            }
        }
    }

    public void onClickMais(View view) { operacoes("+"); }

    public void onClickMenos(View view) { operacoes("-"); }

    public void onClickMult(View view) { operacoes("x"); }

    public void onClickDivisao(View view) { operacoes("/"); }

    public void onClickSinal(View view) {
        String txtSubr = this.txtSubResultado.getText().toString();
        if (!txtSubr.isEmpty()) {
            double num = Double.parseDouble(txtSubr);
            num *= -1;
            this.txtSubResultado.setText(num+"");
        }
    }

    public boolean isOperacao(char op) { return op == '+' || op == '-' || op == 'x' || op == '/' || op == '%'; }


}