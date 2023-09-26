package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private float conversionRate = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        conversionRate = intent.getFloatExtra("CONVERSION_RATE", 1.0f);
    }


    public void openSettings(View view) {
        // muunna EUR -> hello
        //TextView originCurrencyTextView = findViewById(R.id.originCurrencyTextView);
        //originCurrencyTextView.setText("Hello!");
        // TODO avaa asetuksia
        Intent intent = new Intent(this, ConverterSettingsActivity.class);
        startActivity(intent);
    }

    public void convertCurrency(View view) {
        // Luetaan source -kentästä luku, muunnetaan se, ja kirjoitetaan destinationiin
        EditText destinatioCurrencyEditText = findViewById(R.id.DestinationCurrencyEditText);
        EditText originCurrencyEditText = findViewById(R.id.originCurrencyEditText);

        String originCurrencyInput = originCurrencyEditText.getText().toString();
        float originCurrencyAmount = Float.parseFloat(originCurrencyInput);

        float destinationCurrency = originCurrencyAmount * conversionRate;

        destinatioCurrencyEditText.setText(String.valueOf(destinationCurrency));
    }
}