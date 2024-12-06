package com.example.findhome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CityDetailsActivityMunich extends AppCompatActivity {

    private ImageView cityImage;
    private TextView cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details_munich);

        String city = getIntent().getStringExtra("cityName");

        // Initialize views


        // Set data based on the selected city
        if (city != null) {
            if (city.equals("Hamburg")) {
//                cityImage = findViewById(R.id.imageh);
//                cityName = findViewById(R.id.textb1);
//
//
//                cityImage.setImageResource(R.drawable.imageb1);  // Use your own image resource
//                cityName.setText("Abul's 1st House.");
//
//
//                cityImage = findViewById(R.id.imageb2);
//                cityName = findViewById(R.id.textb2);
//
//
//                cityImage.setImageResource(R.drawable.imageb2);  // Use your own image resource
//                cityName.setText("Abul's 2nd House.");
//
//                cityImage = findViewById(R.id.imageb3);
//                cityName = findViewById(R.id.textb3);
//
//
//                cityImage.setImageResource(R.drawable.imageb3);  // Use your own image resource
//                cityName.setText("Abul's 3rd House.");

                city = null;

                // ImageView references
                ImageView house1Image = findViewById(R.id.imagem);
                ImageView house2Image = findViewById(R.id.imagem1);
                ImageView house3Image = findViewById(R.id.imagem2);

                // OnClickListeners for each image
                house1Image.setOnClickListener(v -> {
                    Intent intent = new Intent(CityDetailsActivityMunich.this, House1Activity.class);
                    startActivity(intent);
                });

                house2Image.setOnClickListener(v -> {
                    Intent intent = new Intent(CityDetailsActivityMunich.this, House2Activity.class);
                    startActivity(intent);
                });

                house3Image.setOnClickListener(v -> {
                    Intent intent = new Intent(CityDetailsActivityMunich.this, House3Activity.class);
                    startActivity(intent);
                });
            }
        }
    }
}

