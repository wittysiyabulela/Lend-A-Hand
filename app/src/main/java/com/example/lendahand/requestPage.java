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
    private EditText etQuantity, etDescription;
    private Spinner CollectionCentre;
    private TextView tvCharCount;
    private MaterialButton btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);

        btnBack = findViewById(R.id.btnBack);
        spinnerResourceType = findViewById(R.id.spinnerResourceType);
        etQuantity = findViewById(R.id.Quantity);
        etDescription = findViewById(R.id.Description);
        CollectionCentre = findViewById(R.id.CollectionCentre);

        tvCharCount = findViewById(R.id.tvCharCount);
        btnConfirm = findViewById(R.id.confirm_button);

        String[] resources = {"--Select Resource Type--", "Blanket", "Books", "Clothing", "Cosmetics", "Food","Gift Card", "Medication","Tutoring", "Transport","Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, resources);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerResourceType.setAdapter(adapter);

        String[] centres = {"--Select Collection Centre--","Braamfontein Post office", "Wits Post Office","Pietsburg Post Office(Polokwane)", "Mahikeng Post Office", "Kimberly Post Office","Port Elizabeth Post Office","Post Office Vlaeberg(Cape Town)","Durban Central Post Office","Nelspruit Post Office", "Bloemfontein Post Office"};
        ArrayAdapter<String> centreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, centres);
        centreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CollectionCentre.setAdapter(centreAdapter);


        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                tvCharCount.setText(length + " / 300");

                if(!s.toString().trim().isEmpty()){
                    try {
                        int value = Integer.parseInt(s.toString().trim());
                        if (value > 100) {
                            etQuantity.setError("Maximum 100 items allowed");
                        } else {
                            etQuantity.setError(null);
                        }
                    }catch(NumberFormatException e){
                        etQuantity.setError("Please enter a valid number");
                    }
                }
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
        String selectCentre = CollectionCentre.getSelectedItem().toString();
        String selectedResource = spinnerResourceType.getSelectedItem().toString();

        if(spinnerResourceType.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Please select a resource type", Toast.LENGTH_SHORT).show();
            return;
        }

        if(quantityStr.isEmpty()){
            etQuantity.setError("Please a enter a quantity");
            etQuantity.requestFocus();
            return;
        }

        int quantity = Integer.parseInt(quantityStr);
        if (quantity <= 0 || quantity > 100) {
            etQuantity.setError("Enter a number between 1 and 100");
            etQuantity.requestFocus();
            return;
        }

        if(description.isEmpty()){
            etDescription.setError("Please enter a description");
            etDescription.requestFocus();
            return;
        }

        if (description.length() > 300) {
            etDescription.setError("Description too long");
            etDescription.requestFocus();
            return;
        }
        if (CollectionCentre.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a collection centre", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Request for " + selectedResource + " submitted successfully!", Toast.LENGTH_LONG).show();

        finish();
    }
}