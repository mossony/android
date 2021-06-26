package boyangwan.tk.servicelifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView info = null;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.info);
        Button btn_start1 = findViewById(R.id.btn_start1);
        Button btn_start2 = findViewById(R.id.btn_start2);
        Button btn_stop = findViewById(R.id.btn_stop);
        intent = new Intent(this, MyService.class);
        btn_start1.setOnClickListener(new mClick());
        btn_stop.setOnClickListener(new mClick());
        btn_start2.setOnClickListener(new mClick());

    }

    class mClick implements View.OnClickListener{

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start1:
                    intent.putExtra("music", "R.raw.music1");
                    MainActivity.this.startService(intent);

                    break;
                case R.id.btn_start2:
                    intent.putExtra("music", "R.raw.music2");
                    MainActivity.this.startService(intent);

                    break;
                case R.id.btn_stop:
                    MainActivity.this.stopService(intent);
                    break;
            }
        }
    }
}