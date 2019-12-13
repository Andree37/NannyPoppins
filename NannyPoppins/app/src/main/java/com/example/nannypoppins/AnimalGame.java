package com.example.nannypoppins;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.Random;


public class AnimalGame extends AppCompatActivity {


        private MediaPlayer current;
        private String animal;
        private ImageButton b1;
        private ImageButton b2;
        private ImageButton b3;
        private ImageButton b4;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        b1 = (ImageButton) findViewById(R.id.imageButton_caixa1);
        b2 = (ImageButton) findViewById(R.id.imageButton_caixa2);
        b3 = (ImageButton) findViewById(R.id.imageButton_caixa3);
        b4 = (ImageButton) findViewById(R.id.imageButton_caixa4);
            // home button
            ImageButton home = findViewById(R.id.imageButton_home);
            home.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AnimalGame.this,
                            Home.class);
                    startActivity(intent); // startActivity allow you to move
                }
            });
        change();
        buttons();
        verfi();
    }

        protected void change(){
        ImageButton next = (ImageButton) findViewById(R.id.imageButton_right);
        ImageButton prev = (ImageButton) findViewById(R.id.imageButton_left);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                verfi();
            }
        });
        prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                verfi();
            }
        });
    }

        protected void play(){
        ImageButton play = (ImageButton) findViewById(R.id.imageButton_speaker);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                current.start();
            }
        });
    }

        protected void pickSound(){
        String[] sounds = {"R.raw.cat", "R.raw.chick", "R.raw.cow", "R.raw.pig", "R.raw.tigers", "R.raw.whale"};
        Random rand = new Random();
        int i =rand.nextInt(sounds.length);

        if (i==0){
            current = MediaPlayer.create(this, R.raw.cat);
            animal = "cat";
            b1.setBackgroundResource(R.drawable.animais_button_animal_cat);
            b2.setBackgroundResource(R.drawable.animais_button_animal_bull);
            b3.setBackgroundResource(R.drawable.animais_button_animal_chick);
            b4.setBackgroundResource(R.drawable.animais_button_animal_whale);
        }
        else if (i==1){
            current =  MediaPlayer.create(this, R.raw.chick);
            animal = "chick";
            b1.setBackgroundResource(R.drawable.animais_button_animal_bull);
            b2.setBackgroundResource(R.drawable.animais_button_animal_chick);
            b3.setBackgroundResource(R.drawable.animais_button_animal_tiger);
            b4.setBackgroundResource(R.drawable.animais_button_animal_pig);
        }
        else if (i==2){
            current =  MediaPlayer.create(this, R.raw.cow);
            animal = "cow";
            b1.setBackgroundResource(R.drawable.animais_button_animal_pig);
            b2.setBackgroundResource(R.drawable.animais_button_animal_whale);
            b3.setBackgroundResource(R.drawable.animais_button_animal_bull);
            b4.setBackgroundResource(R.drawable.animais_button_animal_cat);
        }
        else if (i==3){
            current =  MediaPlayer.create(this, R.raw.pig);
            animal = "pig";
            b1.setBackgroundResource(R.drawable.animais_button_animal_whale);
            b2.setBackgroundResource(R.drawable.animais_button_animal_tiger);
            b3.setBackgroundResource(R.drawable.animais_button_animal_bull);
            b4.setBackgroundResource(R.drawable.animais_button_animal_pig);
        }
        else if (i==4){
            current =  MediaPlayer.create(this, R.raw.tigers);
            animal = "tiger";
            b1.setBackgroundResource(R.drawable.animais_button_animal_cat);
            b2.setBackgroundResource(R.drawable.animais_button_animal_bull);
            b3.setBackgroundResource(R.drawable.animais_button_animal_tiger);
            b4.setBackgroundResource(R.drawable.animais_button_animal_whale);
        }
        else {
            current = MediaPlayer.create(this, R.raw.whale);
            animal = "whale";
            b1.setBackgroundResource(R.drawable.animais_button_animal_pig);
            b2.setBackgroundResource(R.drawable.animais_button_animal_cat);
            b3.setBackgroundResource(R.drawable.animais_button_animal_bull);
            b4.setBackgroundResource(R.drawable.animais_button_animal_whale);
        }
    }

        private Boolean bo1 = false;
        private Boolean bo2 = false;
        private Boolean bo3 = false;
        private Boolean bo4 = false;

        protected void buttons(){
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bo1 = true;
                System.out.println("bo1");
                bora();
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bo2 = true;
                System.out.println("bo2");
                bora();
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bo3 = true;
                System.out.println("bo3");
                bora();
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bo4 = true;
                System.out.println("bo4");
                bora();
            }
        });
    }

        protected void verfi(){
        pickSound();
        play();
        System.out.println(animal);

    }


        protected void bora(){
        if(animal.equals("cat") && bo1){
            b1.setBackgroundResource(R.drawable.animais_button_animal_cat_green);
            bo1 = false;
        }
        else if(animal.equals("cow")&& bo3){
            b3.setBackgroundResource(R.drawable.animais_button_animal_bull_green);
            bo3 = false;
        }
        else if(animal.equals("chick") && bo2){
            b2.setBackgroundResource(R.drawable.animais_button_animal_chick_green);
            bo2 = false;
        }
        else if(animal.equals("pig") && bo4){
            b4.setBackgroundResource(R.drawable.animais_button_animal_pig_green);
            bo4 = false;
        }
        else if(animal.equals("tiger") && bo3){
            b3.setBackgroundResource(R.drawable.animais_button_animal_tiger_green);
            bo3 = false;
        }
        else if(animal.equals("whale") && bo4){
            b4.setBackgroundResource(R.drawable.animais_button_animal_whale_green);
            bo4 = false;
        }


    }

    }

