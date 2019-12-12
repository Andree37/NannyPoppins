package com.example.nannypoppins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // geofencing button
        Button button = findViewById(R.id.btn_position);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,
                        Geofencing.class);
                startActivity(intent); // startActivity allow you to move
            }
        });

        // add apps button
        Button apps = findViewById(R.id.btn_apps);

        apps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,
                        AllowedApps.class);
                startActivity(intent); // startActivity allow you to move
            }
        });

        Button exit = findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,
                        Home.class);
                startActivity(intent);
            }
        });
    }
}
