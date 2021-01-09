package com.example.android_f_work;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class thread extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button button_add20,button_thread_start,button_thread_stop;
    private TextView textView;
    private final String TAG = "mth";
    private Boolean flag = Boolean.TRUE;
    private MyAsyncTask asyncTask = new MyAsyncTask();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.thread);
        progressBar = findViewById(R.id.progressBar);
        button_add20 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView7);

        button_add20.setOnClickListener(onClickListener);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        //textView.setVisibility(View.INVISIBLE);


        asyncTask.execute();//启动异步任务的处理
    }


    private View.OnClickListener onClickListener = view -> {
        Button button = (Button)view;
        if(button.getId()==R.id.button2){
            progressBar.setProgress((progressBar.getProgress()+20)%progressBar.getMax());
            textView.setText(progressBar.getProgress()%progressBar.getMax()+"/"+progressBar.getMax());
            //flag = Boolean.FALSE;
        }
    };

    public class MyAsyncTask extends AsyncTask<Void,Integer,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.e(TAG, Thread.currentThread().getName() + " onPreExecute ");
        }
        @Override
        protected void onProgressUpdate(Integer... values) { //响应函数
            super.onProgressUpdate(values);
            Log.e(TAG, Thread.currentThread().getName() + " onProgressUpdate ");//通过publishProgress方法传过来的值进行进度条的更新.
            textView.setText(values[0]+"/"+progressBar.getMax());
            progressBar.setProgress(values[0]);
        }
        @Override
        protected String doInBackground(Void... params) {
            Log.e(TAG, Thread.currentThread().getName() + " doInBackground Begin");
            int j;
            while (flag){
                j=(progressBar.getProgress()+1)%progressBar.getMax();
                publishProgress(j);//调用publishProgress方法将自动触发onProgressUpdate方法来进行进度条的更新.
                Log.e(TAG, "doInBackground: 进度为:"+j );
                try {

                    Thread.sleep(1000);//通过线程休眠模拟耗时操作
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

    }

}