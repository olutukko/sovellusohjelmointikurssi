package com.example.cloud_connection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView responseTextView;
    private Button fetchDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseTextView = findViewById(R.id.response_text_view);

        fetchDataButton = findViewById(R.id.data_button);

        fetchDataButton.setOnClickListener(view -> fetchDataFromRestAPI());

    }
    private void fetchDataFromRestAPI() {
        String url = "https://dummy.restapiexample.com/api/v1/employees"; // Your REST API endpoint

        // Make the Volley request
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response by displaying it in the TextView
                        responseTextView.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        responseTextView.setText("Error: " + error.getMessage());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}