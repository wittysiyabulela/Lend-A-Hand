package com.example.lendahand;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class requestPage extends AppCompatActivity {

    private ImageButton btnBack;
    private Spinner spinnerResourceType;
    private EditText etQuantity, etDescription, etLocation;
    private TextView tvCharCount;
    private MaterialButton btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);

        btnBack = findViewById(R.id.btnBack);
        spinnerResourceType = findViewById(R.id.spinnerResourceType);
        etQuantity = findViewById(R.id.Quantity); // Matches android:id="@+id/Quantity"
        etDescription = findViewById(R.id.Description); // Matches android:id="@+id/Description"
        etLocation = findViewById(R.id.etLocation);
        tvCharCount = findViewById(R.id.tvCharCount);
        btnConfirm = findViewById(R.id.confirm_button); // Matches android:id="@+id/confirm_button"

        String[] resources = {"Other", "Clothing", "Tutoring", "Transport", "Food", "Cosmetics", "Blanket", "Books", "Gift Card"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, resources);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerResourceType.setAdapter(adapter);

        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                tvCharCount.setText(length + " / 300");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnBack.setOnClickListener(v -> finish());

        btnConfirm.setOnClickListener(v -> {
            submitRequest();
        });
    }

    private void submitRequest() {
        String quantityStr = etQuantity.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String selectedResource = spinnerResourceType.getSelectedItem().toString();


        if (quantityStr.isEmpty() || description.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityStr);

        if (quantity <= 0 || quantity > 100) {
            etQuantity.setError("Enter a number between 1 and 100");
            return;
        }

        if (description.length() > 300) {
            etDescription.setError("Description too long");
            return;
        }


        Toast.makeText(this, "Request for " + selectedResource + " submitted successfully!", Toast.LENGTH_LONG).show();

        finish();
    }
}