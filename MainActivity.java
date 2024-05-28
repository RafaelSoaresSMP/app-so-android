package com.example.myapplication;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lista_aplicativos);

        PackageManager packageManager = getPackageManager();

        List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);


        List<AppInfo> userApps = new ArrayList<>();

        for (ApplicationInfo appInfo : apps) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = packageManager.getApplicationLabel(appInfo).toString();
                Drawable appIcon = packageManager.getApplicationIcon(appInfo);
                String packageName = appInfo.packageName;

                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                    String versionName = packageInfo.versionName;

                    userApps.add(new AppInfo(appName, appIcon, packageName, versionName));

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        AppAdapter adapter = new AppAdapter(this, userApps);
        listView.setAdapter(adapter);
    }

}
