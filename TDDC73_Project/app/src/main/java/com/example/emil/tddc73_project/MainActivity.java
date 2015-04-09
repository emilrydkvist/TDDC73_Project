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

        //creating a new image slideshow
        slideShow = new ImgSlideshow(this);
        slideShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 900));

        //adding images to image slideshow
        slideShow.addImage(R.drawable.teamliquid);
        slideShow.addImage(R.drawable.ic_launcher);
        slideShow.addImage(R.drawable.liu);
        slideShow.addImage(R.drawable.dota);
        slideShow.addImage(R.drawable.twitch);
        slideShow.addImage(R.drawable.studio);

        //setting number of images to show simultaneously
        slideShow.setNumberToShow(4);

        //Set the size of the images
        slideShow.setImageSize(200);

        //add actionlistener to image slide show
        slideShow.addActionListener(new SlideshowActionListener() {
            @Override
            public void onClick(clickEvent e) {
                if(e.leftClick){
                    System.out.println("nu har du klickat vänster");
                }
                else if(e.rightClick){
                    System.out.println("nu har du klickat höger");
                }
            }
        });

        //creating new password strength meter
        passwordStrMeter = new PasswordStrengthMeter(this);

        //Set functions for password strength meter
        passwordStrMeter.setPasswordShort("Alldeles för kort!");
        passwordStrMeter.setHexColorShort("#0000FF");
        passwordStrMeter.setHexColorVeryStrong("#FF00FF");

        //add components to layout
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
