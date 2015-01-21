package com.example.emil.tddc73_project;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        feedbackField = new TextView(context);
        feedbackField.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        feedbackField.setText("Lite lösenord osv");

        //this.setBackgroundColor(Color.parseColor("#222222"));

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
    private void checkPassword(String passw) {

        int passwLen = passw.length();
        int passwScore = 0;

        if(passwLen > 5){
            if(passwLen >= 6 && passwLen < 12)
                passwScore = passwScore+3;

            else if(passwLen >= 12)
                passwScore = passwScore+5;

            if(passw.matches(".*[a-z]") && passw.matches(".*[A-Z]")){
                passwScore = passwScore+1;
                feedbackField.setText("Password contains a-z and A-Z " +passw);
            }

            else if(passw.matches(".*[a-z]") || passw.matches(".*[A-Z]"))
                feedbackField.setText("Password contains a-z or A-Z " +passw);

            else if(passw.matches(".*[0-9]"))
                feedbackField.setText("Password contains 0-9 " +passw);
        }
        else
        {
            feedbackField.setText("Password too short");
            feedbackField.setTextColor(Color.parseColor("#FF0000"));
        }


    }
}

