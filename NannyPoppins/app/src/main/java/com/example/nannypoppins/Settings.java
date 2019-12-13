package com.example.nannypoppins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        // new timer settings
        final EditText timerTxt = (EditText) findViewById(R.id.new_timer);

        Button btn_timer = (Button) findViewById(R.id.btn_setTimer);
        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_timer = 0;
                if(!timerTxt.getText().toString().equals(""))
                    new_timer = Integer.parseInt(timerTxt.getText().toString());
                System.out.println(new_timer);
                if(new_timer != 0) {
                    Home.restartTimer = true;
                    Home.counter = new_timer;
                }
            }
        });
    }
}
