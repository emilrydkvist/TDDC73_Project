package com.example.emil.tddc73_project;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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

    private List<Integer> imgData;      //List of image resources
    private List<ImageView> imgArray;   //List of ImageViews
    private int counter = 0;
    private int numberToShow = 1;
    private LinearLayout imgLayout;
    Context mContext;
    private List<SlideshowActionListener> listenerCollection;


    public ImgSlideshow(Context context) {
        super(context);
        mContext = context;

        //new instance of listenercollection
        listenerCollection = new ArrayList<SlideshowActionListener>();

        //Basic settings for the linearlayout
        this.setOrientation(VERTICAL);
        this.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        //this.setBackgroundColor(Color.parseColor("#ffffff"));
        //

        //set image layout properties and imagedata variable
        imgData = new ArrayList<Integer>();
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
        leftBtn.setLayoutParams(new LayoutParams(170, 220));

        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter <= 0)
                    counter = (imgData.size()-1);
                else
                    counter--;

                updateImages();
                clickEvent e = new clickEvent();
                e.leftClick = true;
                notifyListeners(e);
            }
        });

        //Right arrow
        ImageButton rightBtn = new ImageButton(context);
        rightBtn.setImageResource(R.drawable.arrow_right);
        rightBtn.setLayoutParams(new LayoutParams(170, 220));

        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter >= (imgData.size()-1))
                    counter = 0;
                else
                    counter++;

                updateImages();
                clickEvent e = new clickEvent();
                e.rightClick = true;
                notifyListeners(e);
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


    /*
    * notifyListeners
    *
    * Notify all listeners in listenerCollection and
    * call onClick function send clickEvent as parameter
     */
    private void notifyListeners(clickEvent e){

        for(int i=0; i<listenerCollection.size(); i++)
            listenerCollection.get(i).onClick(e);

    }

    /*
    * addActionListener
    *
    * Add new listener to the listenerCollection
     */
    public void addActionListener(SlideshowActionListener listener){
        listenerCollection.add(listener);
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
        imgData.add(resource);
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
        if(imgData.size()>0 && imgData.size()>=imgArray.size() && imgArray.size()>0)
        {
            int imgCounter = counter;

            //Loop through each image view and set appropriate image
            for(int i=0; i<imgArray.size(); i++, imgCounter++)
            {
                if(imgCounter > (imgData.size()-1))
                    imgCounter = 0;

                imgArray.get(i).setImageResource(imgData.get(imgCounter));

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
     * setImageSize
     * @param size
     *size in pixels.
     *
     * Set the size for the images in the image slider.
     */
    public void setImageSize(int size){

        for(int i=0; i<imgArray.size(); i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.weight = 1;
            params.setMargins(10,0,10,0);
            imgArray.get(i).setLayoutParams(params);
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
        else if(nr >= imgData.size())
            numberToShow = imgData.size();
        else
            numberToShow = nr;

        createImages();
        updateImages();
    }
}
