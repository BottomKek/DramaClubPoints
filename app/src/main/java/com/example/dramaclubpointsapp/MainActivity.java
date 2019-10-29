package com.example.dramaclubpointsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();

        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        TextView username = (TextView) findViewById(R.id.textView2);
        username.setText(name);

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
