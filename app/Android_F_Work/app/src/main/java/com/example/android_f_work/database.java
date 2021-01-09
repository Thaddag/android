package com.example.android_f_work;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class database extends AppCompatActivity {
    private ArrayList<phoneinfo> phonelist = new ArrayList<>();
    private Button add_info;
    private DatabaseHelper mydatabasehelper;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);
        ActionBar actionBar=getSupportActionBar();//去除系统自带标题栏
        if(actionBar!=null){
            actionBar.hide();
        }

        add_info = findViewById(R.id.button);
        add_info.setOnClickListener(onClickListener);

        mydatabasehelper = new DatabaseHelper(this,"PhoneNumber.db",null,1);
        mydatabasehelper.getWritableDatabase();
        get_database_data();

        databaseAdapter databaseAdapter =new databaseAdapter(database.this,R.layout.databae_item,phonelist);

        ListView listView =findViewById(R.id.data_base_list);
        listView.setAdapter(databaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                phoneinfo phoneinfo = phonelist.get(position);
                Intent intent = new Intent(database.this,phone.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",phoneinfo.getName());
                bundle.putString("number",phoneinfo.getNumber());
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        phonelist.clear();
        get_database_data();
        databaseAdapter databaseAdapter =new databaseAdapter(database.this,R.layout.databae_item,phonelist);
        ListView listView =findViewById(R.id.data_base_list);
        listView.setAdapter(databaseAdapter);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            if(button.getId()==R.id.button){

                Intent intent = new Intent(database.this,phone.class);
                Bundle bundle = new Bundle();
                bundle.putString("name","");
                bundle.putString("number","");
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        }
    };
    private void get_database_data(){
        SQLiteDatabase db = mydatabasehelper.getWritableDatabase();
        Cursor cursor = mydatabasehelper.getData(db);
        if (cursor.moveToFirst()) {
            do {
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                phoneinfo phoneinfo = new phoneinfo(name,number);
                phonelist.add(phoneinfo);
            } while (cursor.moveToNext());
        }
/*        phoneinfo info1 = new phoneinfo("小明","12346789");
        phonelist.add(info1);
        phoneinfo info2 = new phoneinfo("小亮","12346789");
        phonelist.add(info2);
        phoneinfo info3 = new phoneinfo("小亮","12346789");
        phonelist.add(info3);
        phoneinfo info4 = new phoneinfo("小亮","12346789");
        phonelist.add(info4);
        phoneinfo info5 = new phoneinfo("小亮","12346789");
        phonelist.add(info5);
        phoneinfo info6 = new phoneinfo("小亮","12346789");
        phonelist.add(info6);
        phoneinfo info7 = new phoneinfo("小亮","12346789");
        phonelist.add(info7);*/
    }
}
