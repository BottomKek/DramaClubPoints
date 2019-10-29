package com.example.dramaclubpointsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
    }

    public void goHomeButton(View v){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goSubmitButton(View v){

        Intent intent = new Intent(this, SubmitPointsActivity.class);
        startActivity(intent);
    }
}
