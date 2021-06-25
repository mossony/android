package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNotification(View view){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;

        String id = "notificationChannel";
        String name = "channel 1";

        // When android version >= 8.0
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);

            notificationManager.createNotificationChannel(notificationChannel);

            // Define a Notification
            notification = new Notification.Builder(this, id)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.doge))
                    .setSmallIcon(R.drawable.ic_baseline_live_tv_24)
                    .setContentTitle("Doge notification")
                    .setContentText("Doge has an important news")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.doge)))
                    .setContentIntent(PendingIntent.getActivity(this, 1, new Intent(this, NotificationResult.class), PendingIntent.FLAG_CANCEL_CURRENT))
                    .build();

        }

        // When android version >= 4.1
        else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(this)
                    .setContentTitle("Doge notification")
                    .setContentText("Doge has an important news")
                    .setSmallIcon(R.drawable.ic_baseline_live_tv_24)
                    .setOngoing(true)
                    .setContentIntent(PendingIntent.getActivity(this, 1, new Intent(this, NotificationResult.class), PendingIntent.FLAG_CANCEL_CURRENT))
                    .build();
        }

        notificationManager.notify(0, notification);

    }
}