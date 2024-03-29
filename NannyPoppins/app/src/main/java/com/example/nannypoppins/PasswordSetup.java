package com.example.nannypoppins;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class PasswordSetup extends AppCompatActivity {
        public static String storedPIN;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        // loading screen
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_password_setup);
                Button btnapply = findViewById(R.id.apply);
                btnapply.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                //get PIN
                                EditText pinTxt = (EditText) findViewById(R.id.pin);
                                String pin = pinTxt.getText().toString();

                                if (pinTxt.getText().toString().length() < 3 ) {
                                        TextView AlertTxt = (TextView) findViewById(R.id.AlertTxt);
                                        AlertTxt.setEnabled(true);
                                        AlertTxt.setTextColor(Color.RED);
                                }else {
                                       storedPIN = pin;
                                                                Intent intent = new Intent(PasswordSetup.this,
                                                                        Home.class);
                                                                startActivity(intent);



                                }
                        }
                });
        }
}