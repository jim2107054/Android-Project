package com.example.findhome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Delay the splash screen for 3 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash_Screen.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }
}
