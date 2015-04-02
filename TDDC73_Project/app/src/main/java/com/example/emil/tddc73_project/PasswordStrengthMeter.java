package com.example.emil.tddc73_project;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Emil on 2015-01-16.
 */
public class PasswordStrengthMeter extends LinearLayout {

    EditText passwordField;
    TextView feedbackField;

    // Feedback messages describing the strength of the password
    String passwordShort = "Password is too short";
    String passwordWeak = "Password is weak";
    String passwordOk = "Password is OK";
    String passwordStrong = "Password is strong";
    String passwordVeryStrong = "Password is very strong";

    // Colors of the feedback messages. Must be hex color as a string
    String passwordColorShort = "#FF0000";
    String passwordColorWeak = "#FF0000";
    String passwordColorOk = "#ff7f00";
    String passwordColorStrong = "#009900";
    String passwordColorVeryStrong = "#009900";


    AlgorithmInterface pwAlgorithm;
    int passwordStr = 0;

    public PasswordStrengthMeter(Context context) {
        super(context);

        this.setOrientation(VERTICAL);
        this.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        passwordField = new EditText(context);
        passwordField.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        passwordField.setGravity(Gravity.CENTER);
        //Set to password type
        passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        feedbackField = new TextView(context);
        feedbackField.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        feedbackField.setText("Password Strength Meter");
        feedbackField.setGravity(Gravity.CENTER);

        this.addView(passwordField);
        this.addView(feedbackField);


        passwordField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }


                // Check the strength of the password after it is changed
                @Override
                public void afterTextChanged(Editable s) {
                    checkPassword(s.toString());
                }


            });

        }

    /*
     * Function for determining the strength of a password on a scale from 1-10
     * Set passwordStr to -1 if the password is too short.
     * @param string password
     */
    private void checkPassword(String password) {

        pwAlgorithm = new Algorithm();
        passwordStr = pwAlgorithm.passwordStrength(password);

        //Call function for displaying feedback to the user.
        setPasswordFeedback(passwordStr);
    }

    /*
     *  Set functions for feedback messages.
     */
    public void setPasswordShort(String text)
    {
        passwordShort = text;
    }
    public void setPasswordWeak(String text)
    {
        passwordWeak = text;
    }
    public void setPasswordOk(String text)
    {
        passwordOk = text;
    }
    public void setPasswordStrong(String text)
    {
        passwordStrong = text;
    }
    public void setPasswordVeryStrong(String text)
    {
        passwordVeryStrong = text;
    }


    /*
    *  Set functions for color of feedback messages.
    *  Input needs to be a hex in string
    */
    public void setHexColorShort(String color)
    {
        passwordColorShort = color;
    }
    public void setHexColorWeak(String color)
    {
        passwordColorWeak = color;
    }
    public void setHexColorOk(String color)
    {
        passwordColorOk = color;
    }
    public void setHexColorStrong(String color)
    {
        passwordColorStrong = color;
    }
    public void setHexColorVeryStrong(String color)
    {
        passwordColorVeryStrong = color;
    }


    /*
     *  Function giving feedback on the strength of the password.
     *  @param int passwordStrength
     */
    public void setPasswordFeedback(int passwordStrength) {
        //Password too short
        if(passwordStrength == -1){
            feedbackField.setText(passwordShort);
            feedbackField.setTextColor(Color.parseColor(passwordColorShort));
        }

        //Password is weak
        else if(passwordStrength >= 2 && passwordStrength <4){
            feedbackField.setText(passwordWeak);
            feedbackField.setTextColor(Color.parseColor(passwordColorWeak));
        }

        //Password is Ok
        else if(passwordStrength >= 4 && passwordStrength <6){
            feedbackField.setText(passwordOk);
            feedbackField.setTextColor(Color.parseColor(passwordColorOk));
        }

        //Password is strong
        else if(passwordStrength >= 6 && passwordStrength <8){
            feedbackField.setText(passwordStrong);
            feedbackField.setTextColor(Color.parseColor(passwordColorShort));
        }

        //Password is very strong
        else if(passwordStrength >= 8){
            feedbackField.setText(passwordVeryStrong);
            feedbackField.setTextColor(Color.parseColor(passwordColorVeryStrong));
        }

    }
}

