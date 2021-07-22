package com.example.toucheventdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    TestView testView;
    VelocityTracker velocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView = findViewById(R.id.testview);
        testView.setOnTouchListener(new mOnTouch());
    }

    private class mOnTouch implements View.OnTouchListener{

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x1, y1;
            x1 = (int) event.getX();
            y1 = (int) event.getY();

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    testView.setXY(x1, y1);
                    testView.invalidate();
                    if (velocityTracker == null) {
                        velocityTracker = VelocityTracker.obtain();
                    }
                    else{
                        velocityTracker.clear();
                    }
                    velocityTracker.addMovement(event);
                    break;
                case MotionEvent.ACTION_UP:
                    if (velocityTracker != null){
                        velocityTracker.recycle();
                        velocityTracker = null;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    testView.setXY(x1, y1);
                    testView.invalidate();
                    velocityTracker.addMovement(event);
                    velocityTracker.computeCurrentVelocity(1000);
                    Log.e("Velocity", "speed of x: " + velocityTracker.getXVelocity() +
                            "px/s" + "speed of y: " + velocityTracker.getYVelocity() + "px/s");
                    break;
                case MotionEvent.ACTION_CANCEL:
            }

            return true;
        }
    }
}