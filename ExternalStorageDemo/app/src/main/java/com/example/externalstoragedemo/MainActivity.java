package com.example.externalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button save_btn;
    Button read_btn;
    EditText editName, editPassword;
    TextView textView;

    boolean externalAvailable = false;
    boolean externalWriteable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save_btn = findViewById(R.id.save_btn);
        read_btn = findViewById(R.id.read_btn);
        editName = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        textView = findViewById(R.id.txt);
    }

    public void save(View v) throws IOException {
        String name = editName.getText().toString();
        String password = editPassword.getText().toString();

        // Check status of external storage
        checkExternalStorage();
        if (externalWriteable){
            OutputStream outputStream = new FileOutputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "hello"));
            outputStream.write((name+"##"+password).getBytes());
            outputStream.close();
            Toast.makeText(this, "Info saved to external storage", Toast.LENGTH_SHORT).show();
        }



    }

    @SuppressLint("SetTextI18n")
    public void read(View v) throws IOException {
        checkExternalStorage();

        if (externalAvailable){
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "hello");
            if (file.exists()){
                FileInputStream in = new FileInputStream(file);
                byte[] input = new byte[in.available()];
                while(in.read(input) != -1){
                    String str = new String(input);
                    String [] s = str.split("##");
                    textView.setText("Username: " + s[0] + "  Password: " + s[1]);
                }
                in.close();
            }
        }


    }

    public void checkExternalStorage(){
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            externalAvailable = true;
            externalWriteable = true;
        }
        else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            externalAvailable = true;
            externalWriteable = false;
        }
        else {
            externalAvailable = false;
            externalWriteable = false;
        }

    }

}