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
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;


public class Leaderboard extends AppCompatActivity {

    private TextView Pos1Name, Pos1Count, Pos2Name, Pos2Count, Pos3Name, Pos3Count;
    private EditText searchBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leaderboard);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

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

        Pos1Name = findViewById(R.id.Pos1Name);
        Pos1Count = findViewById(R.id.Pos1Count);

        Pos2Name = findViewById(R.id.Pos2Name);
        Pos2Count = findViewById(R.id.Pos2Count);

        Pos3Name = findViewById(R.id.Pos3Name);
        Pos3Count = findViewById(R.id.Pos3Count);

        /* Commented this out since its causing errors
        Pos1Pic = findViewById(R.id.Pos1Pic);
        Pos1Pic = findViewById(R.id.Pos2Pic);
        Pos1Pic = findViewById(R.id.Pos2Pic);*/

        // -------------------------------------------------------
        // TODO FOR BACKEND TEAMMATE:
        // After fetching top 3 donors from get_top_donators.php
        // set their names and donation counts like this:
        //
        //   Pos1Name.setText(donor1.getFirstName());
        //   Pos1Count.setText(String.valueOf(donor1.getItemsDonated()));
        //   Pos2Name.setText(donor2.getFirstName());
        //   Pos2Count.setText(String.valueOf(donor2.getItemsDonated()));
        //   Pos3Name.setText(donor3.getFirstName());
        //   Pos3Count.setText(String.valueOf(donor3.getItemsDonated()));
        //
        // Then load their profile pictures using Glide:
        //   Glide.with(this).load(donor1.getAvatarUrl()).circleCrop().into(Pos1Pic);
        //   Glide.with(this).load(donor2.getAvatarUrl()).circleCrop().into(Pos2Pic);
        //   Glide.with(this).load(donor3.getAvatarUrl()).circleCrop().into(Pos3Pic);
        //
        // Then pass rank 4+ donors to the RecyclerView adapter:
        //   DonorAdapter adapter = new DonorAdapter(this, donorList);
        //   recyclerView.setAdapter(adapter);
        //
        // For the search filter uncomment and use:
        //   adapter.getFilter().filter(text);
        // -------------------------------------------------------


        searchBar = findViewById(R.id.searchBar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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