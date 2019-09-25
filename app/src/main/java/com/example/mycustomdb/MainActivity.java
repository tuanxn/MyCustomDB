package com.example.mycustomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper myHelper;

    // Called the first time you open the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create SQLiteOpenHelper object
        myHelper = new DBHelper(MainActivity.this);

        myHelper.getWritableDatabase(); // Create database

        // display database name
        Toast.makeText(this, myHelper.getDatabaseName(), Toast.LENGTH_SHORT).show();

        myHelper.createTable("customer_tbl");
    }

    // onResume is called when the application is relaunched


    @Override
    protected void onResume() {
        super.onResume();
        // Insert record using execSQL() method
//        myHelper.insertRecord("INSERT INTO customer_tbl(name, salary, hire_date) VALUES('Tuan', 7500.00, '2019/12/28')");

        // Insert record using insert() method
        long result = myHelper.addRecord("name", "Tom", "salary", 9000.00, "hire_date", "2020/12/12");

        if(result == -1) {
            Toast.makeText(this, "Insertion error!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Record inserted!", Toast.LENGTH_SHORT).show();
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
