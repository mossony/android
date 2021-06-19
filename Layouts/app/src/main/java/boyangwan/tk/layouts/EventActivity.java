package boyangwan.tk.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_imageview);

        View img = findViewById(R.id.image_fragment);
        img.setOnClickListener(new listener());

    }

    public class listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            TextView tv = findViewById(R.id.text_view);
            tv.setText("This is a new text");
            EventActivity.this.setTitle("New Title");
        }
    }



}