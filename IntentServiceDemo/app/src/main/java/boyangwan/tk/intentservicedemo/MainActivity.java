package boyangwan.tk.intentservicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(new MClick());
        btn2.setOnClickListener(new MClick());
    }

    private class MClick implements View.OnClickListener{

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            intent = new Intent(MainActivity.this, MyIntentService.class);
            switch (v.getId()){
                case R.id.btn1:
                    intent.putExtra("num", "1");
                    break;
                case R.id.btn2:
                    intent.putExtra("num", "2");
                    break;
            }
            startService(intent);
        }
    }

}