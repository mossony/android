package com.example.musicplayerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView txt1;
    ImageButton play_btn, pause_btn, stop_btn;
    RadioButton radio1, radio2;

    MediaPlayer mediaPlayer;

    int res_id = R.raw.music;
    String sdcard_file = "/sdcard/Music/20200509.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.text1);
        play_btn = findViewById(R.id.play_btn);
        pause_btn = findViewById(R.id.pause_btn);
        stop_btn = findViewById(R.id.stop_btn);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);

        play_btn.setOnClickListener(new playClick());
        pause_btn.setOnClickListener(new pauseClick());
        stop_btn.setOnClickListener(new stopClick());

        mediaPlayer = new MediaPlayer();

        // Need to ask for permission dynamically after android 6.0
        if(Build.VERSION.SDK_INT >= 23){
            int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    class playClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (radio1.isChecked()){
                mediaPlayer = MediaPlayer.create(MainActivity.this, res_id);
                mediaPlayer.start();
                txt1.setText(radio1.getText());
            }
            else if (radio2.isChecked()){
                try {
                    playMusic(sdcard_file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txt1.setText(radio2.getText());
            }

        }

        public void playMusic(String path) throws IOException {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
    }



    class pauseClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }
    }

    class stopClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.reset();
                mediaPlayer.release();
            }
        }
    }




}