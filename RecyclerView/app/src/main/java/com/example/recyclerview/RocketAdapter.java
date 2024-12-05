package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.ViewHolder> {
//    lista de todos os itens que seram apresentados na tela
    ArrayList<RocketModel> rocketModels;

    public RocketAdapter(ArrayList<RocketModel> rocketModels) {
        this.rocketModels = rocketModels;
    }

    /*
     * método para atribuir os componentes do padrão do list_item (xml)
     * em váriaveis que seram manipuladas por outros métodos
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoguete;
        TextView rocket_name;
        TextView launch_date;
        TextView launch_success;
        TextView payload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoguete  = itemView.findViewById(R.id.imgFoguete);
            rocket_name = itemView.findViewById(R.id.rocket_name);
            launch_date = itemView.findViewById(R.id.launch_date);
            launch_success = itemView.findViewById(R.id.launch_success);
            payload = itemView.findViewById(R.id.payload);
        }

    }

    /*
    * passa como atributo ao método acima o xml list_item,
    * ele será o padrão usado para todos as informações que serão adicionadas
    * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new ViewHolder(view);
    }

    /*
    * atribui todos os valores encapsulados em RocketModel aos componentes de um list_item,
    * tratado com um ViewHolder pelo método mais acima
    * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RocketModel rocketModel = rocketModels.get(position);

        holder.imgFoguete.setImageResource(rocketModel.getImgFoguete());

        holder.rocket_name.setText("Rocket: " + rocketModel.getRocketName());
        holder.launch_date.setText("Launch Date: " + rocketModel.getLaunchDate());

        if (rocketModel.isLaunchSuccess()) {
            holder.launch_success.setText("Launch Succeeded");
        } else {
            holder.launch_success.setText("Launch Failed");
        }

        holder.payload.setText("Payload: " + rocketModel.getPayload());
    }

    @Override
    public int getItemCount() {
        return rocketModels.size();
    }
}
