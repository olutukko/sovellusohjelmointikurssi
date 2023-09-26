package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConverterSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_settings);
        Intent intent = getIntent();
        String message = intent.getStringExtra("HELLO_MESSAGE");
        TextView messageTextView = findViewById(R.id.settingsText);
        messageTextView.setText(message);
    }

    public void goBack(View view) {
        EditText conversionRateEditText = findViewById(R.id.editTextConversionRate);
        String conversionRateInput = conversionRateEditText.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        float conversionRate = Float.parseFloat(conversionRateInput);
        intent.putExtra("CONVERSION_RATE", conversionRate);
        startActivity(intent);
    }
}