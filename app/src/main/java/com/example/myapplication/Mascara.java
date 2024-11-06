package com.example.myapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.text.DecimalFormat;


public class Mascara implements TextWatcher {

    /*
    * váriaveis que são usadas para atribuição das váriaveis de entrada
    * uma é referência ao EditText que está usando a máscara a outra qual o tipo da máscara
    * */
    private EditText edtTtxt;
    private String tipo;

    public Mascara(EditText txt, String tipo) {
        this.edtTtxt = txt;
        this.tipo  = tipo;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        // remove o listener para não ouvir a própria mudança e gerar loop infinito
        this.edtTtxt.removeTextChangedListener(this);

        // verifica se o campo está vazio está vazio
        if (!this.edtTtxt.getText().toString().isEmpty()) {
            String texto = charSequence.toString().replaceAll("[^\\d]", "");
            double num   = Double.parseDouble(texto);

            String formatado;

            // recebe um tipo que diz se é peso ou altura para formartar diferente
            if (tipo.equals("PESO")) {
                formatado = new DecimalFormat("#,##0.00").format(num/100) + " kg";
            } else {
                formatado = new DecimalFormat("#0.00").format(num/100) + " m";
            }

            this.edtTtxt.setText(formatado);

            // indica quantas casas não seram puladas dada o tipo da máscara
            int x = tipo.equals("PESO") ? 3 : 2;

            // coloca o cursor no final dos números menos a quantidade da mascara (peso ou altura)
            this.edtTtxt.setSelection(formatado.length() - x);
        }
        this.edtTtxt.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
