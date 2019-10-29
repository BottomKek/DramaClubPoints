package com.example.dramaclubpointsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText email, password;
    Button registerButton, loginButton;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.regButton);
        loginButton = (Button) findViewById(R.id.loginButton);


        firebaseAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();

                if(TextUtils.isEmpty(emailStr)){
                Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                return;
                 }
                if(TextUtils.isEmpty(passwordStr)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                }

                 if(password.length()<6){
                     Toast.makeText(getApplicationContext(),"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), RegisterInfo.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "E-mail or password is wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                //test login: test@gmail.com, password: testpw
                firebaseAuth.signInWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email or password wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}


