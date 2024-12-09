package com.example.findhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity{
    private DatabaseReference database; // Firebase Database Reference
    private String userId = "user"; // Replace with Firebase Authentication UID if integrated
    private Button api;
    int user = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().getReference("users");

        // Load user data
        user++;
        userId = userId + String.valueOf(user);
        loadUserProfile(userId);

        // Edit Profile Button Listener
        Button editProfileButton = findViewById(R.id.btnEditProfile);
        editProfileButton.setOnClickListener(v -> openEditDialog());

        // Delete Profile Button Listener
        Button deleteProfileButton = findViewById(R.id.btnDelete);
        deleteProfileButton.setOnClickListener(v -> confirmDeleteProfile());
    }

    // Method to load user profile (READ operation)
    private void loadUserProfile(String userId) {
        database.child(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    UserProfile userProfile = task.getResult().getValue(UserProfile.class);
                    if (userProfile != null) {
                        updateUI(userProfile);
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, "No profile found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ProfileActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Get PostCode through API.
    //api = findViewById(R.id.btnGetCode);

    // Method to update UI dynamically
    private void updateUI(UserProfile userProfile) {
        ((TextView) findViewById(R.id.etName)).setText("Name: " + userProfile.getName());
        ((TextView) findViewById(R.id.etProfileName)).setText(userProfile.getName());
        ((TextView) findViewById(R.id.etEmail)).setText("Email: " + userProfile.getEmail());
        ((TextView) findViewById(R.id.etProfileEmail)).setText(userProfile.getEmail());
        ((TextView) findViewById(R.id.etDept)).setText("Department: " + userProfile.getDepartment());
        ((TextView) findViewById(R.id.etDivision)).setText("Division: " + userProfile.getDivision());
        ((TextView) findViewById(R.id.etPhone)).setText("Phone: " + userProfile.getPhone());
        ((TextView) findViewById(R.id.etPostCode)).setText("Post Code: " + userProfile.getPostCode());
    }

    // Method to open Edit Profile dialog
    private void openEditDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.edit_profile_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).create();

        EditText editName = dialogView.findViewById(R.id.etName);
        EditText editEmail = dialogView.findViewById(R.id.etEmail);
        EditText editDepartment = dialogView.findViewById(R.id.etDept);
        EditText editDivision = dialogView.findViewById(R.id.etDivision);
        EditText editPhone = dialogView.findViewById(R.id.etPhone);
        EditText editPostCode = dialogView.findViewById(R.id.etPostCode);

        Button saveButton = dialogView.findViewById(R.id.saveButton);
        Button getCode = dialogView.findViewById(R.id.btnGetCode);

        getCode.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, GetPostCode.class);
            startActivity(intent);
        });

        saveButton.setOnClickListener(saveView -> {
            // Save the updated profile (CREATE/UPDATE operation)
            UserProfile updatedProfile = new UserProfile(
                    editName.getText().toString(),
                    editEmail.getText().toString(),
                    editDepartment.getText().toString(),
                    editDivision.getText().toString(),
                    editPhone.getText().toString(),
                    editPostCode.getText().toString()
            );

            saveUserProfile(userId, updatedProfile);
            dialog.dismiss();
        });

        dialog.show();
    }

    // Method to save/update user profile in Firebase
    private void saveUserProfile(String userId, UserProfile userProfile) {
        database.child(userId).setValue(userProfile).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to confirm deletion of profile
    private void confirmDeleteProfile() {
        new AlertDialog.Builder(ProfileActivity.this)
                .setMessage("Are you sure you want to delete your profile?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> deleteProfile())
                .setNegativeButton("No", null)
                .create()
                .show();
    }

    // Method to delete profile from Firebase
    private void deleteProfile() {
        database.child(userId).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ProfileActivity.this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();
                // Redirect to Splash Screen
                startActivity(new Intent(ProfileActivity.this, Splash_Screen.class));
                finish(); // Close the Profile Activity
            } else {
                Toast.makeText(ProfileActivity.this, "Failed to delete profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
