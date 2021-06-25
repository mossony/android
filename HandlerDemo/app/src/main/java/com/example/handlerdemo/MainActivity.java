package com.example.handlerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt;

    final static String TAG = "HANDLER_TEST";

    Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    Log.i(TAG, "handleMessage: Thread id: " + Thread.currentThread().getId());
                    txt.setText("This is a Thread");
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: Main Thread id: " + Thread.currentThread().getId());
        txt = findViewById(R.id.txt1);
        txt.setText("This is a text view");

        new ActivityThread().start();

    }

    class ActivityThread extends Thread{
        public void run(){
            try {
                Thread.sleep(3000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            Log.i(TAG, "run: work Thread id: " + Thread.currentThread().getId());

            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);

        }

    }

}