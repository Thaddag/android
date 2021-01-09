package com.example.android_f_work;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

public class weather extends AppCompatActivity implements View.OnClickListener{
    private TextView responseText;
    private ImageView weather_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        ActionBar actionBar=getSupportActionBar();//去除系统自带标题栏
        if(actionBar!=null){
            actionBar.hide();
        }
        responseText =  findViewById(R.id.response_text);
        weather_image = findViewById(R.id.weather_image);
        Button sendRequest =  findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.send_request) {
            sendRequest();
        }

    }

    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                Log.d("weather", "run: i will run");
                try {
                    Log.d("weather", "run: im running");
                    URL url = new URL("http://t.weather.itboy.net/api/weather/city/101090201");
                    Log.d("weather", "run: im got the url");

                    connection = (HttpURLConnection) url.openConnection();
                    Log.d("weather", "run: im opened connection");

                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(2000);
                    connection.setReadTimeout(2000);
                    Log.d("weather", "run: connection has been setting");

                    InputStream in = connection.getInputStream();
                    Log.d("weather", "run: im got inputStream");

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    parseRequest(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void parseRequest(String string) throws JSONException {
        Log.d("weather", "run: parseRequest");
        JSONObject object = new JSONObject(string);
        JSONObject cityInfo = object.getJSONObject("cityInfo");
        String city = cityInfo.getString("city");
        JSONObject data = object.getJSONObject("data");
        //JSONObject forecast = data.getJSONObject("forecast");
        JSONArray forecast = data.getJSONArray("forecast");
        JSONObject today_forecast = forecast.getJSONObject(0);
        String type = today_forecast.getString("type");
        switch (type){
            case "晴":{
                weather_image.setImageResource(R.drawable.sun);
                break;
            }
            case "多云":{
                weather_image.setImageResource(R.drawable.partly_cloudy_day);
                break;
            }
            case "小雨": case "中雨": case "大雨":{
                weather_image.setImageResource(R.drawable.rain);
                break;
            }
            case "阴天":{
                weather_image.setImageResource(R.drawable.clouds);
                break;
            }
            case "小雪": case "中雪":case "大雪":{
                weather_image.setImageResource(R.drawable.snow);
                break;
            }
            default:break;
        }
        String wendu = data.getString("wendu");
        String quality = data.getString("quality");
        String shidu = data.getString("shidu");


        String info = "城市:"+city+"\n天气:"+type+"\n温度:"+wendu+"\n湿度:"+shidu+"\n空气质量:"+quality;
        showResponse(info);
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
                Log.d("weather", "run: Textshow,the string is"+response);
            }
        });
    }
}

