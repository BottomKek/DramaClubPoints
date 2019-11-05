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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = fUser.getUid();
    private double userpoints = 0;

    DatabaseReference userRef, rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();

        TextView username = (TextView) findViewById(R.id.textView2);
        username.setText(name);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User testuser = dataSnapshot.getValue(User.class);
                userpoints = testuser.getPoints();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        TextView pointstv = (TextView) findViewById(R.id.textView7);
        pointstv.setText("Total Points: " + userpoints +" points");

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
