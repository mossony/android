package com.example.toucheventdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TextView2 extends View {

    int color;

    public TextView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColor(int _color){
        color = _color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this.getContext(), "onTouchEvent.."+event.getAction(), Toast.LENGTH_SHORT).show();
        if (color == Color.RED){
            return true;
        }

        return false;
    }
}
