package boyangwan.tk.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        // React to the event of the button
        EditText msg = findViewById(R.id.message);
        String s = msg.getText().toString();
//        System.out.println(s);
//        Log.i("message", s);

        // First method to switch activity
//        Intent intent = new Intent(this, SecondActivity.class);
//        this.startActivity(intent);

        // Second method to switch activity
//         Intent intent = new Intent();
//        intent.setClassName(this, "boyangwan.tk.mainactivity.SecondActivity");
//        startActivity(intent);

        // Third method to switch activity
//        Intent intent = new Intent();
//        ComponentName cname = new ComponentName(this, SecondActivity.class);
//        intent.setComponent(cname);
//        startActivity(intent);

        // Implicitly launch activity
//        Intent intent = new Intent();
//        intent.setAction("action.nextActivity");
//        startActivity(intent);

        // Implicitly launch activity
        Intent intent = new Intent("action.nextActivity");
        startActivity(intent);





    }
}