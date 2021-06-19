package boyangwan.tk.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        textView = findViewById(R.id.sport_text_view);
    }

    public void onCheckBoxClicked(View view){
        if ((((CheckBox)view).isChecked())) {
            Toast.makeText(CheckBoxActivity.this, ((CheckBox) view).getText(), Toast.LENGTH_SHORT).show();
        }

    }
}