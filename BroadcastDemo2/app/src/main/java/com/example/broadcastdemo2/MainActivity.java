package com.example.broadcastdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendBroadcast = findViewById(R.id.sendBroadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("sendMsg");
                intent.putExtra("msg", "This is a broadcast");
                sendBroadcast(intent);
            }
        });

        final MyReceiver[] receiver = {null};

        Button registerRec = findViewById(R.id.registerReceiver);
        registerRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiver[0] = new MyReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction("sendMsg");
                registerReceiver(receiver[0], filter);
            }
        });

        Button unRegisterRec = findViewById(R.id.cancelRegister);
        unRegisterRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(receiver[0]);
            }
        });

    }
}