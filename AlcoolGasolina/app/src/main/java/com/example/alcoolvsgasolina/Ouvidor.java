package com.example.alcoolvsgasolina;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class Ouvidor implements TextWatcher {
    private EditText texto;

    public Ouvidor(EditText texto) { this.texto = texto; }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        texto.removeTextChangedListener(this);

        if (!texto.getText().toString().isEmpty()) {
            String preco = charSequence.toString().replaceAll("[^\\d]", "");

            double parsed = Double.parseDouble(preco);

            String formatted = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(parsed/100);

            texto.setText(formatted);
            texto.setSelection(formatted.length());
        }

        texto.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {}

}


