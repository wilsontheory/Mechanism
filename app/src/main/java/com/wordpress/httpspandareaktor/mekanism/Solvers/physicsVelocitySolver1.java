package com.wordpress.httpspandareaktor.mekanism.solvers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wordpress.httpspandareaktor.mekanism.R;
import com.wordpress.httpspandareaktor.mekanism.ShowCalculation;

/**
 * Created by Brian on 1/26/2017.
 */

public class physicsVelocitySolver1 extends AppCompatActivity{

    private double finalConstantVelocity;
    private boolean finalConstantVelocityExists = false;

    private double finalDisplacement;
    private boolean finalDisplacementExists = false;

    private double finalTime;
    private boolean finalTimeExists = false;


    //String for sending text/email messages with the problem
    String shareString;

    //declare parameters that will be sent by the intent to ShowCalculation
    String resultType;
    String resultValue;
    String resultValue2;

    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phys_velo_solver_1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create the onClickListener for button that will trigger the calculation
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        Button calcButton = (Button) findViewById(R.id.eq7_trigger_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }


    public void EQ7velocityEnable(View view) {
//this method is declared for the first checkbox to allow inputting of final velocity to work
        EditText editText = (EditText) findViewById(R.id.eq7_input_velocity);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq7_check_velocity);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalConstantVelocityExists = false;
        }
    }

    public void EQ7displacementEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq7_input_displacement);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq7_check_displacement);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalDisplacementExists = false;

        }
    }

    public void EQ7timeEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq7_input_time);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq7_check_time);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalTimeExists = false;

        }
    }


    //The final method for sending the accumulated data to the ShowCalculation activity

    public void sendData() throws NullPointerException {
        //figure out the final variables
        calculateAnswer();
        if (vetData()) {
            mMediaPlayer.start();
            buildShareString(determineAlgo());
            Log.v("sendData", "Share string sent is " + shareString);
            try {
                Intent i = new Intent(this, ShowCalculation.class);
                i.putExtra("resultType", resultType);
                i.putExtra("resultValue", resultValue);
                i.putExtra("resultValue2", resultValue2);
                i.putExtra("shareString", shareString);
                // send extra as a string
                i.putExtra("rootClass", "Physics");
                Log.v("sendData method", "sent" + resultType + resultValue + resultValue2);
                startActivity(i);
            } catch (NullPointerException e) {
                Log.v("MainActivity.java", "Null Pointer Exception" + e);
            }
        } else {
            Toast.makeText(this, "Need exactly 2 fields filled with numbers", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean vetData() {
        if (!finalConstantVelocityExists && finalDisplacementExists && finalTimeExists) {
            return true;
        } else if (finalConstantVelocityExists && finalDisplacementExists && !finalTimeExists) {
            return true;
        } else if (finalConstantVelocityExists && !finalDisplacementExists && finalTimeExists) {
            return true;
        } else  {
            return false;
        }
    }


    private String determineAlgo() {

        // the algorithm for solving stored as an array
        int[] algo = new int[3];

        //see if first field has value, if yes set it and make existence boolean true, mark in algo

        EditText firstField = (EditText) findViewById(R.id.eq7_input_velocity);
        if (firstField.length() != 0) {
            finalConstantVelocityExists = true;
            finalConstantVelocity = Double.parseDouble(firstField.getText().toString());
            algo[0] = 1;
        } else {
            algo[0] = 0;
            finalConstantVelocityExists = false;
        }

        //see if second field has value, if yes set it and make existence boolean true
        EditText secondField = (EditText) findViewById(R.id.eq7_input_displacement);
        if (secondField.length() != 0) {
            finalDisplacementExists = true;
            finalDisplacement = Double.parseDouble(secondField.getText().toString());
            algo[1] = 1;
        } else {
            finalDisplacementExists = false;
            algo[1] = 0;
        }

        //see if third field has value, if yes set it and make existence boolean true
        EditText thirdField = (EditText) findViewById(R.id.eq7_input_time);
        if (thirdField.length() != 0) {
            finalTimeExists = true;
            finalTime = Double.parseDouble(thirdField.getText().toString());
            algo[2] = 1;
        } else {
            finalTimeExists = false;
            algo[2] = 0;
        }


        String finalString = "";
        finalString += String.valueOf(algo[0]);
        finalString += String.valueOf(algo[1]);
        finalString += String.valueOf(algo[2]);

        Log.v("Algo is", finalString);
        return finalString;

    }


    private void calculateAnswer() {

        //retrieve solution method with key algo
        String solutionMethod = determineAlgo();
        Log.v("calculateAnswer", "the algo in calculateAnswer is " + solutionMethod);
        switch (solutionMethod) {
            case "011":
                resultType = "constant velocity = ";
                resultValue = String.valueOf(finalDisplacement / finalTime);

                break;
            case "101":
                resultType = "displacement = ";
                resultValue = String.valueOf(finalConstantVelocity * finalTime);

                break;
            case "110":
                resultType = "time = ";
                resultValue = String.valueOf(finalDisplacement / finalConstantVelocity);

                break;
            default:
                Log.v("Broken switch statement", " " + solutionMethod);

        }
    }


    private void buildShareString(String solutionMethod) {
        switch (solutionMethod) {
            // create the string for sharing via email or text message
            case "011":
                shareString = "[Mekanism]: given change in displacement (" + finalDisplacement + ") , change in time " +
                        "(" + finalTime + "); constant velocity =";
                break;

            case "101":
                shareString = "[Mekanism]: given constant velocity (" + finalConstantVelocity + ") , time " +
                        "(" + finalTime + ") , ; displacement =";
                break;

            case "110":
                shareString = "[Mekanism]: given velocity (" + finalConstantVelocity + ") , final displacement " +
                        "(" + finalDisplacement + "); time =";
                break;

            default:
                Log.v("Switch buildShareString", " " + solutionMethod);
        }
    }
}
