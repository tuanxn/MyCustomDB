package com.example.mycustomdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QCOM.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    You use the onCreate method to create a database, but it can only be used once
    To create a new DB, you need to delete the old one. Instead, we will create a new
    method used to create databases.
     */
    public void createTable(String tableName) {
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + tableName + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "salary DOUBLE, " +
                "hire_date DATE)");
    }

}
