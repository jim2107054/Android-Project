package com.example.findhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout imageBerlin, imageMunich, imageHamburg;
    private LinearLayout navSearch, navContact, navFavourite, navProfile, navHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize city items
        imageBerlin = findViewById(R.id.image_berlin);
        imageMunich = findViewById(R.id.image_munich);
        imageHamburg = findViewById(R.id.image_hamburg);

        // Initialize bottom navigation items
        navHome = findViewById(R.id.nav_search);
        navSearch = findViewById(R.id.nav_contact);
        navContact = findViewById(R.id.nav_myads);
        navFavourite = findViewById(R.id.nav_favourite);
        navProfile = findViewById(R.id.nav_profile);

        // Set click listeners for city items
        imageBerlin.setOnClickListener(v -> navigateToCityDetailsBerlin("Berlin"));
        imageMunich.setOnClickListener(v -> navigateToCityDetailsMunich("Munich"));
        imageHamburg.setOnClickListener(v -> navigateToCityDetailsHamburg("Hamburg"));

        // Set click listeners for navigation items
        navHome.setOnClickListener(v -> navigateToHome());
        //navSearch.setOnClickListener(v -> navigateToSearch());
        navContact.setOnClickListener(v -> navigateToContact());
        navFavourite.setOnClickListener(v -> navigateToFavourites());
        navProfile.setOnClickListener(v -> navigateToProfile());
    }

    // Navigation methods for cities
    private void navigateToCityDetailsBerlin(String cityName) {
        Intent intent = new Intent(MainActivity.this, CityDetailsActivityBerlin.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }

    private void navigateToCityDetailsMunich(String cityName) {
        Intent intent = new Intent(MainActivity.this, CityDetailsActivityMunich.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }

    private void navigateToCityDetailsHamburg(String cityName) {
        Intent intent = new Intent(MainActivity.this, CityDetailsActivityHamburg.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }

    // Navigation methods for bottom navigation
    private void navigateToHome() {
        // Already on Home, no action needed
    }

//    private void navigateToSearch() {
//        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//        startActivity(intent);
//    }

    private void navigateToContact() {
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }

    private void navigateToFavourites() {
        Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);
        startActivity(intent);
    }

    private void navigateToProfile() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
