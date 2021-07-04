package com.example.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    SharedPreferences sharedPreferences;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        editText = findViewById(R.id.editText);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                String s = editText.getText().toString();
                saveData(s);
                break;
            case R.id.btn2:
                String name = sharedPreferences.getString("name", "Unknown user");
                Toast.makeText(this, "Hello, "+name, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void saveData(String s){
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", s);
        editor.apply();
    }
}