package com.example.moviedemo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    Button play_btn;
    MediaPlayer player;
    SurfaceHolder surfaceHolder;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surface_view1);
        play_btn = findViewById(R.id.play_btn);
        player = new MediaPlayer();

        path = "/sdcard/Movies/forthenight.mp4";

        if (Build.VERSION.SDK_INT >= 23){
            int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }

    }

    public void play(View view) throws IOException {
        player.reset();
        player.setDataSource(path);
        if (Build.VERSION.SDK_INT >= 23){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                    .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED).build();
            player.setAudioAttributes(audioAttributes);
        }
        else{
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        surfaceHolder = surfaceView.getHolder();
        player.setDisplay(surfaceHolder);
        player.prepare();
        player.start();

    }
}