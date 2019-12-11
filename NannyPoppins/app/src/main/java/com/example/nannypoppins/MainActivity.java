package com.example.nannypoppins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        // loading screen
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        //folder set up for first time use
        File filePhotos = new File(Environment.getExternalStorageDirectory() + "NannyPhotos");
        File fileVideos = new File(Environment.getExternalStorageDirectory() +"NannyVideos");
        File fileCreations = new File(Environment.getExternalStorageDirectory() + "NannyCreations");

        // folders need to be fiddled with
        if(!filePhotos.exists()) {
            System.out.println(filePhotos.mkdirs());
        }
        if(!fileVideos.exists()) {
            fileVideos.mkdirs();
        }
        if(!fileCreations.exists()) {
            fileCreations.mkdirs();
        }
        //baby setup
        group = findViewById(R.id.radiogroup);

        Button btnapply = findViewById(R.id.apply);
        btnapply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //get gender
                int radioId = group.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String gender = (String) radioButton.getText();

                //get birthdate
                EditText ageTxt = (EditText) findViewById(R.id.babyAge);
                int age = 0;
                if (!ageTxt.getText().toString().equals("")) {
                    age = Integer.parseInt(ageTxt.getText().toString());
                }

                //get name
                EditText nameTxt = (EditText) findViewById(R.id.babyName);
                String name = nameTxt.getText().toString();

                //get database
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                DocumentReference childRef = db.collection("Child").document();

                Baby baby = new Baby();
                baby.setBirthDate(age);
                baby.setName(name);
                baby.setGender(gender);

                childRef.set(baby).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent); // startActivity allow you to move
                        }
                    }
                });
            }
        });
    }

    public void checkButton(View v) {
        int radioId = group.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
    }
}
