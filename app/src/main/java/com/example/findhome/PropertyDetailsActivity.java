package com.example.findhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PropertyDetailsActivity {

    private Context context;
    private View view;

    public PropertyDetailsActivity(Context context) {
        this.context = context;

        // Inflate the layout for the property details
        LayoutInflater inflater = LayoutInflater.from(context);
        this.view = inflater.inflate(R.layout.activity_property_details, null);
    }

    public View getView() {
        return view;
    }

    public void setupDetails(int[] images) {
//        // Set the details
//        TextView titleTextView = view.findViewById(R.id.title_detail);
//        TextView rentTextView = view.findViewById(R.id.etRentIT1);
//        TextView sizeTextView = view.findViewById(R.id.etsizeIT1);
//        TextView roommatesTextView = view.findViewById(R.id.etRoommeIT1);

        // Add images to the HorizontalScrollView container
        LinearLayout imageContainer = view.findViewById(R.id.imageContainer);
        for (int image : images) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins(8, 0, 8, 0); // Add spacing between images
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(image);
            imageContainer.addView(imageView);
        }
    }
}