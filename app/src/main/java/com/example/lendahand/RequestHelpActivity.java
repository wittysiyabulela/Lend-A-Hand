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

public class RequestHelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_help);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText title = findViewById(R.id.requestTitle);
        TextInputEditText name = findViewById(R.id.requestName);
        TextInputEditText surname = findViewById(R.id.requestSurname);
        TextInputEditText description = findViewById(R.id.requestDescription);
        TextInputEditText location = findViewById(R.id.requestLocation);

        MaterialButton submit = findViewById(R.id.submitRequest);
        submit.setOnClickListener(v -> {
            String t = title.getText() == null ? "" : title.getText().toString().trim();
            String n = name.getText() == null ? "" : name.getText().toString().trim();
            String s = surname.getText() == null ? "" : surname.getText().toString().trim();
            String d = description.getText() == null ? "" : description.getText().toString().trim();
            String l = location.getText() == null ? "" : location.getText().toString().trim();

            if (t.isEmpty() || n.isEmpty() || s.isEmpty() || d.isEmpty() || l.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: send to backend / database
            Toast.makeText(this, "Request submitted (frontend only).", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ThankYouActivity.class));
            finish();
        });
    }
}

