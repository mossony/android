package boyangwan.tk.messengerdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    final static String TAG = "messenger";
    final static int MSG_SAY_HELLO = 1;

    public MyService() {
    }

    class IncomingHandler extends Handler{
        public void handleMessage(Message message){
            switch (message.what){
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(),
                            "hello: "+message.getData().getString("data"),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(message);
        }
    }

    Messenger messenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Bind succeed");
        return messenger.getBinder();
    }
}