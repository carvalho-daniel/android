package com.example.pizzaria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recyclerView);

        ArrayList<PizzaModel> pizzaModels = new ArrayList<>();

        // nome, ingredientes, valor, imagem
        PizzaModel pizza1  = new PizzaModel("Calabresa", "calabresa, queijo", 29.90, R.drawable.calabresa);
        PizzaModel pizza2  = new PizzaModel("Portuguesa", "Ovos, cebola, azeitona, ervilha, queijo e presunto", 29.90, R.drawable.portuguesa);
        PizzaModel pizza3  = new PizzaModel("Marguerita", "Molho, muçarela, tomate e manjericão", 29.90, R.drawable.marguerita);
        PizzaModel pizza4  = new PizzaModel("Frango com catupiry", " frango com catupiry, queijo, milho", 29.90, R.drawable.frango_catupiry);
        PizzaModel pizza5  = new PizzaModel("Mussarela", "mussarela, molho, milho, ervilha", 29.90, R.drawable.mussarela);
        PizzaModel pizza6  = new PizzaModel("Napolitana", "mussarela, azeite, molho de tomate e manjericão", 29.90, R.drawable.napolitana);
        PizzaModel pizza7  = new PizzaModel("Lombo canadense", "Lombinho, cebola, bacon e champignon", 29.90, R.drawable.lombo_canadense);
        PizzaModel pizza8  = new PizzaModel("Quatro queijos", "Gorgonzola, provolone, muçarela e parmesão", 29.90, R.drawable.quatro_queijo);
        PizzaModel pizza9  = new PizzaModel("Brigadeiro", "Queijo, brigadeiro", 29.90, R.drawable.brigadeiro);
        PizzaModel pizza10 = new PizzaModel("Romeu e Julieta", "Queijo branco e goiabada", 29.90, R.drawable.romeu_julieta);
        PizzaModel pizza11 = new PizzaModel("Chocolate com morango", "Queijo, chocolate branco, morango", 29.90, R.drawable.branco_morango);
        PizzaModel pizza12 = new PizzaModel("Avelã com frutas", "Morango, abacaxi, kiwi, creme de avelã", 29.90, R.drawable.creme_frutas);
        PizzaModel pizza13 = new PizzaModel("Nutella", "creme de avelã", 29.90, R.drawable.nutella);
        PizzaModel pizza14 = new PizzaModel("Banana com canela", "banoffee, canela", 29.90, R.drawable.banana_canela);
        PizzaModel pizza15 = new PizzaModel("Califórnia", "Queijo, pêssego, goiaba, figo e abacaxi", 29.90, R.drawable.california);
        PizzaModel pizza16 = new PizzaModel("Pepperoni", "Queijo mussarela, fatias de pepperoni, linguiça feita de carne condimentada picante e defumada", 29.90, R.drawable.pepperoni);
        PizzaModel pizza17 = new PizzaModel("Alho-poró com bacon", "Queijo, bacon refogado com alho-poró", 29.90, R.drawable.alho_bacon);
        PizzaModel pizza18 = new PizzaModel("Carne seca com catupiry", "Carne com temperos diversos, queijo, catupiry", 29.90, R.drawable.carne_seca);
        PizzaModel pizza19 = new PizzaModel("Costela bovina", "Costela bovina, queijo, molho bbq, cheddar", 29.90, R.drawable.costela);

        pizzaModels.add(pizza1);
        pizzaModels.add(pizza2);
        pizzaModels.add(pizza3);
        pizzaModels.add(pizza4);
        pizzaModels.add(pizza5);
        pizzaModels.add(pizza6);
        pizzaModels.add(pizza7);
        pizzaModels.add(pizza8);
        pizzaModels.add(pizza9);
        pizzaModels.add(pizza10);
        pizzaModels.add(pizza11);
        pizzaModels.add(pizza12);
        pizzaModels.add(pizza13);
        pizzaModels.add(pizza14);
        pizzaModels.add(pizza15);
        pizzaModels.add(pizza16);
        pizzaModels.add(pizza17);
        pizzaModels.add(pizza18);
        pizzaModels.add(pizza19);

        RecyclerView.Adapter<PizzaAdapter.ViewHolder> adapter = new PizzaAdapter(pizzaModels);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}