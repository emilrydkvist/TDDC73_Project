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

                @Override
                public void afterTextChanged(Editable s) {
                    checkPassword(s.toString());
                }


            });

        }

    /*
     * Function for determining the strength of a password on a scale from 1-50
     * Set passwStr to -1 if the password is too short.
     * @param string passw
     */
    private void checkPassword(String passw) {

        Pattern capitalLetter = Pattern.compile("([A-Z])");
        Pattern numerical = Pattern.compile("([0-9])");
        Pattern specialChar = Pattern.compile("([[^a-z]&&[^A-Z]&&[^0-9]])");
        Matcher match;
        int passwLen = passw.length();
        int passwStr = 0;

        //Check password length and assigns a strength score. Password must be at least 6 chars long
        if(passwLen < 6)
            passwStr = -1;
        else {
            if (passwLen >= 6 && passwLen < 12)
                passwStr += 10;
            else if (passwLen >= 12)
                passwStr += 20;


            //Check for capital letters in password
            match = capitalLetter.matcher(passw);
            while (match.find()) {
                feedbackField.setText("Password contains A-Z " + passw);
                passwStr += 10;
                break;
            }

            //Check for numerical characters in password
            match = numerical.matcher(passw);
            while (match.find()) {
                feedbackField.setText("Password contains 0-9 " + passw);
                passwStr += 10;
                break;
            }

            //Check for special characters in password
            match = specialChar.matcher(passw);
            while (match.find()) {
                feedbackField.setText("Password contains special char " + passw);
                passwStr += 10;
                break;
            }
        }
        //Call function for displaying feedback to the user.
        setPasswordStrength(passwStr);

    }

    /*
     *  Function giving feedback on the strength of the password.
     *  @param int passwordStrength
     */
    public void setPasswordStrength(int passwordStrength) {
        //Password too short
        if(passwordStrength == -1){
            feedbackField.setText("Password is too short");
            feedbackField.setTextColor(Color.parseColor("#FF0000"));
        }

        //Password is weak
        else if(passwordStrength >= 10 && passwordStrength <20){
            feedbackField.setText("Password is weak");
            feedbackField.setTextColor(Color.parseColor("#FF0000"));
        }

        //Password is Ok
        else if(passwordStrength >= 20 && passwordStrength <30){
            feedbackField.setText("Password is OK");
            feedbackField.setTextColor(Color.parseColor("#ff7f00"));
        }

        //Password is strong
        else if(passwordStrength >= 30 && passwordStrength <40){
            feedbackField.setText("Password is strong");
            feedbackField.setTextColor(Color.parseColor("#009900"));
        }

        //Password is very strong
        else if(passwordStrength >= 40){
            feedbackField.setText("Password is very strong");
            feedbackField.setTextColor(Color.parseColor("#009900"));
        }

    }
}

