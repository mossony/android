package boyangwan.tk.intentdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){

        if(view.getId() == R.id.button1) {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName(this, A_Activity.class);
            intent.setComponent(componentName);
            startActivity(intent);
        }
        if(view.getId() == R.id.button2) {
            Intent intent = new Intent();
            // Action "button2" defined in AndroidManifest
            intent.setAction("button2");
            startActivity(intent);

        }


    }

}