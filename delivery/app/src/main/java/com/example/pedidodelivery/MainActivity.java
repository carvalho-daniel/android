package com.example.pedidodelivery;

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

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText editNome;
    Button btnMenos, btnMais, btnPedir;
    TextView txtQtd, txtTotal;

    // Valores usados com frequência declarados como constantes
    final int PRECO = 15;
    final int MAXIMO = 10;

    double pctDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // botões usados nas funções
        btnMenos = findViewById(R.id.btnMenos);
        btnMais = findViewById(R.id.btnMais);
        btnPedir = findViewById(R.id.btnPedir);
    }

    /**/
    public void onClickMenos(View view) {
        botoes("MENOS");
    }

    /**/
    public void onCLickMais(View view) {
        botoes("MAIS");
    }

    /*
      Função que recebe qual o botão está sendo apertado ( em formato de String ex: "MAIS" )
    */
    private void botoes(final String OP) {
        // TextView da quantidade do pedido e subtotal
        TextView txtQtd_1 = findViewById(R.id.txtQtd);
        TextView txtTotal = findViewById(R.id.txtTotal);

        // transformando o texto do TextView em inteiro para facilitar as comparações
        int qtd = Integer.parseInt(txtQtd_1.getText().toString());

        /*
         * Se o botão apertado for mais o valor a ser somado sera 1,
         * caso contrário o valor sera -1 fazendo ele decrementar
        */
        int i = OP.equals("MAIS") ? 1 : -1;
        qtd += i;

        /*
         * Caso a quantidade do pedido seja zero, o botão de menos sera desabilitado,
         * caso contrário sera habilitado
        */
        if (qtd == 0)
            this.btnMenos.setEnabled(false);
        else
            this.btnMenos.setEnabled(true);

        /*
         * Caso a quantidade do pedido seja o máximo, o botão de mais sera desabilitado,
         * caso contrário seja habilitado
        */
        if (qtd == MAXIMO)
            this.btnMais.setEnabled(false);
        else
            this.btnMais.setEnabled(true);

        double total = qtd * PRECO;
        double desconto = 0;

        if (qtd > 5) {
            pctDesc = qtd;
            pctDesc /= 100;
            desconto = total * pctDesc; // constante de desconto
        }
        DecimalFormat df = new DecimalFormat("#.##");
        total -= desconto;

        // Mostrar o subtotal conforme o usuário clica nos botões de incremento e decremento
        txtTotal.setText("Sub total: R$" + total + "\n Desconto: R$" + df.format(desconto));
        txtQtd_1.setText(String.valueOf(qtd));
    }

    /*
    * */
    public void onClickPedir(View view) {
        EditText edtNome = findViewById(R.id.editNome);
        TextView txtQtd_1 = findViewById(R.id.txtQtd);
        TextView txtTotal = findViewById(R.id.txtTotal);

        /*
        * fazer o total ficar embaixo
        * e mudar o id ( tirar a msg "sub total:"
        * */

        // Desabilitar os botões quando o pedido for realizado
        this.btnMais.setEnabled(false);
        this.btnMenos.setEnabled(false);
        this.btnPedir.setEnabled(false);

        // Nome que o usuário digitou
        String nome = edtNome.getText().toString();

        int qtd = Integer.parseInt(txtQtd_1.getText().toString());
        double desconto = 0;
        double total;

        total = (PRECO * qtd);

        // Se a quantidade do pedido for maior que 5 terá um desconto de 0.05
        if (qtd > 5) {
            pctDesc = qtd;
            pctDesc /= 100;
            desconto = total * pctDesc; // constante de desconto
        }

        total -= desconto;

        if (qtd == 0) {
            Toast.makeText(getApplicationContext(), "O pedido não pode ser zero!", Toast.LENGTH_SHORT).show();
        } else {
            // Caso o nome esteja vazio o nome vai apenas para "cliente"
            if (nome.isEmpty())
                nome = "cliente";

            String msgFinal = "Obrigado " + nome + " por pedir,\no total é de R$" + total + "\n Com desconto de R$" + desconto;

            txtTotal.setText(msgFinal);
        }


    }


}