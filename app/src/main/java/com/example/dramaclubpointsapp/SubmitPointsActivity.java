package com.example.dramaclubpointsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class SubmitPointsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_points);




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
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        EditText first = (EditText) findViewById(R.id.editText);
        EditText last = (EditText) findViewById(R.id.editText2);
        EditText productionName = (EditText) findViewById(R.id.editText3);
        EditText companyName = (EditText) findViewById(R.id.editText4);
        TextView getGrade = (TextView) findViewById(R.id.problemSolver);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        EditText meme = (EditText) findViewById(R.id.editText6);

        String firstName = first.getText().toString();
        String lastName = last.getText().toString();
        String grade = getGrade.getText().toString();
        String role = spinner.getSelectedItem().toString();
        Date currentTime = Calendar.getInstance().getTime();
        String time = currentTime.toString();
        String prod = productionName.getText().toString();
        String memes = meme.getText().toString();

        if (first.getText().toString().equals("") || last.getText().toString().equals("") || spinner.getSelectedItem().toString().equals("Role in Production:")){

            Toast toast = Toast.makeText(SubmitPointsActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            first.setText("");
            last.setText("");
            productionName.setText("");
            companyName.setText("");






            databaseHelper.addData(time,firstName, lastName, grade, prod, memes);

            String sent = firstName + " " + lastName + ", " + grade + ", " + role + ".";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, sent);
            String chooserTitle = "Choose an app to send your message";
            Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
            startActivity(chosenIntent);


        }


    }
}
