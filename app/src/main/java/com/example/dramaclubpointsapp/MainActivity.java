package com.example.dramaclubpointsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goSubmitButton(View v){

        Intent intent = new Intent(this, SubmitPointsActivity.class);
        startActivity(intent);
    }

    public void goHistoryButton(View v){
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        startActivity(intent);
    }
}
