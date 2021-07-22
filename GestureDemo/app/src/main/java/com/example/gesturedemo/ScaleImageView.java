package com.example.gesturedemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;


import androidx.appcompat.widget.AppCompatImageView;

public class ScaleImageView extends AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {

    ScaleGestureDetector mScaleGestureDetector;
    Matrix mScaleMatrix = new Matrix();
    float initScale = 1.0f;
    static final float MAX_SCALE = 6.0f;
    float[] matrixValues = new float[9];


    @SuppressLint("ClickableViewAccessibility")
    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        this.setOnTouchListener(this);
    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        if (scale < MAX_SCALE && scaleFactor > 1.0f || scale>initScale && scaleFactor < 1.0f){
            if (scaleFactor * scale < initScale){
                scaleFactor = initScale/scale;
            }
            if (scaleFactor * scale > MAX_SCALE){
                scaleFactor = MAX_SCALE/scale;
            }
        }
        mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
        setImageMatrix(mScaleMatrix);
        checkBorderAndCenterWhenScale();
        setImageMatrix(mScaleMatrix);

        return true;
    }

    private void checkBorderAndCenterWhenScale(){
        RectF rectF = getMatrixRectF();
        float dX = 0;
        float dY = 0;
        int width = getWidth();
        int height = getHeight();
        if (rectF.width() >= width){
            if (rectF.left > 0){
                dX = - rectF.left;
            }
            if (rectF.right < width){
                dX = width - rectF.right;
            }
        }
        if (rectF.height() >= height){
            if (rectF.top > 0){
                dY = -rectF.top;
            }
            if (rectF.bottom < height){
                dY = height - rectF.bottom;
            }
        }

        if (rectF.width() < width){
            dX = width * 0.5f - rectF.right + 0.5f * rectF.width();
        }
        if (rectF.height()<height){
            dY = height * 0.5f - rectF.bottom + 0.5f * rectF.height();
        }

        mScaleMatrix.postTranslate(dX, dY);
    }

    private RectF getMatrixRectF(){
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();
        Drawable d = getDrawable();
        if (null != d){
            rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    private float getScale(){
        mScaleMatrix.getValues(matrixValues);

        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }
}
