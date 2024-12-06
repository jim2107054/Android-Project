package com.example.findhome;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//public class House2Activity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_house2);
//    }
//}

public class House2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house1);

        LinearLayout mainLayout = findViewById(R.id.mainLayout);

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


