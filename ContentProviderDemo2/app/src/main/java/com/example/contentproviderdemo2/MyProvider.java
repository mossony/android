package com.example.contentproviderdemo2;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {

    MySQLiteDBHelper dbHelper;
    SQLiteDatabase db;
    public static final int USER_DIR = 0;
    public static final int USER_ITEM = 1;

    public static final String AUTHORITY = "com.example.provider";

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "user", USER_DIR);
        uriMatcher.addURI(AUTHORITY, "user/#", USER_ITEM);
    }


    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteDBHelper(getContext(), "user.db", null, 1);
        db = dbHelper.getWritableDatabase();
        return true;
    }

    @SuppressLint("Recycle")
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case USER_DIR:
                cursor = db.query("user", projection, selection, selectionArgs, null, null, null);
                break;
            case USER_ITEM:
                String queryID = uri.getPathSegments().get(1);
                cursor = db.query("user", projection, "_id = ?", new String[]{queryID}, null, null, null);
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.provider.user";
            case USER_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.provider.user";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case USER_DIR:
            case USER_ITEM:
                long newUserId = db.insert("user", null, values);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleteInt = 0;
        switch (uriMatcher.match(uri)) {
            case USER_DIR:
                deleteInt = db.delete("user", selection, selectionArgs);
                break;
            case USER_ITEM:
                String deleteId = uri.getPathSegments().get(1);
                deleteInt = db.delete("user", "_id = ?", new String[]{deleteId});
                break;
            default:
                break;
        }
        return deleteInt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updateRow = 0;
        switch (uriMatcher.match(uri)) {
            case USER_DIR:
                updateRow = db.update("user", values, selection, selectionArgs);
                break;
            case USER_ITEM:
                String updateId = uri.getPathSegments().get(1);
                updateRow = db.update("user", values, "_id=?", new String[]{updateId});
                break;
            default:
                break;
        }
        return updateRow;
    }
}
