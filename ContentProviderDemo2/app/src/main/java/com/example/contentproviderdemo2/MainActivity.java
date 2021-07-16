package com.example.contentproviderdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView contactView;
    EditText name;
    EditText phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactView = findViewById(R.id.contact_view);
        name = findViewById(R.id.text1);
        phone = findViewById(R.id.text2);

        if (Build.VERSION.SDK_INT >= 23){
            String[] permissions = new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};
            int i = 0;
            for (String permission: permissions){
                if (this.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                    this.requestPermissions(permissions, i++);
                }
            }
        }
    }

    public void queryClick(View view){
        contactView.setText("");
        String id = name.getText().toString();
        Uri uri = Uri.parse("content://com.example.provider/user/"+id);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        printQueryResult(cursor);


    }


    public void insertClick(View view){
        String name1 = name.getText().toString();
        String phoneNum = phone.getText().toString();

        Uri uri = Uri.parse("content://com.example.provider/user");
        ContentValues values = new ContentValues();
        values.put("username", name1);
        values.put("phonenumber", phoneNum);
        getContentResolver().insert(uri, values);
        Toast.makeText(this, "Content inserted", Toast.LENGTH_SHORT).show();

    }

    public void deleteClick(View view){
        String name1 = name.getText().toString();

        Uri uri = Uri.parse("content://com.example.provider/user");
        getContentResolver().delete(uri, "username=?", new String[]{name1});
        Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();

    }

    public void updateClick(View view){
        String name1 = name.getText().toString();
        String phoneNum = phone.getText().toString();

        Uri uri = Uri.parse("content://com.example.provider/user");
        ContentValues values = new ContentValues();
        values.put("phonenumber", phoneNum);
        getContentResolver().update(uri, values, "username=?", new String[]{name1});
        Toast.makeText(this, "Contact updated", Toast.LENGTH_SHORT).show();


    }

    public void queryAllClick(View view){
        contactView.setText("");
        Uri uri = Uri.parse("content://com.example.provider/user");

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        printQueryResult(cursor);
    }

    private void printQueryResult(Cursor cursor){
        if (cursor != null){
            contactView.setText("");
            while (cursor.moveToNext()){
                String ID = cursor.getString(0);
                String contactName = cursor.getString(1);
                String phoneNumber = cursor.getString(2);
                contactView.append("Contact ID: " + ID + "\nName: " + contactName + "\nphone: " + phoneNumber + "\n\n");
            }
            cursor.close();
        }
    }

}