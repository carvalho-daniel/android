package com.example.myapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Mascara implements TextWatcher {
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
        edtTtxt.removeTextChangedListener(this);

        // verifica se o campo está vazio está vazio
        if (!edtTtxt.getText().toString().isEmpty()) {
            String texto = charSequence.toString().replaceAll("[^\\d]", "");
            double num   = Double.parseDouble(texto);

            String formatado;
            // recebe um tipo que diz se é peso ou altura para formartar diferente
            if (tipo.equals("PESO")) {
                formatado = new DecimalFormat("#,##0.00").format(num/100) + " kg";
            } else {
                formatado = new DecimalFormat("#0.00").format(num/100) + " m";
            }

            edtTtxt.setText(formatado);
            /*
            x indica a quantida de casas que não se deve pular,
            já que o texto fica depois do valor
             */
            int x = tipo.equals("PESO") ? 3 : 2;

            // coloca o cursor no final dos números menos a quantidade da mascara (peso ou altura)
            edtTtxt.setSelection(formatado.length() - x);

        }


        edtTtxt.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
