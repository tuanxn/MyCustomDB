package com.example.mycustomdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    // Insert record into table using execSQL method
    public void insertRecord(String sqlStatement) {
        this.getWritableDatabase().execSQL(sqlStatement);
    }

    // Insert record using insert() method
    public long addRecord(String nameKey, String nameValue, String salaryKey, Double salaryValue, String dateKey, String dateValue) {

        // Create db object reference
        SQLiteDatabase db = this.getReadableDatabase();
        // Create ContentValues object
        ContentValues value = new ContentValues();

        // Insert key-value pairs into the ContentValues dictionary
        value.put(nameKey, nameValue);
        value.put(salaryKey, salaryValue);
        value.put(dateKey, dateValue);

        // Insert into table and return record ID or -1 if unsuccessful
        // 2nd argument will always be null
        return db.insert("customer_tbl", null, value);

    }

    // Delete method using execSQL() method
    public void deleteRecord(String sqlStatemnet) {
        this.getWritableDatabase().execSQL(sqlStatemnet);
    }

    // Delete() method used
    public int removeRecord(String tableName, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, whereClause, whereArgs);
    }

    // Update using execSQL() method
    public void updateRecord(String sqlStatement) {
        this.getWritableDatabase().execSQL(sqlStatement);
    }

    // Update using update() method
    public int changeRecord(String tableName, Object[] values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // Create ContentValue dictionary with column headers and values from the String array
        cv.put("name", values[0].toString());
        cv.put("salary", ((Double) values[1]));
        cv.put("hire_date", values[2].toString());
        return db.update(tableName, cv, whereClause, whereArgs);
    }

    // Create method that creates an array list of customer object records from table
    public ArrayList<Customer> readRecords(String sqlStatement) {
        // Create array list to hold all customer records
        ArrayList<Customer> allCustomers = new ArrayList<Customer>();
        // Create variables to hold value from each column
        int id;
        String name;
        double salary;
        String hire_date;
        // Create database instance
        SQLiteDatabase db = this.getWritableDatabase();
        // Create cursor object that is returned when we call rawQuery()
        // Provide null value for optional WHERE clause
        Cursor cursor = db.rawQuery(sqlStatement, null);
        // Loop through the returned cursor object while it has another record to read
        // Assign the values from each column to its respective variable
        // Instantiate a Customer object for each record and add it to the Customer array list
        while(cursor.moveToNext()) {
            id = cursor.getInt(0);
            name = cursor.getString(1);
            salary = cursor.getDouble(2);
            hire_date = cursor.getString(3);
            allCustomers.add(new Customer(id, name, salary, hire_date));
        }
        // Return the Customer array list
        return allCustomers;
    }

}
