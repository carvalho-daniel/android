package com.example.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recyclerView);

        int img1 = R.drawable.foguete1;
        int img2 = R.drawable.foguete2;

        RocketModel rocketModel_1 = new RocketModel(img1,"falcon 1", "06/11/2024", true, "satellite");
        RocketModel rocketModel_2 = new RocketModel(img2,"falcon 2", "06/11/2024", true, "satellite");
        RocketModel rocketModel_3 = new RocketModel(img1,"dragon 2", "06/11/2024", false, "satellite");
        RocketModel rocketModel_4 = new RocketModel(img2,"dragon 3", "06/11/2024", false, "satellite");
        RocketModel rocketModel_5 = new RocketModel(img1,"dragon 4", "06/11/2024", false, "satellite");
        RocketModel rocketModel_6 = new RocketModel(img2,"dragon 5", "06/11/2024", false, "satellite");
        RocketModel rocketModel_7 = new RocketModel(img1,"dragon 6", "06/11/2024", false, "satellite");
        RocketModel rocketModel_8 = new RocketModel(img2,"dragon 7", "06/11/2024", false, "satellite");
        RocketModel rocketModel_9 = new RocketModel(img1,"dragon 8", "06/11/2024", false, "satellite");

        ArrayList<RocketModel> rocketModels = new ArrayList<>();
        rocketModels.add(rocketModel_1);
        rocketModels.add(rocketModel_2);
        rocketModels.add(rocketModel_3);
        rocketModels.add(rocketModel_4);
        rocketModels.add(rocketModel_5);
        rocketModels.add(rocketModel_6);
        rocketModels.add(rocketModel_7);
        rocketModels.add(rocketModel_8);
        rocketModels.add(rocketModel_9);

        RecyclerView.Adapter<RocketAdapter.ViewHolder> adapter = new RocketAdapter(rocketModels);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}