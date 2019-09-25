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

    @Override
    protected void onPause() {
        super.onPause();
        // Closing database when home button is pressed
        // Important to avoid memory leaks
        myHelper.close();
        Toast.makeText(this, myHelper.getDatabaseName() + " closed!", Toast.LENGTH_SHORT).show();
    }
}
