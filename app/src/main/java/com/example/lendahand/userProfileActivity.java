package com.example.lendahand;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class userProfileActivity extends AppCompatActivity {

    TextView userName;
    TextView userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;


        userName = findViewById(R.id.userName);
        userLocation= findViewById(R.id.userLocation);

     TabLayout tabLayout = findViewById(R.id.tabLayout);
     ViewPager2 viewPager = findViewById(R.id.viewPager);
     BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
     bottomNav.setSelectedItemId(R.id.bottom_navigation);

     //<!--fetch user info from the database-->
     userName.setText("--user name--");
     userLocation.setText("--user location");

    //counter - need the actual requests/donations from DB
    new TabLayoutMediator(tabLayout,viewPager, (tab,position)->{
        if(position == 0) tab.setText("Requested (0)");
        else tab.setText("Donated (0)");
    }).attach();
        });
    }
}