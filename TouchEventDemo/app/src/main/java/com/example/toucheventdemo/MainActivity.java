package com.example.toucheventdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {

    TextView1 testView1;
    TextView2 testView2;
    TextView2 testView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testView1 = findViewById(R.id.textview1);
        testView1.setOnClickListener(this);
        testView1.setOnLongClickListener(this);
        testView1.setOnTouchListener(this);

        testView2 = findViewById(R.id.textview2);
        testView3 = findViewById(R.id.textview3);

        testView2.setColor(Color.RED);
        testView3.setColor(Color.YELLOW);

        testView1.setOnTouchListener(this);
        testView2.setOnTouchListener(this);
        testView3.setOnTouchListener(this);

        

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "onClick..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "onLongClick..", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(this, "onTouch.."+event.getAction(), Toast.LENGTH_SHORT).show();
        return false;
    }
}