package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY9;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/19/17.
 */

public class T4PHY9 implements Generator{


    //this is a tier 4 generator applying PHY9
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY9(){
        //generate a random code for the 3-var
        String randomizedPHY9 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY9) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 70);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 80);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY9);

        trueAnswer = Double.parseDouble(PHY9.solveMissing(randomizedPHY9, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "A %s accelerates from %s to %s meters/second in a certain period of time. What was the average velocity during this period?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar);
                break;
            case "101":
                base = "A %s accelerated from an initial velocity of %s meters/second to an unknown final velocity. " +
                        "If the average velocity during this period of acceleration was %s meters/second, what was the final velocity?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar);
                break;
            case "110":
                base = "A %s accelerated from an unknown initial velocity to %s meters/second. If the average velocity " +
                        "during this period of acceleration was %s, what was the initial velocity?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar);
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
        return "phy9";
    }
}
