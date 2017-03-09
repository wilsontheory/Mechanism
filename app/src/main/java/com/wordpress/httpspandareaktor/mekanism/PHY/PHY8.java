package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/8/2017.
 */

public class PHY8 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "A derived equation of motion relating displacement, " +
            "final velocity, initial velocity, and time.";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescDisplacement,
            PHYutils.PHYvarDescFinalVelocity,
            PHYutils.PHYvarDescInitialVelocity,
            PHYutils.PHYvarDescTime};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymDisplacement;
    public Spanned symbolValB = PHYutils.PHYvarSymFinalVelocity;
    public Spanned symbolValC = PHYutils.PHYvarSymInitialVelocity;
    public Spanned symbolValD = PHYutils.PHYvarSymTime;

    public Spanned unitValA = PHYutils.PHYvarUnitDisplacement;
    public Spanned unitValB = PHYutils.PHYvarUnitFinalVelocity;
    public Spanned unitValC = PHYutils.PHYvarUnitInitialVelocity;
    public Spanned unitValD = PHYutils.PHYvarUnitTime;

    public String solveMissing(String arrayCode, double param1, double param2, double param3){
        switch (arrayCode) {
            case "0111":
                return  String.valueOf((0.5)*(param1 + param2) * param3);
            case "1011":
                return String.valueOf(((2 * param1)/param3) - param2);
            case "1101":
                return String.valueOf(((2 * param1)/param3) - param2);
            case "1110":
                return String.valueOf((2 * param1)/(param2 + param3));
            default:
                return "error in solution method";

        }
    }


    //!!!DONE DEFINING UNIQUE STUFF !!!

    //variables for the actual inputs
    double varA;
    double varB;
    double varC;
    double varD;

    //array of the parameters to fill ThreeVar form
    public Spanned[] resourceArray = {symbolValA, symbolValB, symbolValC, symbolValD,
            unitValA, unitValB, unitValC, unitValD};

    public PHY8() {
    }
}
