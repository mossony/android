package com.example.recordingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button startBtn, stopBtn;
    MediaRecorder mediaRecorder;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.btn_1);
        stopBtn = findViewById(R.id.btn_2);

        startBtn.setOnClickListener(new onClick());
        stopBtn.setOnClickListener(new onClick());

        path = "/sdcard/Music/audio.amr";

        if (Build.VERSION.SDK_INT >= 23){
            String [] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};
            for (int i = 0; i < permissions.length;i++){
                if (this.checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED){
                    this.requestPermissions(permissions, i);
                }
            }
        }
    }


    class onClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v == startBtn){
                try {
                    startRecordAudio(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (v == stopBtn){
                stopRecordAudio();
            }
        }

        public void startRecordAudio(String path) throws IOException {
            if (mediaRecorder == null){
                mediaRecorder = new MediaRecorder();
            }
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(path);
            mediaRecorder.prepare();
            mediaRecorder.start();
        }

        public void stopRecordAudio(){
            if (mediaRecorder != null){
                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder = null;
            }

        }
    }
}