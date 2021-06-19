package boyangwan.tk.widgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButtonActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
    }


    public void submit(View view){
//        RadioButton male = findViewById(R.id.MaleRadioButton);
//        RadioButton female = findViewById(R.id.FemaleRadioButton);
        RadioGroup group = findViewById(R.id.RadioGroup);
        TextView textView = findViewById(R.id.infoTextView);
        EditText nameBox = findViewById(R.id.nameEditText);
        String name = nameBox.getText().toString();
        RadioButton chosen = findViewById(group.getCheckedRadioButtonId());

        textView.setText("Gender: " + chosen.getText() + " Name: " + name);


    }
}