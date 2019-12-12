package com.example.nannypoppins;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PasswordDefinitions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // loading screen
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_password_out);
        Button exit = findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordDefinitions.this,
                        Home.class);
                startActivity(intent);
            }
        });
        Button btnapply = findViewById(R.id.apply);
        btnapply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //get PIN
                EditText pinTxt = (EditText) findViewById(R.id.pin);
                String pin = pinTxt.getText().toString();


                if (pinTxt.getText().toString().length() < 3 || !pinTxt.getText().toString().equals(PasswordSetup.storedPIN) ) {
                    TextView AlertTxt = (TextView) findViewById(R.id.AlertTxt);
                    AlertTxt.setEnabled(true);
                    AlertTxt.setTextColor(Color.RED);
                }else {
                    Intent intent = new Intent(PasswordDefinitions.this,
                            Settings.class);
                    startActivity(intent);


                }
            }
        });
    }
}