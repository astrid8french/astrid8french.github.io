/*
 * Name: Astrid French
 * Date: 15 August 2024
 * Prof. Jerome DiMarzio
 * CS360 - Project Three
 */

package com.example.myinventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myinventory.database.DataBaseUser;
import com.example.myinventory.database.User;

public class LoginActivity extends Activity {
    // Declare variables
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private DataBaseUser db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set layout from XML
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        // Connect to database for User
        db = new DataBaseUser(this);

        loginButton.setOnClickListener(v -> {
            // Get user input
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Fetch user from database
            User user = db.getUserByUsername(username);

            // Validate credentials
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // Navigate to ListActivity with username
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    intent.putExtra("username", user.getUsername()); // Pass username
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
