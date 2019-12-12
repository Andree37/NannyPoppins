package com.example.nannypoppins;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    public static List<ApplicationInfo> mApps;
    GridView mGrid;
    CustomViewGroup view;
    WindowManager manager;
    WindowManager.LayoutParams localLayoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //initial setup
        requestPermissions();
        mApps = new ArrayList<>();
        blockNotificationBar();
        loadApps();

        mGrid = (GridView) findViewById(R.id.displayApps);
        mGrid.setAdapter(new AppsAdapter(this, mApps));

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                ApplicationInfo app = mApps.get(position);

                Intent launchApp = getPackageManager().getLaunchIntentForPackage(app.packageName);
                onPause();
                startActivity(launchApp);
            }
        });

        // folder button
        Button folder = findViewById(R.id.btn_kids);

        folder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,
                        Folder.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
        // camera button
        Button camara = findViewById(R.id.btn_camera);

        camara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */
                Intent intent = new Intent(Home.this,
                        Camara.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
        final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setMessage("U sure, man?");
        builder.setCancelable(true);
        builder.setNegativeButton("Nah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Close!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        });

        Button exit = findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,
                        PasswordOut.class);
                startActivity(intent);
            }
        });

        // settings button
        Button settings = findViewById(R.id.btn_settings);

        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,
                        PasswordDefinitions.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
        // colorgame button
        Button colorgame = findViewById(R.id.btn_colors);
        colorgame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,
                        DrawingGame.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                1
        );
    }

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ApplicationInfo> apps = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        PackageManager pm = getPackageManager();
        for (ApplicationInfo app : apps) {
            if (pm.getLaunchIntentForPackage(app.packageName) != null) {
                if (!app.packageName.equalsIgnoreCase("com.example.nannypoppins")) {
                    if (mApps.size() < 4)
                        mApps.add(app);
                }
            }

        }
    }

    private void blockNotificationBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        manager = ((WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE));
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        localLayoutParams.gravity = Gravity.TOP;
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        localLayoutParams.height = (int) (50 * getResources()
                .getDisplayMetrics().scaledDensity);
        localLayoutParams.format = PixelFormat.TRANSPARENT;
        view = new CustomViewGroup(this);

    }

    public class AppsAdapter extends BaseAdapter {
        private final Context mContext;
        List<ApplicationInfo> appsList;

        public AppsAdapter(Context context, List<ApplicationInfo> appsList) {
            this.mContext = context;
            this.appsList = appsList;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final ApplicationInfo info = mApps.get(position);
            if (convertView == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.linear_layout_apps, null);
            }
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview_app_art);
            final TextView nameTextView = (TextView) convertView.findViewById(R.id.textview_app_name);
            imageView.setImageDrawable(info.loadIcon(getPackageManager()));
            nameTextView.setText(info.loadLabel(getPackageManager()));
            return convertView;
        }

        public final int getCount() {
            return mApps.size();
        }

        public final Object getItem(int position) {
            return mApps.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }

}
