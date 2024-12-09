package com.example.findhome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput, deptInput;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth and Database Reference
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        // Initialize Views
        emailInput = findViewById(R.id.etEmailAddress);
        passwordInput = findViewById(R.id.etPassword);
        deptInput = findViewById(R.id.etDept);
        signUpButton = findViewById(R.id.btnSignUp);

        // SignUp button click listener
        signUpButton.setOnClickListener(v -> performSignUp());

        // Redirect to login activity
        TextView loginText = findViewById(R.id.etAccountExist);
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void performSignUp() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String dept = deptInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || dept.isEmpty()) {
            Toast.makeText(this, "Please fill all details.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {
                    String userId = firebaseUser.getUid();
                    User user = new User(password, dept, email);

                    // Save user data in Firebase Realtime Database
                    database.child("users").child(userId).setValue(user).addOnCompleteListener(dbTask -> {
                        if (dbTask.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish(); // Close SignUpActivity
                        } else {
                            Log.e("DatabaseError", "Error saving user data: " + dbTask.getException().getMessage());
                            Toast.makeText(SignUpActivity.this, "Failed to save user data.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Log.e("SignUpError", "Error: " + task.getException().getMessage());
                Toast.makeText(SignUpActivity.this, "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

// User model class
class User {
    public String password, dept, email;

    public User() {
    } // Default constructor required for Firebase

    public User(String password, String dept, String email) {
        this.password = password;
        this.dept = dept;
        this.email = email;
    }
}
