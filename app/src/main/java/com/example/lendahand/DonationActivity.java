package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DonationActivity extends AppCompatActivity {
    public static final String EXTRA_REQUEST_TITLE = "extra_request_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String requestTitle = getIntent().getStringExtra(EXTRA_REQUEST_TITLE);

        TextInputEditText item = findViewById(R.id.donationItem);
        TextInputEditText quantity = findViewById(R.id.donationQuantity);
        TextInputEditText note = findViewById(R.id.donationNote);

        MaterialButton donate = findViewById(R.id.donateNowButton);
        donate.setOnClickListener(v -> {
            String i = item.getText() == null ? "" : item.getText().toString().trim();
            String q = quantity.getText() == null ? "" : quantity.getText().toString().trim();

            if (i.isEmpty() || q.isEmpty()) {
                Toast.makeText(this, "Please fill in item and quantity.", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: send donation to backend (OkHTTP)
            Intent intent = new Intent(this, ThankYouActivity.class);
            intent.putExtra("requestTitle", requestTitle);
            startActivity(intent);
            finish();
        });
    }
}

