package com.example.nannypoppins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Folder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        // home button
        Button home = findViewById(R.id.btn_home);

        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */
                Intent intent = new Intent(Folder.this,
                        Home.class);
                startActivity(intent); // startActivity allow you to move
            }
        });

        // photos folder
        Button photos = findViewById(R.id.btn_images);

        photos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<String> fileList = new ArrayList<>();
                File file = new File(Environment.getExternalStorageDirectory()+File.separator+"NannyPhotos");
                ListDir(file, fileList);
            }
        });
    }

    void ListDir(File f, List<String> fileList) {
        File[] files = f.listFiles();
        fileList.clear();
        for(File file : files) {
            fileList.add(file.getPath());
        }
        ArrayAdapter<String> directoryList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileList);
    }
}
