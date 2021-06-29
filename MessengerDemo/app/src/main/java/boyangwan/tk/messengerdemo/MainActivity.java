package boyangwan.tk.messengerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    boolean mBind = false;
    Messenger receiveMessenger = null;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("messenger", "Connected");
            receiveMessenger = new Messenger(service);
            mBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("messenger", "Disconnected");
            mBind = false;
        }
    };

    // Bind when activity started
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    // Unbind when activity stopped
    @Override
    protected void onStop() {
        super.onStop();
        if (mBind){
            unbindService(mConnection);
            mBind = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayHello(View v){

        if (!mBind){
            return;
        }

        Message message = Message.obtain(null, MyService.MSG_SAY_HELLO);
        Bundle bundle = new Bundle();
        bundle.putString("data", "client");
        message.setData(bundle);

        try {
            receiveMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}