package com.example.nannypoppins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup group;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        //baby setup
        group = findViewById(R.id.radiogroup);

        Button btnapply = findViewById(R.id.apply);
        btnapply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int radioId = group.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                String gender = (String) radioButton.getText();
                System.out.println(gender);
            }
        });

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */Intent intent = new Intent(MainActivity.this,
                        Geofencing.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }

    public void checkButton(View v) {
        int radioId = group.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
    }
}
