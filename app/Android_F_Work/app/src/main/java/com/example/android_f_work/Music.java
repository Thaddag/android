package com.example.android_f_work;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.List;

public class Music extends AppCompatActivity {
    private ImageButton start,pause;
    private SeekBar seekBar;
    private final String TAG = "mmu";
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        pause = findViewById(R.id.imageButton3);
        start = findViewById(R.id.imageButton4);

        //seekBar = findViewById(R.id.seekBar);
        pause.setOnClickListener(onClickListener);
        start.setOnClickListener(onClickListener);

        //seekBar.setMax(100);
       // seekBar.setProgress(0);
        /*seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(musicService.mediaPlayer!=null){
                    musicService.seek(seekBar.getProgress());
                }
            }
        });*/
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageButton button = (ImageButton) view;
            switch (button.getId()){
                case R.id.imageButton4:{
                    if(musicService.mediaPlayer!=null){
                        musicService.pause();
                    }
                    break;
                }//暂停
                case R.id.imageButton3:{
                    Log.e(TAG, "onClick: 点击‘继续’" );
                    if(musicService.mediaPlayer!=null){
                        musicService.mediaStart();
                    }
                    else {

                        Intent intent = new Intent(Music.this, musicService.class);
                        startService(intent);
                        Log.e(TAG, "onClick: 启动mu service" );
                        /*seekBar.setMax(musicService.get_time());
                        Log.e(TAG, "onClick: seekbar设置最大值" );
                        seekBar.setProgress(0);
                        Log.e(TAG, "onClick:seekbar设置" );*/
                        //Music.MyAsyncTask asyncTask = new Music.MyAsyncTask();
                        //asyncTask.execute();//启动异步任务的处理
                        Log.e(TAG, "onClick: 启动线程" );
                    }
                }//继续
                default:break;
            }
        }
    };

    /*public class MyAsyncTask extends AsyncTask<Void,Integer,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.e(TAG, Thread.currentThread().getName() + " onPreExecute ");
        }
        @Override
        protected void onProgressUpdate(Integer... values) { //响应函数
            super.onProgressUpdate(values);

            Log.e(TAG, Thread.currentThread().getName() + " onProgressUpdate ");//通过publishProgress方法传过来的值进行进度条的更新.
            seekBar.setProgress(values[0]*100/seekBar.getMax());
        }
        @Override
        protected String doInBackground(Void... params) {
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground Begin");
            for (int i = 0;i < 100; i ++){ //使用for循环来模拟进度条的进度.
                int now_time = musicService.get_now_time();
                Log.e(TAG, "doInBackground: 获取now——time" );
                publishProgress(now_time);//调用publishProgress方法将自动触发onProgressUpdate方法来进行进度条的更新.
                try {
                    Thread.sleep(1);//通过线程休眠模拟耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground End");
            return null;
        }
        @Override
        protected void onPostExecute(String string ){
            super.onPostExecute(string);
            Log.e(TAG, Thread.currentThread().getName() + " onPostExecute ");
        }

    }*/


}
