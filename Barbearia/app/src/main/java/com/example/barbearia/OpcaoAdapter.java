package com.example.barbearia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OpcaoAdapter extends RecyclerView.Adapter<OpcaoAdapter.OpcaoViewHolder> {
    private Context context;
    private List<Opcao> opcaoList;
    private OnItemClickListener onItemClickListener;

    public OpcaoAdapter(Context context, List<Opcao> opcaoList) {
        this.context = context;
        this.opcaoList = opcaoList;
    }

    @Override
    public OpcaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_opcao, parent, false);
        return new OpcaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OpcaoViewHolder holder, int position) {
        Opcao opcao = opcaoList.get(position);
        holder.tituloOpcao.setText(opcao.getTitulo());
        holder.descricaoOpcao.setText(opcao.getDescricao());
        holder.precoOpcao.setText("R$ " + String.valueOf(opcao.getPreco()));
        holder.imgOpcao.setImageResource(opcao.getImagem());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(opcao);
        });
    }

    @Override
    public int getItemCount() { return opcaoList.size(); }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Opcao opcao);
    }

    public static class OpcaoViewHolder extends RecyclerView.ViewHolder {
        TextView tituloOpcao, descricaoOpcao, precoOpcao;
        ImageView imgOpcao;

        public OpcaoViewHolder(View itemView) {
            super(itemView);
            tituloOpcao = itemView.findViewById(R.id.tituloOpcao);
            descricaoOpcao = itemView.findViewById(R.id.descricaoOpcao);
            precoOpcao = itemView.findViewById(R.id.precoOpcao);
            imgOpcao = itemView.findViewById(R.id.imgOpcao);
        }
    }

}

