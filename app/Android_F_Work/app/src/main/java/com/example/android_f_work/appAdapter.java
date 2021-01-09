package com.example.android_f_work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class appAdapter extends ArrayAdapter<app> {//继承ArrayAdapter适配器，泛型指定为Fruit类
    private int resourceId;//样式
    public appAdapter(Context context, int textViewResourceId, List<app> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){//每当子项被滚动到屏幕上时会被调用
        app app = getItem(position);//得到当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);//加载我们传入的布局，
//false表示只让父布局的layout有效，但不为这个View添加父布局
        ImageView list_image=(ImageView) view.findViewById(R.id.main_list_image);//通过view获得fruitimage实例
        TextView appName=(TextView) view.findViewById(R.id.main_list_name);//通过view获得fruitname实例
        list_image.setImageResource(app.getImageId());//设置显示图片
        appName.setText(app.getName());//设置显示文字
        return view;
    }
}