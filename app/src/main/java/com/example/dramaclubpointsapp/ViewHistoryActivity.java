package com.example.dramaclubpointsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ViewHistoryActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        databaseHelper = new DatabaseHelper(this, null, null, 1);
        displayTextView = (TextView) findViewById(R.id.displayTextView);
        printDB();
    }

    public void goHomeButton(View v){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goSubmitButton(View v){

        Intent intent = new Intent(this, SubmitPointsActivity.class);
        startActivity(intent);
    }

    public void printDB() {
        // calls the method that creates a string of all the database elements
        // sets this string to the text in Textview component

        String dbString = databaseHelper.databasetoString();
        displayTextView.setText(dbString);
    }
}
