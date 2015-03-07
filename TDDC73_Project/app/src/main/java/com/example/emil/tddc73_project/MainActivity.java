package com.example.emil.tddc73_project;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {


    ImgSlideshow slideShow;
    PasswordStrengthMeter passwordStrMeter;
    LinearLayout theLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theLayout = new LinearLayout(this);
        theLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        theLayout.setOrientation(LinearLayout.VERTICAL);

        slideShow = new ImgSlideshow(this);
        slideShow.addImage(R.drawable.arrow_right);
        slideShow.addImage(R.drawable.ic_launcher);
        slideShow.addImage(R.drawable.arrow_left);
        slideShow.addImage(R.drawable.blacknwhiteperlin);
        slideShow.addImage(R.drawable.dogealbin);
        slideShow.setNumberToShow(3);
        slideShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000));

        //setContentView(slideShow);


        passwordStrMeter = new PasswordStrengthMeter(this);
        //setContentView(passwordStrMeter);
        theLayout.addView(passwordStrMeter);
        theLayout.addView(slideShow);

        setContentView(theLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
