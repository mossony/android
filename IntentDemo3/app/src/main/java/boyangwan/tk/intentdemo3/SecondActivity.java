package boyangwan.tk.intentdemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.hello);

        Bundle received = this.getIntent().getExtras();
        String txt1 = received.getString("text1");
        String txt2 = received.getString("text2");
        textView.setText("Hello, " + txt1 + ", " + txt2);
    }
}