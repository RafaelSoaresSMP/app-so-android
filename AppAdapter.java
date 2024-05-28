package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AppAdapter extends BaseAdapter {
    private Context context;
    private List<AppInfo> appList;

    public AppAdapter(Context context, List<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        ImageView appIcon = convertView.findViewById(R.id.app_icon);
        TextView appName = convertView.findViewById(R.id.app_name);
        TextView version = convertView.findViewById(R.id.version);

        AppInfo appInfo = appList.get(position);

        appIcon.setImageDrawable(appInfo.icon);
        appName.setText(appInfo.name);
        version.setText(appInfo.version);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicou em " + appList.get(position).name, Toast.LENGTH_SHORT).show();
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(appInfo.packageName);
                if (intent != null) {
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Não foi possível abrir o aplicativo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

}
