package com.example.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    ProgressBar progressBar;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        progressBar = findViewById(R.id.progress_horizontal);
        txt = findViewById(R.id.txt);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TimeTickLoad timeTickLoad = new TimeTickLoad();
        timeTickLoad.execute(1000);

    }

    @SuppressLint("StaticFieldLeak")
    class TimeTickLoad extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            Log.i("hello", "onPreExecute");
            txt.setText("onPreExecute");
            progressBar.setVisibility(ProgressBar.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            txt.setText(s);
            Log.i("hello", "onPostExecute");
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i("hello", "onProgressUpdate");
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        // No UI task here
        @Override
        protected String doInBackground(Integer... integers) {
            Log.i("hello", "doInBackGround");
            for (int i = 0;i<=10;i++){
                publishProgress(i*10);
                try {
                    Thread.sleep(integers[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Finished";
        }
    }

}