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
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText password = findViewById(R.id.password);
        TextInputLayout emailLayout = findViewById(R.id.emailLayout);
        TextInputLayout passwordLayout = findViewById(R.id.passwordLayout);

        MaterialButton login = findViewById(R.id.loginButton);
        MaterialButton register = findViewById(R.id.goRegisterButton);

        login.setOnClickListener(v -> {
            String e = email.getText() == null ? "" : email.getText().toString().trim();
            String p = password.getText() == null ? "" : password.getText().toString().trim();

            emailLayout.setError(null);
            passwordLayout.setError(null);

            if (e.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Enter email and password.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!SessionManager.hasAccount(this)) {
                Toast.makeText(this, "No account found. Please create an account.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!SessionManager.emailExists(this, e)) {
                emailLayout.setError("No account found for this email");
                Toast.makeText(this, "No account found for this email.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!SessionManager.credentialsMatch(this, e, p)) {
                passwordLayout.setError("Incorrect password");
                Toast.makeText(this, "Incorrect password. Please try again.", Toast.LENGTH_SHORT).show();
                return;
            }

            SessionManager.setLoggedIn(this, true);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        register.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}