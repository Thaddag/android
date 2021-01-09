package com.example.android_f_work;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class calculator extends AppCompatActivity {
    private TextView textView;
    private Button button_0,button_1,button_2,button_3,button_4,button_5,
                    button_6,button_7,button_8,button_9,button_dian,button_c,
                    button_jia,button_jian,button_cheng,button_chu,button_deng,button_baifen,button_delete;
    private Queue<Float> numbers;
    private Queue<Character> operator;
    private String string;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            switch (button.getId())
            {
                case R.id.calculator_button_0:
                {
                    if(string.charAt(string.length()-1)=='/'){
                        Toast.makeText(getApplicationContext(),"can't divide 0",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    string=textView.getText().toString()+0;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_1:
                {
                    string=textView.getText().toString()+1;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_2:
                {
                    string=textView.getText().toString()+2;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_3:
                {
                    string=textView.getText().toString()+3;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_4:
                {
                    string=textView.getText().toString()+4;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_5:
                {
                    string=textView.getText().toString()+5;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_6:
                {
                    string=textView.getText().toString()+6;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_7:
                {
                    string=textView.getText().toString()+7;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_8:
                {
                    string=textView.getText().toString()+8;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_9:
                {
                    string=textView.getText().toString()+9;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_dian:
                {
                    Log.d("calculator", "onClick:dian");
                    if(string.length()==0)break;
                    char lastchar = string.charAt(string.length()-1);
                    if (lastchar == '+'||lastchar == '-'||lastchar == '*'||lastchar == '/')break;
                    String[] stringArray= string.split("-|\\+|\\*|/");
                    String last = stringArray[stringArray.length-1];
                    if (last.contains("."))break;
                    else {
                        Log.d("calculator", "onClick:dian should add dian");
                        string  = string  + ".";
                    }
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_delete:
                {
                    if (string.length()>0){
                        string = string.substring(0,string.length()-1);
                    }
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_C:
                {
                    string = null;
                    textView.setText(string);
                    break;
                }
                case R.id.calculator_button_jia:
                {
                    if(string.length()==0)break;
                    char lastchar = string.charAt(string.length()-1);
                    if (lastchar == '+'||lastchar == '-'||lastchar == '*'||lastchar == '/'||lastchar == '.'){
                        string  = string.substring(0,string.length()-1);
                        string = string + "+";
                        textView.setText(string);
                    }
                    else{
                        string  = string + "+";
                        textView.setText(string);
                    }
                    break;
                }
                case R.id.calculator_button_jian:
                {
                    if(string.length()==0)break;
                    char lastchar = string.charAt(string.length()-1);
                    if (lastchar == '+'||lastchar == '-'||lastchar == '*'||lastchar == '/'||lastchar == '.'){
                        string  = string.substring(0,string.length()-1);
                        string = string + "-";
                        textView.setText(string);
                    }
                    else{
                        string  = string + "-";
                        textView.setText(string);
                    }
                    break;
                }
                case R.id.calculator_button_cheng:
                {
                    if(string.length()==0)break;
                    char lastchar = string.charAt(string.length()-1);
                    if (lastchar == '+'||lastchar == '-'||lastchar == '*'||lastchar == '/'||lastchar == '.'){
                        string  = string.substring(0,string.length()-1);
                        string = string + "*";
                        textView.setText(string);
                    }
                    else{
                        string  = string + "*";
                        textView.setText(string);
                    }
                    break;
                }
                case R.id.calculator_button_chu:
                {
                    if(string.length()==0)break;
                    char lastchar = string.charAt(string.length()-1);
                    if (lastchar == '+'||lastchar == '-'||lastchar == '*'||lastchar == '/'||lastchar == '.'){
                        string  = string.substring(0,string.length()-1);
                        string = string + "/";
                        textView.setText(string);
                    }
                    else{
                        string  = string + "/";
                        textView.setText(string);
                    }
                    break;
                }
                case R.id.calculator_button_deng:
                {
                    Float solution = suan();
                    string = Float.toString(solution);
                    textView.setText(string);
                    break;
                }

                case R.id.calculator_button_baifen:
                {
                    Float solution = suan();
                    string = Float.toString(solution/100);
                    textView.setText(string);
                    break;
                }

                default:break;

            }

        }
    };
    private Float suan(){
        for(String ele:string.split("-|\\+|\\*|/"))
        {
            Float number = Float.parseFloat(ele);
            numbers.offer(number);
        }
        string = string.replace("0","");
        string = string.replace("1","");
        string = string.replace("2","");
        string =  string.replace("3","");
        string = string.replace("4","");
        string = string.replace("5","");
        string = string.replace("6","");
        string = string.replace("7","");
        string = string.replace("8","");
        string = string.replace("9","");
        string = string.replace(".","");
        for(int i = 0;i<string.length();i++)
        {
            Character op = string.charAt(i);
            operator.offer(op);

        }
        Float a = 0f;
        Float b = 0f;
        Character o;
        if(!numbers.isEmpty()) {
            a = numbers.poll();
            while (!(numbers.isEmpty()||operator.isEmpty())) {
                b = numbers.poll();
                o = operator.poll();
                if (o == '+') a = a + b;
                else if (o == '-') a = a - b;
                else if (o == '*') a = a * b;
                else if (o == '/') a = a / b;
            }
        }
        numbers.clear();
        operator.clear();
        return a;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        ActionBar actionBar=getSupportActionBar();//去除系统自带标题栏
        if(actionBar!=null){
            actionBar.hide();
        }
        numbers = new LinkedList<Float>();
        operator = new LinkedList<Character>();
        textView = findViewById(R.id.textView);
        textView.setText("");
        string = textView.getText().toString();
        button_0 = findViewById(R.id.calculator_button_0);
        button_1 = findViewById(R.id.calculator_button_1);
        button_2 = findViewById(R.id.calculator_button_2);
        button_3 = findViewById(R.id.calculator_button_3);
        button_4 = findViewById(R.id.calculator_button_4);
        button_5 = findViewById(R.id.calculator_button_5);
        button_6 = findViewById(R.id.calculator_button_6);
        button_7 = findViewById(R.id.calculator_button_7);
        button_8 = findViewById(R.id.calculator_button_8);
        button_9 = findViewById(R.id.calculator_button_9);
        button_dian = findViewById(R.id.calculator_button_dian);
        button_jia = findViewById(R.id.calculator_button_jia);
        button_jian = findViewById(R.id.calculator_button_jian);
        button_cheng = findViewById(R.id.calculator_button_cheng);
        button_chu = findViewById(R.id.calculator_button_chu);
        button_deng = findViewById(R.id.calculator_button_deng);
        button_baifen = findViewById(R.id.calculator_button_baifen);
        button_delete = findViewById(R.id.calculator_button_delete);
        button_c =  findViewById(R.id.calculator_button_C);
        button_0.setOnClickListener(onClickListener);
        button_1.setOnClickListener(onClickListener);
        button_2.setOnClickListener(onClickListener);
        button_3.setOnClickListener(onClickListener);
        button_4.setOnClickListener(onClickListener);
        button_5.setOnClickListener(onClickListener);
        button_6.setOnClickListener(onClickListener);
        button_7.setOnClickListener(onClickListener);
        button_8.setOnClickListener(onClickListener);
        button_9.setOnClickListener(onClickListener);
        button_dian.setOnClickListener(onClickListener);
        button_jia.setOnClickListener(onClickListener);
        button_jian.setOnClickListener(onClickListener);
        button_cheng.setOnClickListener(onClickListener);
        button_chu.setOnClickListener(onClickListener);
        button_baifen.setOnClickListener(onClickListener);
        button_deng.setOnClickListener(onClickListener);
        button_delete.setOnClickListener(onClickListener);
        button_c.setOnClickListener(onClickListener);




    }
}
