package com.example.android_f_work;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mcontext;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mcontext = context;
    }
    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE_Phone ="CREATE TABLE PHONEINFO ( number integer primary key, name text)";
        db.execSQL(CREATE_TABLE_Phone);
        Toast.makeText(mcontext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
    public Cursor getData(SQLiteDatabase db){
        Log.d("database", "getData: 获取数据库信息");
        String selectQuery = "select number,name from PHONEINFO";
        Cursor cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }
    public void insertData(SQLiteDatabase db,String name,String number){
        Log.d("database", "insertData: 添加数据，号码为:"+number);
        String insertQuery = "insert into PHONEINFO (name,number) values (\""+ name +"\",\""+number+"\")";
        Log.d("database", "insertData: 添加语句为:"+insertQuery);
        db.execSQL(insertQuery);
        Log.d("database", "insertData: 添加语句执行成功");
    }
    public void deleteData(SQLiteDatabase db,String number){
        Log.d("database", "deleteData: 删除数据，号码为:"+number);
        String insertQuery = "delete from PHONEINFO where number = \""+number+"\"";
        db.execSQL(insertQuery);
    }
}
