package com.example.handlerpostdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Handler countHandler = new Handler();
    int count = 0;
    TextView textView;
    ProgressBar progressBar;

    Runnable mRunToast = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }
    };

    Runnable runcount = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            textView.setText(""+String.valueOf(count++));
            countHandler.postDelayed(runcount, 1000);
        }
    };

    Runnable updateProgress = new Runnable() {
        int i = 0;
        @Override
        public void run() {
            i = i+10;
            Message msg = updateProgressHandler.obtainMessage();
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgressHandler.sendMessage(msg);
        }
    };

    @SuppressLint("HandlerLeak")
    Handler updateProgressHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            progressBar.setProgress(msg.arg1);
            updateProgressHandler.post(updateProgress);
            if (msg.arg1 >=100){
                removeCallbacks(updateProgress);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.btnShowToast)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnStart)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnStop)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnStartProgress)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnStopProgress)).setOnClickListener(this);
        textView = findViewById(R.id.countTxt);
        progressBar = findViewById(R.id.progress_horizontal);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowToast:
                countHandler.postAtTime(mRunToast, SystemClock.uptimeMillis()+5*1000);
                break;
            case R.id.btnStart:
                countHandler.postDelayed(runcount, 1000);
                break;
            case R.id.btnStop:
                countHandler.removeCallbacks(runcount);
                break;
            case R.id.btnStartProgress:
                updateProgressHandler.post(updateProgress);
                break;
            case R.id.btnStopProgress:
                updateProgressHandler.removeCallbacks(updateProgress);
                break;
        }
    }
}