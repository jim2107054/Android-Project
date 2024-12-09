package com.example.findhome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class GetPostCode extends AppCompatActivity {

    EditText etDivision, etDistrict, etThana, etSuboffice;
    TextView tvPostcode;
    Button button;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_post_code);

        etDivision = findViewById(R.id.getDivision);
        etDistrict = findViewById(R.id.etDistrict);
        etThana = findViewById(R.id.etThana);
        etSuboffice = findViewById(R.id.etSuboffice);
        tvPostcode = findViewById(R.id.tvPostcode);
        button = findViewById(R.id.btnGetCode);

        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {
        String url = "https://raw.githubusercontent.com/saaiful/postcode-bd/refs/heads/master/postcode-pretty.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String inputDivision = etDivision.getText().toString().trim();
                            String inputDistrict = etDistrict.getText().toString().trim();
                            String inputThana = etThana.getText().toString().trim();
                            String inputSuboffice = etSuboffice.getText().toString().trim();

                            String foundPostcode = "Not found";

                            // Iterate through all keys in the JSON object
                            for (Iterator<String> it = response.keys(); it.hasNext(); ) {
                                String key = it.next();
                                JSONObject entry = response.getJSONObject(key).getJSONObject("en");

                                String division = entry.getString("division").trim();
                                String district = entry.getString("district").trim();
                                String thana = entry.getString("thana").trim();
                                String suboffice = entry.getString("suboffice").trim();

                                // Check if the input matches this entry
                                if (division.equalsIgnoreCase(inputDivision) &&
                                        district.equalsIgnoreCase(inputDistrict) &&
                                        thana.equalsIgnoreCase(inputThana) &&
                                        suboffice.equalsIgnoreCase(inputSuboffice)) {
                                    foundPostcode = key.trim();
                                    break;
                                }
                            }

                            // Display the result
                            tvPostcode.setText("Post Code is: " + foundPostcode);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            tvPostcode.setText("Error parsing data");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        tvPostcode.setText("Error fetching data");
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
