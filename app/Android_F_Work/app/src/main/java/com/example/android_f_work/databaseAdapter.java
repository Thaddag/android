package com.example.android_f_work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class databaseAdapter extends ArrayAdapter<phoneinfo> {
    private int resourceId;
    public databaseAdapter(Context context, int textViewResourceId, List<phoneinfo> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        phoneinfo phoneinfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);//加载我们传入的布局，
        TextView phone_name_text = (TextView)view.findViewById(R.id.data_base_item_name);
        TextView phone_id_text = (TextView)view.findViewById(R.id.data_base_item_info);
        phone_name_text.setText(phoneinfo.getName());
        phone_id_text.setText(phoneinfo.getNumber());
        return view;
    }
}
