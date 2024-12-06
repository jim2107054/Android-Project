package com.example.findhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout imageBerlin, imageMunich, imageHamburg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize city items
        imageBerlin = findViewById(R.id.image_berlin);
        imageMunich = findViewById(R.id.image_munich);
        imageHamburg = findViewById(R.id.image_hamburg);

        // Set click listeners for city items
        imageBerlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCityDetailsBerlin("Berlin");
            }
        });

        imageMunich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCityDetailsMunich("Munich");
            }
        });

        imageHamburg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCityDetailsHamburg("Hamburg");
            }
        });
    }

    //Navigate to Home in Hamburg city.
    private void navigateToCityDetailsHamburg(String hamburg) {
        Intent intent = new Intent(MainActivity.this, CityDetailsActivityHamburg.class);
        intent.putExtra("cityName", hamburg);  // Pass city name to the new Activity
        startActivity(intent);
    }

    //Navigate to Home in Munich city.
    private void navigateToCityDetailsMunich(String munich) {
        Intent intent = new Intent(MainActivity.this, CityDetailsActivityMunich.class);
        intent.putExtra("cityName", munich);  // Pass city name to the new Activity
        startActivity(intent);
    }

    // Helper method to navigate to CityDetailsActivity
    private void navigateToCityDetailsBerlin(String berlin) {
        Intent intent = new Intent(MainActivity.this, CityDetailsActivityBerlin.class);
        intent.putExtra("cityName", berlin);  // Pass city name to the new Activity
        startActivity(intent);
    }
}
