package boyangwan.tk.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ButtonStyleActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        textView = findViewById(R.id.text_view);
    }

    public void showMSG_1(View view){

        if (((ToggleButton)view).isChecked()){
            textView.setText("Toggle is ON");
        }
        else{
            textView.setText("Toggle is OFF");
        }
    }

    public void showMSG_2(View view){

        if (((Switch)view).isChecked()){
            textView.setText("Switch is ON");
        }
        else{
            textView.setText("Switch is OFF");
        }
    }
}