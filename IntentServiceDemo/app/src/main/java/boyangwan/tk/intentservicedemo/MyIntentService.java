package boyangwan.tk.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String num = (String) intent.getExtras().get("num");
            if ("1".endsWith(num)){
                for (int i = 1;i<=10;i++){
                    try {
                        Thread.sleep(1000);
                        Log.i("test", Thread.currentThread().getName()+"--"+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if("2".endsWith(num)){
                for (int i = 11;i<=20;i++){
                    Log.i("test", Thread.currentThread().getName()+"--"+i);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("test", "Service ended");
    }

}