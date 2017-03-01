package com.wordpress.httpspandareaktor.mekanism;

import android.text.Spanned;

/**
 * Created by Brian on 12/10/2016.
 */

public class Equation {

    //create a variable to store the string describing parameters needed to solve equation
    private String mParams;
    private Spanned mFormattedParams;
    private String mVarcount;
    private String mDesc;
    private Class mSolver;
    private String mCode;
    private int mImageResourceId;
    public boolean specialFormatting;

    public Equation(Class solver, String code, int ImageResourceId, String varCount, String desc, String params) {
        mDesc = desc;
        mVarcount = varCount;
        mParams = params;
        mSolver = solver;
        mCode = code;
        mImageResourceId = ImageResourceId;
        specialFormatting = false;

    }

    public Equation(Class solver, String code, int ImageResourceId, String varCount, String desc, Spanned formattedParams) {
        mDesc = desc;
        mVarcount = varCount;
        mFormattedParams = formattedParams;
        mSolver = solver;
        mCode = code;
        mImageResourceId = ImageResourceId;
        specialFormatting = true;
    }

    public String getParams() {
        //getter method for params
        return mParams;
    }

    public Spanned getFormattedParams() {
        //getter method for formatted parameters
        return mFormattedParams;
    }

    public String getVarcount() {
        //get the variable count
        return mVarcount;
    }

    public String getDesc() {
        //getter method for subject
        return mDesc;
    }

    public Class getSolver() {
        //getter method for solver, which is the class that takes inputs for equation
        return mSolver;
    }

    public String getCode() {
        //getter method for the code, which allows solver to find equation info
        return mCode;
    }

    public int getImageResourceId() {
        //getter method for the image resource ID for the equation
        return mImageResourceId;
    }
}
