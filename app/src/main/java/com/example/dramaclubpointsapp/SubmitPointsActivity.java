package com.example.dramaclubpointsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubmitPointsActivity extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = fUser.getUid();
    private double currentPoints;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_points);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User testuser = dataSnapshot.getValue(User.class);
                currentPoints=testuser.getPoints();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void goHomeButton(View v){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goHistoryButton(View v){
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View v){
        TextView yes = (TextView) findViewById(R.id.problemSolver);

        Boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()){
            case R.id.radioButton:
                if(checked) {
                   yes.setText("freshmen");
                }
                break;
            case R.id.radioButton2:
                if(checked) {
                    yes.setText("sophomore");
                }
                break;
            case R.id.radioButton3:
                if(checked) {
                    yes.setText("junior");
                }
                break;
            case R.id.radioButton4:
                if(checked){
                    yes.setText("senior");
                }

        }

    }
    public void submitPoints(View v){
        DatabaseHelper databaseHelper = new DatabaseHelper(this, null, null, 1);

        EditText first = (EditText) findViewById(R.id.editText);
        EditText last = (EditText) findViewById(R.id.editText2);
        EditText productionName = (EditText) findViewById(R.id.editText3);
        EditText companyName = (EditText) findViewById(R.id.editText4);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        EditText meme = (EditText) findViewById(R.id.editText6);
        EditText report = (EditText) findViewById(R.id.editText5);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        String role = spinner.getSelectedItem().toString();
        String firstName = first.getText().toString();
        String lastName = last.getText().toString();
        String time = dateFormat.format(calendar.getTime());
        String prod = productionName.getText().toString();
        String memes = meme.getText().toString();
        double points;

        if (role.equals("Major Role - 8") || role.equals("Stage Manager - 8") || role.equals("Student Assistant - 8")){
            points = 8;
        }
        else if (role.equals("Crew Head - 6")){
            points = 6;
        }
        else if (role.equals("Supporting Role - 5")){
            points = 5;
        }
        else if(role.equals("Dance Captain - 4") || role.equals("Ensemble for GI - 4")){
            points = 4;
        }
        else if(role.equals("Chorus/Walk On - 3") || role.equals("Set Crew - 3") || role.equals("Props Crew - 3") || role.equals("Tech Crew - 3") || role.equals("Costumes/Make Up Crew - 3") || role.equals("Musician/Pit Orchestra - 3") || role.equals("Showchoir - 3") || role.equals("Theatre Fest - 3")){
            points = 3;
        }
        else if (role.equals("Running Crew - 2") || role.equals("Publicity Crew - 2")){
            points = 2;
        }
        else if(role.equals("Hang and Focus - 1") || role.equals("Variety Show Performer (besides Showchoir) - 1")){
            points = 1;
        }
        else if(role.equals("Let The Stars Come Out - 0.5")){
            points = 0.5;
        }
        else if(role.equals("Audience - 0.25")){
            points = 0.25;
        }
        else {
            points = 0;
        }

        if (first.getText().toString().equals("") || last.getText().toString().equals("") || spinner.getSelectedItem().toString().equals("Role in Production:") || meme.getText().toString().equals("")){

            Toast toast = Toast.makeText(SubmitPointsActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            first.setText("");
            last.setText("");
            productionName.setText("");
            companyName.setText("");
            report.setText("");
            meme.setText("");

            Toast toast = Toast.makeText(SubmitPointsActivity.this, "Submission Successful!!", Toast.LENGTH_LONG);
            toast.show();

            PointSubmission sub = new PointSubmission(time,firstName, lastName, prod, points, memes);
            databaseHelper.addSubmission(sub);

            //double newPoints = currentPoints+points;
            //Toast.makeText(getApplicationContext(),"old points: " + currentPoints,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"points to be added: " + points,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"points: " + newPoints,Toast.LENGTH_SHORT).show();
            //toast.show();

            mDatabase.child("users").child(uid).child("points").setValue(currentPoints+points);

        }

    }
}
