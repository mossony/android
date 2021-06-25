package boyangwan.tk.intentdemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go(View view){
        EditText text1 = findViewById(R.id.text1);
        EditText text2 = findViewById(R.id.text2);

        Intent intent = new Intent();
        ComponentName cmp = new ComponentName(this, SecondActivity.class);
        intent.setComponent(cmp);

        Bundle bundle = new Bundle();
        bundle.putString("text1", text1.getText().toString());
        bundle.putString("text2", text2.getText().toString());

        intent.putExtras(bundle);
        startActivity(intent);

    }
}