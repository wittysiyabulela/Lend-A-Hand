package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class UserProfileActivity extends AppCompatActivity {

    TextView userName;
    TextView userLocation;
    MaterialButton requestButton;
    ShapeableImageView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);

        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        userName = findViewById(R.id.userName);
        userLocation = findViewById(R.id.userLocation);
        requestButton = findViewById(R.id.requestButton);
        profilePic = findViewById(R.id.profilePic);


        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.profile);

        ProfileViewPagerAdapter adapter = new ProfileViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // -------------------------------------------------------
        // TODO FOR BACKEND TEAMMATE:
        // After fetching the user from the database:
        //
        // 1. Set the user's name and location:
        //    userName.setText(user.getFirstName() + " " + user.getLastName());
        //    userLocation.setText(user.getLocation());
        //
        // 2. Load their profile picture using Glide:
        //    Glide.with(this)
        //         .load(user.getAvatarUrl())
        //         .circleCrop()
        //         .into(profilePic);
        //
        // The same Avatar_URL should also be loaded on:
        //    - Leaderboard podium (Pos1Pic, Pos2Pic, Pos3Pic)
        //    - Leaderboard RecyclerView rows (DonorPic)
        // -------------------------------------------------------

        userName.setText("John Doe");
        userLocation.setText("Johannesburg, SA");

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) tab.setText("Requested");
            else tab.setText("Donated");
        }).attach();


        requestButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, requestPage.class);
            startActivity(intent);
        });

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

                return true;
            } else if (id == R.id.leaderboard) {
                startActivity(new Intent(this, Leaderboard.class));
                finish();
                return true;
            }
            return id == R.id.profile;
        });
    }
}