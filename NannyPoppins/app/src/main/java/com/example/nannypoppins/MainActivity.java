package com.example.nannypoppins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RadioGroup group;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        //get database
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Child");

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

                Baby baby = new Baby("Daniel", new Timestamp(System.currentTimeMillis()), false);
                dbRef.push().setValue("test");

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent); // startActivity allow you to move

            }
        });


        // geofencing button
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
