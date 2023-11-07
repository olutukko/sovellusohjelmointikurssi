package com.example.sensor_information;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView accelerationTextView;

    private LocationManager locationManager;
    private TextView locationTextView;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Accelerometer setup
        accelerationTextView = findViewById(R.id.accelerationTextView);
        Button accelerometerButton = findViewById(R.id.accelerometerButton);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelerometerButton.setOnClickListener(view -> {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        });

        // Location setup
        locationTextView = findViewById(R.id.locationTextView);
        Button gpsButton = findViewById(R.id.gpsButton);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        gpsButton.setOnClickListener(view -> {
            checkLocationPermission();
        });
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                // Permission denied, handle this situation (e.g., show a message to the user)
            }
        }
    }

    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (locationManager != null) {
                try {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
                } catch (SecurityException e) {
                    e.printStackTrace();
                    // Handle the exception or show an error message to the user
                }
            }
        } else {
            // Permission is not granted, handle this situation (e.g., show a message to the user)
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            String accelerationInfo = "X: " + values[0] + ", Y: " + values[1] + ", Z: " + values[2];
            accelerationTextView.setText(accelerationInfo);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String locationInfo = "Latitude: " + latitude + ", Longitude: " + longitude;
            locationTextView.setText(locationInfo);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Not used in this example
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Not used in this example
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Not used in this example
        }
    };
}
