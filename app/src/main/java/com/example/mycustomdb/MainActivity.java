package com.example.mycustomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper myHelper;

    private static final String tag = "MainActivity";

    private static ArrayList<Customer> allCustomers;

    // Called the first time you open the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create SQLiteOpenHelper object
        myHelper = new DBHelper(MainActivity.this);

        myHelper.getWritableDatabase(); // Create database

        // display database name
//        Toast.makeText(this, myHelper.getDatabaseName(), Toast.LENGTH_SHORT).show();

        myHelper.createTable("customer_tbl");
    }

    // onResume is called when the application is relaunched


    @Override
    protected void onResume() {
        super.onResume();
//        // Insert record using execSQL() method
//        myHelper.insertRecord("INSERT INTO customer_tbl(name, salary, hire_date) VALUES('Tuan', 7500.00, '2019/12/28')");
//
//        // Insert record using insert() method
//        long result = myHelper.addRecord("name", "Tom", "salary", 9000.00, "hire_date", "2020/12/12");
//
//        if(result == -1) {
//            Toast.makeText(this, "Insertion error!", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this, "Record inserted!", Toast.LENGTH_SHORT).show();
//        }
//           Delete rows using execSQL()
//        myHelper.deleteRecord("DELETE FROM customer_tbl WHERE id=1");

//      // Delete using delete()
//      // Notice you put placeholders in the WHERE clause without the WHERE keyword
//      String[] whereArgs = {"11"};
//      int rows = myHelper.removeRecord("customer_tbl", "id = ? ", whereArgs);
//      Toast.makeText(MainActivity.this, "Rows deleted " + rows, Toast.LENGTH_SHORT).show();

//        // Update using execSQL
//        myHelper.updateRecord("UPDATE customer_tbl SET name='Tuan', salary='1000.50', hire_date='2020/01/01' WHERE id='15'");

//        // Update using update() method
//        Object[] cvArr = {"Tom", 2500.99, "2020/02/02"};
//        String[] whereArgs = {"14"};
//        int rowsUpdated = myHelper.changeRecord("customer_tbl", cvArr, "id=?", whereArgs);
//        Toast.makeText(MainActivity.this, rowsUpdated + " rows updated!", Toast.LENGTH_LONG).show();

        // Call readRecords() method
        try {
            allCustomers = myHelper.readRecords("SELECT * FROM customer_tbl");

            for(Customer customer: allCustomers) {
                // Use Log.d to write to logCat console
                Log.d(tag, customer.getId() + ", " + customer.getName() + ", " + customer.getSalary() + ", " + customer.getHire_date());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(tag, e.getMessage());
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Closing database when home button is pressed
        // Important to avoid memory leaks
        myHelper.close();
        Toast.makeText(this, myHelper.getDatabaseName() + " closed!", Toast.LENGTH_SHORT).show();
    }
}
