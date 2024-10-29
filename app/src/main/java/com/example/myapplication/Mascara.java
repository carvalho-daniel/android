package com.example.myapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class Mascara implements TextWatcher {
    EditText edtTtxt;
    String tipo;

    public Mascara(EditText texto, String tipo) {
        this.edtTtxt = texto;
        this.tipo  = tipo;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        edtTtxt.removeTextChangedListener(this);

        if (!charSequence.toString().isEmpty()) {
            String texto = charSequence.toString().replaceAll("[^\\d]", "");
            double num   = Double.parseDouble(texto);

            String formatado = "";
            if (tipo.equals("PESO")) {
                formatado = new DecimalFormat("#,##0.00").format(num/100) + " kg";
            } else if (tipo.equals("ALTURA")) {
                formatado = new DecimalFormat("#0.00").format(num/100) + " m";
            }

            edtTtxt.setText(formatado);
            edtTtxt.setSelection(formatado.length() - (tipo.equals("PESO") ? 3 : 2));

        }


        edtTtxt.removeTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
