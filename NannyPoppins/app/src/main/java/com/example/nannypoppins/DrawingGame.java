package com.example.nannypoppins;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class DrawingGame  extends AppCompatActivity implements View.OnClickListener {

    private ImageButton currpaint, drawBtn, baru, save;
    private DrawingView drawView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        drawView = (DrawingView) findViewById(R.id.drawing);
        drawBtn = (ImageButton) findViewById(R.id.draw_btn);
        baru = (ImageButton) findViewById(R.id.new_btn);
        save = (ImageButton) findViewById(R.id.save_btn);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);

        currpaint = (ImageButton) paintLayout.getChildAt(0);

        System.out.println(currpaint);
        currpaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        drawBtn.setOnClickListener(this);
        baru.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    public void paintClicked(View v){
        if(v != currpaint) {
            ImageButton imgView = (ImageButton) v;
            String color = v.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currpaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currpaint = (ImageButton)v;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.draw_btn) {
            drawView.setupDrawing();

        }
        if(v.getId() == R.id.new_btn) {
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New Drawing");
            newDialog.setMessage("Start new drawing");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            newDialog.show();
        }
        if(v.getId() == R.id.save_btn) {
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save Drawing");
            saveDialog.setMessage("Save drawing to device?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    drawView.overlayCanvas();
                    drawView.setDrawingCacheEnabled(true);
                    String imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(), drawView.getDrawingCache(), UUID.randomUUID().toString()+".png", "drawing");
                    if(imgSaved != null) {
                        Toast savedToast = Toast.makeText(getApplicationContext(), "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                        savedToast.show();;
                    } else {
                        Toast unsaved = Toast.makeText(getApplicationContext(), "Image could not be saved", Toast.LENGTH_SHORT);
                        unsaved.show();
                    }
                    drawView.destroyDrawingCache();
                }
            });
            saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }
    }
}