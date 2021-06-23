package com.example.broadcastdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = findViewById(R.id.hello);

        Button button = findViewById(R.id.broadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.broadcastdemo1", "com.example.broadcastdemo1.MyReceiver"));
                intent.setAction("com.example.BROADCAST");
                intent.putExtra("msg", "This is a broadcast message");
                sendBroadcast(intent);
            }
        });
    }
}