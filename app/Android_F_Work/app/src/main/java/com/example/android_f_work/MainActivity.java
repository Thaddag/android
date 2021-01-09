package com.example.android_f_work;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;



public class MainActivity extends AppCompatActivity {
    private List<app> appList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();//去除系统自带标题栏
        if(actionBar!=null){
            actionBar.hide();
        }
        init_apps();
        appAdapter adapter= new appAdapter(MainActivity.this,R.layout.main_list,appList);
        ListView listView = findViewById(R.id.main_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                app app = appList.get(position);
                if(app.getName()=="计算器"){
                    Intent intent = new Intent(MainActivity.this,calculator.class);
                    startActivity(intent);
                }else if(app.getName()=="天气"){
                    Intent intent = new Intent(MainActivity.this,weather.class);
                    startActivity(intent);
                }else if(app.getName()=="电话"){
                    Intent intent = new Intent(MainActivity.this,database.class);
                    startActivity(intent);
                }else if(app.getName()=="音乐"){
                    Intent intent = new Intent(MainActivity.this,Music.class);
                    startActivity(intent);
                }else if(app.getName()=="线程"){
                    Intent intent = new Intent(MainActivity.this,thread.class);
                    startActivity(intent);
                }

                Toast.makeText(MainActivity.this, app.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init_apps(){

        app calculator = new app(R.drawable.calculator,"计算器");//新建fruit 实例
        appList.add(calculator);//将新建的fruit实例加入list列表中
        app weather = new app(R.drawable.cloud,"天气");
        appList.add(weather);/*
        app database = new app(R.drawable.databases,"数据库");
        appList.add(database);*/
        app phone = new app(R.drawable.phone,"电话");
        appList.add(phone);
        app music = new app(R.drawable.music,"音乐");
        appList.add(music);
        app thread = new app(R.drawable.thread,"线程");
        appList.add(thread);
    }

}
