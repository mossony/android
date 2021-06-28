package boyangwan.tk.bindingservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyBindService extends Service {

    final static String TAG = "MyBindService";
    IBinder iBinder = null;
    Random mGenerator;

    public class MyBinder extends Binder{
        MyBindService getService(){
            return MyBindService.this;
        }
    }

    public MyBindService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        iBinder = new MyBinder();
        mGenerator = new Random();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return iBinder;
    }

    public int getRandNum(){
        return mGenerator.nextInt(100);
    }


}