package boyangwan.tk.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity {

    ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        img1 = findViewById(R.id.img1);
    }

    public void centerCrop(View view){
        img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public void centerInside(View view){
        img1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    public void fitCenter(View view){
        img1.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    public void fitEnd(View view){
        img1.setScaleType(ImageView.ScaleType.FIT_END);
    }

    public void fitStart(View view){
        img1.setScaleType(ImageView.ScaleType.FIT_START);
    }

}