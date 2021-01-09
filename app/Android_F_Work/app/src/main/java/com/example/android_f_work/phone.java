package com.example.android_f_work;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class phone extends AppCompatActivity {
    private TextView text_name;
    private TextView text_number;
    private Button phoneinfo_delete,phone_call,phone_send_message,chenge_info,add_phoneinfo;
    private String name,number;
    private DatabaseHelper mydatabasehelper;
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);
        ActionBar actionBar=getSupportActionBar();//去除系统自带标题栏
        if(actionBar!=null){
            actionBar.hide();
        }
        Log.d("database", "onCreate: phone page create");
        mydatabasehelper = new DatabaseHelper(this,"PhoneNumber.db",null,1);
        Log.d("database", "onCreate: get databasehelper");
        text_name = findViewById(R.id.textView4);
        text_number = findViewById(R.id.textView6);

        chenge_info = findViewById(R.id.button4);
        phoneinfo_delete = findViewById(R.id.button_phoneinfo_delete);
        phone_call = findViewById(R.id.button6);
        phone_send_message = findViewById(R.id.button7);
        add_phoneinfo = findViewById(R.id.button_phoneinfo_add);

        add_phoneinfo.setOnClickListener(onClickListener);
        chenge_info.setOnClickListener(onClickListener);
        phoneinfo_delete.setOnClickListener(onClickListener);
        phone_call.setOnClickListener(onClickListener);
        phone_send_message.setOnClickListener(onClickListener);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        name  = bundle.getString("name");
        number = bundle.getString("number");
        Log.d("database", "onCreate: 开启电话界面，姓名"+name+"电话"+number);
        if(number.length()==0){
            text_name.setHint("请输入姓名");
            text_number.setHint("请输入号码");
            text_name.setText("");
            text_number.setText("");
            LinearLayout layout = findViewById(R.id.mid_line);
            layout.setVisibility(View.INVISIBLE);
            chenge_info.setVisibility(View.INVISIBLE);
            phoneinfo_delete.setVisibility(View.INVISIBLE);
            phone_call.setVisibility(View.INVISIBLE);
            phone_send_message.setVisibility(View.INVISIBLE);
        }else {
            text_name.setText(name);
            text_number.setText(number);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            Button button = (Button)view;
            switch (button.getId()){
                case R.id.button4:{
                    if(text_number.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"数字不能为空",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Boolean flag;
                    SQLiteDatabase db = mydatabasehelper.getWritableDatabase();
                    flag = findSame(text_number.getText().toString());
                    if(flag==Boolean.FALSE){
                        mydatabasehelper.deleteData(db, number);
                        mydatabasehelper.insertData(db, text_name.getText().toString(), text_number.getText().toString());
                        name = text_name.getText().toString();
                        number = text_number.getText().toString();
                        Toast.makeText(getApplicationContext(), "更改成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"已有该电话号码",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }//更改信息

                case R.id.button_phoneinfo_delete:{
                    showNormalDialog();
                    break;//删除该人信息
                }//删除信息

                case R.id.button_phoneinfo_add:{
                    Log.d("database", "onClick: 点击添加");
                    if(text_number.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"数字不能为空",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Boolean flag;
                    SQLiteDatabase db = mydatabasehelper.getWritableDatabase();
                    flag = findSame(text_number.getText().toString());
                    if(flag == Boolean.FALSE){
                        mydatabasehelper.insertData(db,text_name.getText().toString(),text_number.getText().toString());
                        name = text_name.getText().toString();
                        number = text_number.getText().toString();

                        Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"添加失败，已存在相同号码",Toast.LENGTH_SHORT).show();
                        Log.d("database", "onClick: 添加失败，已存在相同号码");
                    }
                    break;
                }//添加信息

                case R.id.button6:{
                    if(number.length()!=0){
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + number);
                        intent.setData(data);
                        startActivity(intent);
                        break;
                    }else{
                        Toast.makeText(getApplicationContext(),"号码为空",Toast.LENGTH_SHORT).show();
                    }
                }//打电话

                case R.id.button7:{
                    if(number.length()!=0){
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        Uri uri = Uri.parse("smsto:"+number);
                        intent.setData(uri);
                        startActivity(intent);
                        break;
                    }else{
                        Toast.makeText(getApplicationContext(),"号码为空",Toast.LENGTH_SHORT).show();
                    }
                }//发短信
                default:break;
            }
        }
    };

    private boolean findSame(String number){
        Log.d("database", "findSame: 调用比较函数");
        Boolean flag = Boolean.FALSE;
        SQLiteDatabase db = mydatabasehelper.getWritableDatabase();
        Cursor cursor = mydatabasehelper.getData(db);
        if (cursor.moveToFirst()) {
            do {
                String getDataNumber = cursor.getString(cursor.getColumnIndex("number"));
                Log.d("database", "onCreate:数据库中其中一个手机号是"+getDataNumber);
                if(getDataNumber.equals(text_number.getText().toString())){
                    Log.d("database", "onCreate:找到相同手机号");
                    flag = Boolean.TRUE;
                }
            } while (cursor.moveToNext());
        }
        return flag;
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(phone.this);
        //normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("删除");
        normalDialog.setMessage("确定要删除吗？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = mydatabasehelper.getWritableDatabase();
                        mydatabasehelper.deleteData(db,number);
                        finish();
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
}
