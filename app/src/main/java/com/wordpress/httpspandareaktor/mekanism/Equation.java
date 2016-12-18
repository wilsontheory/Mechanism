package com.wordpress.httpspandareaktor.mekanism;

/**
 * Created by Brian on 12/10/2016.
 */

public class Equation {

    //create a variable to store the string describing parameters needed to solve equation
    private String mParams;
    private String mVarcount;
    private String mSubject;
    private Class mSolver;
    private int mImageResourceId;

    public Equation(Class solver, int ImageResourceId, String varCount, String subject, String params) {
        mSubject = subject;
        mVarcount = varCount;
        mParams = params;
        mSolver = solver;
        mImageResourceId = ImageResourceId;

    }

    public String getParams() {
        //getter method for params
        return mParams;
    }

    public String getVarcount() {
        //get the variable count
        return mVarcount;
    }

    public String getSubject() {
        //getter method for subject
        return mSubject;
    }

    public Class getSolver() {
        //getter method for solver, which is the class that takes inputs for equation
        return mSolver;
    }

    public int getImageResourceId() {
        //getter method for the image resource ID for the equation
        return mImageResourceId;
    }
}
