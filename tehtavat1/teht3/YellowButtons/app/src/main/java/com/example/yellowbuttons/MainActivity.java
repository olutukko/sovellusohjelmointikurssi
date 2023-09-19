package com.example.yellowbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setBackgroundColor(Color.YELLOW);
            }
        });
    }
    public void changeButtonColor(View view) {
        // Find the id of the button that was clicked.
        int id = view.getId();

        // Use the id to find the Button object.
        Button button = findViewById(id);

        // Change the Button object's background color to yellow.
        button.setBackgroundColor(Color.YELLOW);
    }

    public void buttonClicked(View view) {
        TextView button = findViewById(R.id.button);

    }

}