package com.example.pizzaria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    // lista de pizzas
    ArrayList<PizzaModel> pizzaModels;

    public PizzaAdapter(ArrayList<PizzaModel> pizzaModels) {
        this.pizzaModels = pizzaModels;
    }

    // classe interna que representa cada item de pizza na tela
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView nome, valor, ingredientes, quantidade;
        Button mais, menos;

        // associa os layout aos atributos da classe view Holder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            nome = itemView.findViewById(R.id.nome);
            valor = itemView.findViewById(R.id.valor);
            ingredientes = itemView.findViewById(R.id.ingredientes);
            mais = itemView.findViewById(R.id.mais);
            menos = itemView.findViewById(R.id.menos);
            quantidade = itemView.findViewById(R.id.quantidade);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // método que recebe o ViewHolder e associa cada valor aos componentes respectivos do item da lista
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PizzaModel pizzaModel = pizzaModels.get(position);

        // associa os valores da classe modelo ao layout
        holder.img.setImageResource(pizzaModel.getImagem());
        holder.nome.setText(pizzaModel.getNome());
        holder.valor.setText("R$ " + String.valueOf(pizzaModel.getValor()));
        holder.ingredientes.setText(pizzaModel.getIngredientes());

        // métodos que mudam a quantidade do item
        holder.mais.setOnClickListener(v -> {
            pizzaModel.setQuantidade(pizzaModel.getQuantidade()+1);
            holder.quantidade.setText(String.valueOf(pizzaModel.getQuantidade()));
        });

        holder.menos.setOnClickListener(v -> {
            if (pizzaModel.getQuantidade() > 0) {
                pizzaModel.setQuantidade(pizzaModel.getQuantidade() - 1);
                holder.quantidade.setText(String.valueOf(pizzaModel.getQuantidade()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzaModels.size();
    }
}
