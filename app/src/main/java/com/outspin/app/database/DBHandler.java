package com.outspin.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String USER = "user";
    public static final String PHONE = "phone";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    public DBHandler(@Nullable Context context) {
        super(context, USER + ".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableUser = "CREATE TABLE " + USER +
                "(" + PHONE + " INT PRIMARY KEY, " + NAME + " TEXT, " + PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(createTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(int phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PHONE, phone);
        cv.put(PASSWORD, password);

        long insert = db.insert(USER, null, cv);
        if(insert == -1) return false;
        else return true;
    }

    public boolean checkUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        String select = "Select * FROM " + USER;
        Cursor cursor = db.rawQuery(select, null);
        boolean success = cursor.moveToFirst();

        cursor.close();
        db.close();

        return success;
    }
}


