package com.example.lendahand;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class leaderboard extends AppCompatActivity {

    TextView Pos1Name, Pos1Count;
    TextView Pos2Name, Pos2Count;
    TextView Pos3Name, Pos3Count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leaderboard);

        Pos1Name = findViewById(R.id.Pos1Name);
        Pos1Count = findViewById(R.id.Pos1Count);

        Pos2Name = findViewById(R.id.Pos2Name);
        Pos2Count = findViewById(R.id.Pos2Count);

        Pos3Name = findViewById(R.id.Pos3Name);
        Pos3Count = findViewById(R.id.Pos3Count);

        RecyclerView recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));



        BottomNavigationView bottomnav= findViewById(R.id.bottom_navigation);
        bottomnav.setSelectedItemId(R.id.leaderboard);
    }
}