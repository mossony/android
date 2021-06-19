package boyangwan.tk.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        findViewById(R.id.default_toast).setOnClickListener(this);
        findViewById(R.id.customize_toast).setOnClickListener(this);
        findViewById(R.id.image_toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast toast = null;
        switch (v.getId()){
            case R.id.default_toast:
                Toast.makeText(getApplicationContext(), "Default Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.customize_toast:
                toast = Toast.makeText(getApplicationContext(), "Customized Position", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case R.id.image_toast:
                toast = Toast.makeText(getApplicationContext(), "With Image", Toast.LENGTH_SHORT);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.ic_launcher_background);
                toastView.addView(imageView);
                toast.show();
                break;
        }
    }
}