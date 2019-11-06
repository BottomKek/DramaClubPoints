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
    private double currentPoints;

    DatabaseReference mDatabase;

    //0-10 unranked, 10-70 thespian, 70-120 honors thespian,120-180 national honors thespian , 180+ international honors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView pointstv = (TextView) findViewById(R.id.textView7);
        final TextView ranktv = (TextView) findViewById(R.id.textView3);
        final TextView nextrank = (TextView) findViewById(R.id.textView5);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User testuser = dataSnapshot.getValue(User.class);
                currentPoints = testuser.getPoints();
                pointstv.setText("Total Points: " + currentPoints +" points");

                if(currentPoints <= 10){
                    ranktv.setText("Unranked");
                    nextrank.setText("Next Rank in: "+(10 - currentPoints)+" points");
                }

                if(currentPoints > 10 && currentPoints <= 70){
                    ranktv.setText("Thespian");
                    nextrank.setText("Next Rank in: "+(70 - currentPoints)+" points");
                }

                if(currentPoints > 70 && currentPoints <= 120){
                    ranktv.setText("Honors Thespian");
                    nextrank.setText("Next Rank in: "+(120 - currentPoints)+" points");
                }

                if(currentPoints > 120 && currentPoints <= 180){
                    ranktv.setText("National Honors Thespian");
                    nextrank.setText("Next Rank in: "+(180-currentPoints)+" points");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        String name = fUser.getDisplayName();

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
