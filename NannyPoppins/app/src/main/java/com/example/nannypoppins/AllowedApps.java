package com.example.nannypoppins;



import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllowedApps extends ListActivity {
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private AdaptadorApps listadaptor = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allowed_apps);

        packageManager = getPackageManager();

        new LoadApplications().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = applist.get(position);
        try {
            Intent intent = packageManager
                    .getLaunchIntentForPackage(app.packageName);

            if (null != intent) {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(AllowedApps.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(AllowedApps.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private List<ResolveInfo> checkForLaunchIntent(List<ResolveInfo> list) {
        ArrayList<ResolveInfo> applist = new ArrayList<ResolveInfo>();
        for (ResolveInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.resolvePackageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = new ArrayList<>();
            List<ApplicationInfo> apps = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
            PackageManager pm = getPackageManager();
            for (ApplicationInfo app : apps) {
                if(pm.getLaunchIntentForPackage(app.packageName) != null) {
                    if (!app.packageName.equalsIgnoreCase("com.projeto.kidsecurenest")) {
                        applist.add(app);
                    }
                }

            }
            listadaptor = new AdaptadorApps(AllowedApps.this,
                    R.layout.row_apps, applist);
            Home.mApps = AdaptadorApps.appsLis;

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(AllowedApps.this, null,
                    "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Home.mApps = AdaptadorApps.appsLis;
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}