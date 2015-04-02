package com.example.emil.tddc73_project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Emil on 2015-04-02.
 */
public class Algorithm implements  AlgorithmInterface {

    // Empty constructor
    public Algorithm() {

    }

    @Override
    public int passwordStrength(String password) {

        //Patterns
        Pattern capitalLetter = Pattern.compile("([A-Z])");
        Pattern numerical = Pattern.compile("([0-9])");
        Pattern specialChar = Pattern.compile("([[^a-z]&&[^A-Z]&&[^0-9]])");
        Matcher match;
        int passwordLen = password.length();
        int passwordStr = 0;

        //Check password length and assigns a strength score. Password must be at least 6 chars long
        if(passwordLen < 6)
            passwordStr = -1;
        else {
            if (passwordLen >= 6 && passwordLen < 12)
                passwordStr += 2;
            else if (passwordLen >= 12)
                passwordStr += 4;

            //Check for capital letters in password
            match = capitalLetter.matcher(password);
            while (match.find()) {
                //feedbackField.setText("Password contains A-Z " + passw);
                passwordStr += 2;
                break;
            }

            //Check for numerical characters in password
            match = numerical.matcher(password);
            while (match.find()) {
                //feedbackField.setText("Password contains 0-9 " + passw);
                passwordStr += 2;
                break;
            }

            //Check for special characters in password
            match = specialChar.matcher(password);
            while (match.find()) {
                //feedbackField.setText("Password contains special char " + passw);
                passwordStr += 2;
                break;
            }
        }

        return passwordStr;
    }
}
