package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY3;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/15/17.
 */

public class T4PHY3 implements Generator {
    //this is a tier 4 generator applying the PHY3 equation
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY3(){
    //generate a random code for the 3-var
    String randomizedPHY3 = GenUtils.generateRandomCode(3);
    //store the unit of the answer manually here
        switch (randomizedPHY3) {
        case "011":
            firstVar = GenUtils.generateRandomInRange(1, 50);
            secondVar = GenUtils.generateRandomInRange(1, 50);
            trueAnswerUnit = PHYutils.PHYvarUnitAverageAcceleration.toString();
            break;
        case "101":
            firstVar = GenUtils.generateRandomInRange(1, 20);
            secondVar = GenUtils.generateRandomInRange(1, 50);
            trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
            break;
        case "110":
            firstVar = GenUtils.generateRandomInRange(1, 20);
            secondVar = GenUtils.generateRandomInRange(1, 50);
            trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
            break;
    }
    //call buildQuestionString after the randomized vars have been created
    buildQuestionString(randomizedPHY3);

    trueAnswer = Double.parseDouble(PHY3.solveMissing(randomizedPHY3, firstVar, secondVar));


}


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "After recording the velocity of a moving %s at two separate moments, you determine that the velocity has changed %s " +
                        "meters/second in %s seconds. What was the average acceleration between these moments?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "A %s moving in a straight line accelerates at a rate of %s meters/second^2 over the course of %s seconds. " +
                        "What is the change in the object's velocity throughout this time interval?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "110":
                base = "If a %s accelerates at a constant rate of %s meters/second^2 for t seconds, and if the change in its velocity " +
                        "was %s meters/second, what is t?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
        }
    }

    @Override
    public String getFormattedQuestion() {
        return formatted;
    }

    @Override
    public String getTrueAnswer() {
        return String.valueOf(trueAnswer) + " " + trueAnswerUnit;
    }

    @Override
    public String getHint() {
        //set to "null" if no hint is to be included
        return "phy3";
    }
}
