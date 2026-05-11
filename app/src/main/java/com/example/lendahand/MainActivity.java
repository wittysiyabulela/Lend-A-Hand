package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.home);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.profile) {
                startActivity(new Intent(this, UserProfileActivity.class));
                finish();
                return true;
            } else if (id == R.id.leaderboard) {
                startActivity(new Intent(this, Leaderboard.class));
                finish();
                return true;
            } else if (id == R.id.home) {
                return true;
            }
            return false;
        });

        MaterialButton requestButton = findViewById(R.id.requestHelpButton);
        requestButton.setOnClickListener(v -> startActivity(new Intent(this, RequestHelpActivity.class)));

        RecyclerView feed = findViewById(R.id.feedRecycler);
        feed.setLayoutManager(new LinearLayoutManager(this));

        // Demo data for now (replace with DB/API later)
        List<RequestItem> demo = new ArrayList<>();
        demo.add(new RequestItem("Baby clothes", "Newborn clothes needed for a family.", "Braamfontein", "Thando M."));
        demo.add(new RequestItem("Blankets", "Warm blankets for winter nights.", "Soweto", "Ayesha K."));
        demo.add(new RequestItem("Food parcel", "Basic groceries for the week.", "Parktown", "Sipho N."));

        feed.setAdapter(new RequestAdapter(demo));
    }

    static class RequestItem {
        final String title;
        final String description;
        final String location;
        final String requesterName;

        RequestItem(String title, String description, String location, String requesterName) {
            this.title = title;
            this.description = description;
            this.location = location;
            this.requesterName = requesterName;
        }
    }

    static class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.VH> {
        private final List<RequestItem> items;

        RequestAdapter(List<RequestItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_request_card, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            RequestItem item = items.get(position);
            holder.title.setText(item.title);
            holder.description.setText(item.description);
            holder.location.setText(item.location);
            holder.requester.setText(item.requesterName);

            holder.donateButton.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DonationActivity.class);
                intent.putExtra(DonationActivity.EXTRA_REQUEST_TITLE, item.title);
                v.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        static class VH extends RecyclerView.ViewHolder {
            final TextView title;
            final TextView description;
            final TextView location;
            final TextView requester;
            final MaterialButton donateButton;

            VH(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.reqTitle);
                description = itemView.findViewById(R.id.reqDescription);
                location = itemView.findViewById(R.id.reqLocation);
                requester = itemView.findViewById(R.id.reqRequester);
                donateButton = itemView.findViewById(R.id.donateButton);
            }
        }
    }
}