package com.example.emil.tddc73_project;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albintornqvist on 18/01/15.
 *
 * Image slideshow component.
 * Add images to the component and specify number of images to show.
 */
public class ImgSlideshow extends LinearLayout{

    private List<Integer> ImgData;
    private List<ImageView> imgArray;
    private int counter = 0;
    private int numberToShow = 1;
    private LinearLayout imgLayout;
    Context mContext;


    public ImgSlideshow(Context context) {
        super(context);
        mContext = context;

        //Basic settings for the linearlayout
        this.setOrientation(VERTICAL);
        //this.setBackgroundColor(Color.parseColor("#ffffff"));
        //

        //set image layout properties and imagedata variable
        ImgData = new ArrayList<Integer>();
        imgArray = new ArrayList<ImageView>();

        imgLayout = new LinearLayout(context);
        imgLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imgParams.weight = 1;
        imgLayout.setLayoutParams(imgParams);
        //

        //LinearLayout for arrow buttons
        LinearLayout buttonArray = new LinearLayout(context);
        buttonArray.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        btnParams.weight = 1;
        buttonArray.setLayoutParams(btnParams);
        //

        ////Arrow buttons declaration with onclick methods
        //Left arrow
        ImageButton leftBtn = new ImageButton(context);
        leftBtn.setImageResource(R.drawable.arrow_left);
        leftBtn.setLayoutParams(new LayoutParams(200, 250));

        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter <= 0)
                    counter = (ImgData.size()-1);
                else
                    counter--;

                updateImages();
            }
        });

        //Right arrow
        ImageButton rightBtn = new ImageButton(context);
        rightBtn.setImageResource(R.drawable.arrow_right);
        rightBtn.setLayoutParams(new LayoutParams(200, 250));

        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter >= (ImgData.size()-1))
                    counter = 0;
                else
                    counter++;

                updateImages();
            }
        });
        ////

        //Add buttons to the view
        buttonArray.addView(leftBtn);
        buttonArray.addView(rightBtn);

        createImages();

        this.addView(imgLayout);
        this.addView(buttonArray);
        //
    }

    /**
     * addImage
     *
     * Function for adding images to image slider
     * @param resource
     * Takes an image resource as a parameter
     */
    public void addImage(int resource)
    {
        ImgData.add(resource);
        updateImages();
    }

    /**
     * updateImages()
     *
     * Private function that resets the shown images in the imgArray
     * according to 'counter's current value.
     */
    private void updateImages()
    {
        if(ImgData.size()>0 && ImgData.size()>=imgArray.size() && imgArray.size()>0)
        {
            int imgCounter = counter;

            for(int i=0; i<imgArray.size(); i++, imgCounter++)
            {
                if(imgCounter > (ImgData.size()-1))
                    imgCounter = 0;

                imgArray.get(i).setImageResource(ImgData.get(imgCounter));

            }
        }
    }

    /**
     * createImages()
     *
     * Private function used to reset imgArray and
     * add a number of imgLayouts according to the local variable 'numberToShow'
     */
    private void createImages()
    {
        imgArray.clear();
        imgLayout.removeAllViewsInLayout();

        for(int i=0; i<numberToShow; i++)
        {
            imgArray.add(new ImageView(mContext));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            params.setMargins(10,0,10,0);
            imgArray.get(i).setLayoutParams(params);
            imgLayout.addView(imgArray.get(i));
        }
    }

    /**
     * setNumberToShow
     * @param nr
     *
     * Set function to choose the number of images to
     * show at once on the image slide show.
     */
    public void setNumberToShow(int nr)
    {
        if(nr <= 0)
            numberToShow = 1;
        else if(nr >= ImgData.size())
            numberToShow = ImgData.size();
        else
            numberToShow = nr;

        createImages();
        updateImages();
    }
}
