package com.example.internalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button save_btn;
    Button read_btn;
    EditText editName, editPassword;
    TextView textView;
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
        FileOutputStream fileOutputStream = openFileOutput("hello", Context.MODE_PRIVATE);
        fileOutputStream.write((name+"##"+password).getBytes());
        fileOutputStream.close();
        Toast.makeText(this, "Info saved", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    public void read(View v) throws IOException {
        FileInputStream fileInputStream = openFileInput("hello");
        byte[] input = new byte[fileInputStream.available()];
        while(fileInputStream.read(input) != -1){
            String str = new String(input);
            String [] s = str.split("##");
            textView.setText("Username: " + s[0] + "  Password: " + s[1]);

        }
        fileInputStream.close();
    }

}