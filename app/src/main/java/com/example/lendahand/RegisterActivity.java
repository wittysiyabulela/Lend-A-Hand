package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText name = findViewById(R.id.name);
        TextInputEditText surname = findViewById(R.id.surname);
        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText password = findViewById(R.id.password);

        MaterialButton register = findViewById(R.id.registerButton);
        MaterialButton goLogin = findViewById(R.id.goLoginButton);
        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        register.setOnClickListener(v -> {
            String n = name.getText() == null ? "" : name.getText().toString().trim();
            String s = surname.getText() == null ? "" : surname.getText().toString().trim();
            String e = email.getText() == null ? "" : email.getText().toString().trim();
            String p = password.getText() == null ? "" : password.getText().toString().trim();

            if (n.isEmpty() || s.isEmpty() || e.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            SessionManager.saveCredentials(this, e, p);
            SessionManager.setLoggedIn(this, true);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        goLogin.setOnClickListener(v -> finish());
    }
}

