package boyangwan.tk.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Handler handler = new Handler();
    int i = 0;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            i++;
            if(i == 100){
                handler.removeCallbacks(this);
            }
            progressBar.setProgress(i);
            handler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        progressBar = findViewById(R.id.horizontalBar);
        handler.postDelayed(runnable, 100);
    }


}