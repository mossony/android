package com.example.contentproviderdemo1;

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

    String mSelectionClause;
    String[] mSelectionArgs;
    String[] SQL_COLUMN = new String[]{ContactsContract.CommonDataKinds.Identity.RAW_CONTACT_ID,
                                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                        ContactsContract.CommonDataKinds.Phone.NUMBER};

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
        String name1 = name.getText().toString();
        if (!name1.equals("")){
            Cursor cursor = getContact(name1);
            printQueryResult(cursor);
        }

    }

    private Cursor getContact(String searchName){

        mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME + "=?";
        mSelectionArgs = new String[]{searchName};
        return getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,SQL_COLUMN,
                mSelectionClause, mSelectionArgs, null);
    }

    public void insertClick(View view){
        String name1 = name.getText().toString();
        String phoneNum = phone.getText().toString();

        ContentValues values = new ContentValues();
        Uri rawContentUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContentID = ContentUris.parseId(rawContentUri);
        if (!name1.equals("")){
            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContentID);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name1);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }

        if (!phoneNum.equals("")){
            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContentID);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNum);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
            Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteClick(View view){
        String name1 = name.getText().toString();
        if (!name1.equals("")){
            getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.Contacts.DISPLAY_NAME + "=?", new String[]{name1});
            Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateClick(View view){
        String name1 = name.getText().toString();
        String phoneNum = phone.getText().toString();

        Long rawContactID = 0L;
        Uri uri = Uri.parse("content://com.android.contacts/data");
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("data1", phoneNum);

        if (!name1.equals("")){
            Cursor cursor = getContact(name1);
            if (cursor.moveToFirst()){
                rawContactID = cursor.getLong(0);
            }
            resolver.update(uri, values, "mimetype=? and raw_contact_id=?", new String[]{ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, rawContactID+""});
            cursor.close();
        }
    }

    public void queryAllClick(View view){
        // content://com.android.contacts/data/phones

        @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                SQL_COLUMN, null, null, null);
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