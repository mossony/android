package boyangwan.tk.servicelifecycledemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    MediaPlayer player1;
    MediaPlayer player2;

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i("hello", "onCreate is running");
        Toast.makeText(this, "Creating background service", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("hello", "onStartCommand is running");
        Toast.makeText(this, "Starting background service", Toast.LENGTH_SHORT).show();

        if (intent.getExtras().get("music").equals("R.raw.music1")){
            player1 = MediaPlayer.create(this, R.raw.music1);
            player1.start();
        }
        else if (intent.getExtras().get("music").equals("R.raw.music2")){
            player2 = MediaPlayer.create(this, R.raw.music2);
            player2.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("hello", "onDestroy is running");
        Toast.makeText(this, "Destroying background service", Toast.LENGTH_SHORT).show();

        if (player1 != null){
            player1.release();
            player1 = null;
        }
        if (player2 != null){
            player2.release();
            player2 = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("hello", "onUnbind is running");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i("hello", "onRebind is running");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("hello", "onBind is running");
        return null;
    }
}