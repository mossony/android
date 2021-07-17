package com.example.touchdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    TestView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView = findViewById(R.id.testview);
        testView.setOnTouchListener(new mOnTouch());
    }

    private class mOnTouch implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x1, y1;
            x1 = (int) event.getX();
            y1 = (int) event.getY();

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    testView.setXY(x1, y1);
                    testView.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    testView.setXY(x1, y1);
                    testView.invalidate();
                    break;
                case MotionEvent.ACTION_CANCEL:
            }

            return true;
        }
    }
}