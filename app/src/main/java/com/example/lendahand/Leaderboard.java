package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Leaderboard extends AppCompatActivity {

    private TextView Pos1Name, Pos1Count, Pos2Name, Pos2Count, Pos3Name, Pos3Count;
    private EditText searchBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leaderboard);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Edge-to-edge padding
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                );
                return insets;
            });
        }

        // Top 3 leaderboard views
        Pos1Name = findViewById(R.id.Pos1Name);
        Pos1Count = findViewById(R.id.Pos1Count);

        Pos2Name = findViewById(R.id.Pos2Name);
        Pos2Count = findViewById(R.id.Pos2Count);

        Pos3Name = findViewById(R.id.Pos3Name);
        Pos3Count = findViewById(R.id.Pos3Count);

        // Search bar
        searchBar = findViewById(R.id.searchBar);

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Search filtering
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Bottom Navigation
        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);

        bottomnav.setSelectedItemId(R.id.leaderboard);

        bottomnav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.home) {

                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;

            } else if (id == R.id.profile) {

                startActivity(new Intent(this, UserProfileActivity.class));
                finish();
                return true;

            } else if (id == R.id.leaderboard) {

                return true;
            }

            return false;
        });
    }

    private void filter(String text) {

        // Example:
        // adapter.getFilter().filter(text);

    }
}