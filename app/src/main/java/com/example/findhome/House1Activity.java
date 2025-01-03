package com.example.findhome;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class House1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        ConstraintLayout mainLayout = findViewById(R.id.mainLayoutB1);

        PropertyDetailsActivity propertyDetails = new PropertyDetailsActivity(this);
        mainLayout.addView(propertyDetails.getView());

        // Set up the details and images
        int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
        propertyDetails.setupDetails(
//                "Home in Hamburg",
//                "2000 Taka",
//                "94m²",
//                "2",
                images
        );

    }
}


