package com.example.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    final static int DB_VERSION = 1;
    final static String DB_NAME = "CONTACT";
    final  String TABLE_NAME = "CONTACT_TABLE";
    public static final  String id = "Stt";
    final String name_column = "Name";
    final String phone_column = "Phone_number";
    final String type_column = "Contact_Type";


    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + name_column + " TEXT,"
                + phone_column + " TEXT,"
                + type_column + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }
    void addContact(String name, String phoneNumber, String Type){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name_column, name);
        values.put(phone_column, phoneNumber);
        values.put(type_column, Type);
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
