
package com.example.dramaclubpointsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterInfo extends AppCompatActivity {

    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    private static final String TAG = "MyActivity";

    private DatabaseReference mDatabase;

    private String uid = fUser.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //https://stackoverflow.com/questions/39704291/how-can-i-add-name-profile-pic-address-of-a-user-to-firebase-database/39706279
    //set username

    public void finishRegistering(View v){

        EditText username = (EditText) findViewById(R.id.uname);
        String nameStr = username.getText().toString();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nameStr)
                .build();

        fUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });

        User dbuser = new User(nameStr, 0);
        mDatabase.child("users").child(uid).setValue(dbuser);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}