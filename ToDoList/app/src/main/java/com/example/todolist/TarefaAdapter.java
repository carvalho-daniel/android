package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    private Context context;
    private List<Tarefa> tarefaList;
    private OnItemClickListener onItemClickListener;

    public TarefaAdapter(Context context, List<Tarefa> tarefaList) {
        this.context = context;
        this.tarefaList = tarefaList;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int position) {
        Tarefa tarefa = tarefaList.get(position);
        holder.titulo.setText(tarefa.getNome());
        holder.check.setChecked(tarefa.isCompleta());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(tarefa);
        });

        holder.check.setOnClickListener(v -> {
            if(holder.check.isChecked())
                tarefa.setCompleta(true);
            else
                tarefa.setCompleta(false);
        });

        holder.editar.setOnClickListener(v->{
            final EditText novoTitulo = new EditText(holder.itemView.getContext());
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Editar tarefa")
                    .setMessage(holder.titulo.getText().toString())
                    .setView(novoTitulo)
                    .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String novoNome = novoTitulo.getText().toString();
                            holder.titulo.setText(novoNome);
                            dialog.dismiss(); // fecha o dialog
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // fecha o dialog ao pressionar "Cancelar"
                        }
                    })
                    .setNeutralButton("Excluir", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.excluir(holder.getAdapterPosition());
                        }
                    })
                    .setCancelable(false) // impede o fechamento do diálogo ao tocar fora dele
                    .show();
        });

    }

    @Override
    public int getItemCount() { return tarefaList.size(); }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Tarefa tarefa);
    }


    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        CheckBox check;
        ImageButton editar;

        public TarefaViewHolder(View itemView) {
            super((itemView));
            titulo = itemView.findViewById(R.id.nomeTarefa);
            check = itemView.findViewById(R.id.check);
            editar = itemView.findViewById(R.id.editar);
        }


    }
}
