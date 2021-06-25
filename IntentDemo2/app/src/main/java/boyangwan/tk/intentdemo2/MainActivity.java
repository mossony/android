package boyangwan.tk.intentdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Open different apps according to the selected radio button
    @SuppressLint("NonConstantResourceId")
    public void sendIntent(View view){
        Intent intent = null;
        RadioGroup radioGroup = findViewById(R.id.radio);
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radio_button_1:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
                break;

            // call requires permission
            case R.id.radio_button_2:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1352632516"));
                break;

            case R.id.radio_button_3:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://1273738485"));
                break;

            // find the coordinate in the google map
            case R.id.radio_button_4:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:104.139,30.674"));
                break;

            // search for restaurants in the google map
            case R.id.radio_button_5:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=restaurant"));
                break;

            // take a picture
            case R.id.radio_button_6:
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                break;

            case R.id.radio_button_7:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                break;

            case R.id.radio_button_8:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/1"));
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

}