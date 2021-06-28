package boyangwan.tk.bindingservicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyBindService mService = null;
    boolean Binded = false;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MyBindService", "onServiceConnected");
            MyBindService.MyBinder myBinder = (MyBindService.MyBinder) service;
            mService = myBinder.getService();
            Binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyBindService", "onServiceDisconnected");
            Binded = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindService(View v){
        Intent intent = new Intent(this, MyBindService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    public void unbindService(View v){
        if (!Binded){
            Toast.makeText(this, "Service already unbinded", Toast.LENGTH_SHORT).show();
            return;
        }
        unbindService(mConnection);
        Binded = false;
    }

    public void getData(View v){

        if (!Binded){
            Toast.makeText(this, "Service not yet established", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Random number generated: "+mService.getRandNum(), Toast.LENGTH_SHORT).show();
    }

}