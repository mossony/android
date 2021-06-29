package boyangwan.tk.servicelifecycledemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent = null;

    MyServiceConnection conn;

    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btn_start)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_bind)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_unbind)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_stop)).setOnClickListener(this);

        intent = new Intent(this, MyService.class);
        conn = new MyServiceConnection();

    }

    private int flag = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startService(intent);
                break;

            case R.id.btn_bind:
                if (++flag>1){
                    Log.e("TEST", "Already binded");
                }
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btn_unbind:
                unbindService(conn);
                flag = 0;
                break;

            case R.id.btn_stop:
                stopService(intent);
                break;
        }
    }
}