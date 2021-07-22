package com.example.multiactiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    int mActivePointerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mActivePointerID = event.getPointerId(0);
        int pointerIndex = event.findPointerIndex(mActivePointerID);
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);
        Log.d("MultiAction", "The action is: " + actionToString(event.getAction()));
        if (event.getPointerCount() > 1){
            for (int i = 0;i < event.getPointerCount();i++){
                pointerIndex = event.findPointerIndex(i);
                x = event.getX(pointerIndex);
                y = event.getY(pointerIndex);
                Log.d("MultiAction", "Multi touch event, pointer " + i + ", x:" + x + "y: " + y);

            }
        }
        else{
            Log.d("MultiAction", "Single touch event: " + "x:" + x + "y: " + y);
        }
        return true;
    }

    public static String actionToString(int action){
        switch (action){
            case MotionEvent.ACTION_DOWN:
                return "down";
            case MotionEvent.ACTION_MOVE:
                return "move";
            case MotionEvent.ACTION_CANCEL:
                return "cancel";
            case MotionEvent.ACTION_UP:
                return "up";
            case MotionEvent.ACTION_POINTER_UP:
                return "pointer up";
            case MotionEvent.ACTION_OUTSIDE:
                return "outside";
            case MotionEvent.ACTION_POINTER_DOWN:
                return "pointer down";
        }
        return "";
    }

}