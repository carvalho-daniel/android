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

        if (!edtTtxt.getText().toString().isEmpty()) {
            String texto = charSequence.toString().replaceAll("[^\\d]", "");
            double num   = Double.parseDouble(texto);

            String formatado;
            if (tipo.equals("PESO")) {
                formatado = new DecimalFormat("#,##0.00").format(num/100) + " kg";
            } else {
                formatado = new DecimalFormat("#0.00").format(num/100) + " m";
            }

            edtTtxt.setText(formatado);
            int x = tipo.equals("PESO") ? 3 : 2;
            edtTtxt.setSelection(formatado.length() - x);

        }


        edtTtxt.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
