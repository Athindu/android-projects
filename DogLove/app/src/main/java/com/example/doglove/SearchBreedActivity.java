package com.example.doglove;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SearchBreedActivity extends AppCompatActivity {

    Button submit_btn;
    Button stop_btn;

    TextView wrongBreed;
    ImageView slideshow;
    int count;
    int randNum;
    int[] slideImages;
    int dogNum;
    Boolean checkValue = true;
    Random r;
    int highValue;
    int lowValue;
    Random rangeNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_breed);

        final String breedArray[] = {"Rottweiler", "Bullmastiff", "Airedale", "Basset", "Beagle", "Boxer",
                "Collie", "Dhole", "Doberman", "Keeshond", "Blenheim Spaniel", "Golden retriever",
                "Labrador retriever", "Siberian husky", "Silky terrier"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, breedArray);

        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.actv);       //making an auto complete text view to make the task easier when searching for some breed
        textView.setThreshold(1);
        textView.setAdapter(adapter);



        wrongBreed = (TextView) findViewById(R.id.wrongBreed);
        slideshow = (ImageView) findViewById(R.id.slideshow);

        submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String wordSearch = textView.getText().toString();              //https://stackoverflow.com/questions/51360400/how-check-my-edit-text-input-value-is-equals-to-the-array-value
                for (int i = 0; i < breedArray.length; i++) {
                    if (wordSearch.equals(breedArray[i].toString())) {          //check if the typed name is in the array and work accordingly
                        System.out.println(wordSearch);
                        System.out.println("correct");
                        wrongBreed.setText("");

                        //if wordSearch equals rottweiler then given an random number from 0-10 as first 10 photos are from that breed
                        //if wordSearch equals labarodre then given an random number from 10-20 next 10 photos are from that breed
                        //likewise

                        //setting a high and low value to search between ranges accordingly
                        //and calls random num generate method to select a random image

                        if (wordSearch.equals("Rottweiler")) {
                            highValue = 10;
                            lowValue = 1;
                            getRandomNumberInRange(1,10);
                            runnable.run();

                        } else if (wordSearch.equals("Bullmastiff")) {
                            highValue =20;
                            lowValue =11;
                            getRandomNumberInRange(11,20);
                            runnable.run();

                        } else if (wordSearch.equals("Airedale")) {
                            highValue =30;
                            lowValue =21;
                            getRandomNumberInRange(21,30);
                            runnable.run();

                        } else if (wordSearch.equals("Basset")) {
                            highValue =40;
                            lowValue =31;
                            getRandomNumberInRange(31,40);
                            runnable.run();

                        } else if (wordSearch.equals("Beagle")) {
                            highValue =50;
                            lowValue =41;
                            getRandomNumberInRange(41,50);
                            runnable.run();

                        } else if (wordSearch.equals("Blenheim Spaniel")) {
                            highValue =60;
                            lowValue =51;
                            getRandomNumberInRange(51,60);
                            runnable.run();

                        } else if (wordSearch.equals("Boxer")) {
                            highValue =70;
                            lowValue =61;
                            getRandomNumberInRange(61,70);
                            runnable.run();

                        } else if (wordSearch.equals("Collie")) {
                            highValue =80;
                            lowValue =71;
                            getRandomNumberInRange(71,80);
                            runnable.run();

                        } else if (wordSearch.equals("Dhole")) {
                            highValue =90;
                            lowValue =81;
                            getRandomNumberInRange(81,90);
                            runnable.run();

                        } else if (wordSearch.equals("Doberman")) {
                            highValue =100;
                            lowValue =91;
                            getRandomNumberInRange(91,100);
                            runnable.run();

                        } else if (wordSearch.equals("Golden retriever")) {
                            highValue =110;
                            lowValue =101;
                            getRandomNumberInRange(101,110);
                            runnable.run();

                        } else if (wordSearch.equals("Keeshond")) {
                            highValue =120;
                            lowValue =111;
                            getRandomNumberInRange(111,120);
                            runnable.run();

                        } else if (wordSearch.equals("Labrador retriever")) {
                            highValue =130;
                            lowValue =121;
                            getRandomNumberInRange(121,130);
                            runnable.run();

                        } else if (wordSearch.equals("Siberian husky")) {
                            highValue =140;
                            lowValue =131;
                            getRandomNumberInRange(131,140);
                            runnable.run();

                        } else if (wordSearch.equals("Silky terrier")) {
                            highValue =150;
                            lowValue =141;
                            getRandomNumberInRange(141,150);
                            runnable.run();
                        }
                        break;
                    } else {
                        wrongBreed.setText("Wrong dog breed !!!");
                        int imgResource = getResources().getIdentifier("drawable/bsorry" , null, getPackageName());
                        slideshow.setImageResource(imgResource);
                        textView.setText("");
                    }
                }
            }
        });

        stop_btn = (Button) findViewById(R.id.stop_btn);
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Stop runnable...");
                handler.removeCallbacks(runnable);              //stopping the runnable
                textView.setText("");
            }
        });
    }


    public void getRandomNumberInRange(int min, int max){
        if (min >= max) {
            throw new IllegalArgumentException("Maximum need to be greater than minimum number...");
        }

        rangeNum = new Random();
        count =rangeNum.nextInt((max - min) + 1) + min;

    }


    final Handler handler = new Handler();
    final Runnable runnable= new Runnable() {
        public void run() {
            System.out.println(count);

            if (count<= highValue && count>= lowValue){                          //check for the range
                System.out.println(count);
                int str  = getResources().getIdentifier("drawable/" + "img_" + count, null, getPackageName());
                slideshow.setImageResource(str);
                getRandomNumberInRange(lowValue,highValue);
                if (count== highValue +1){
                    count= lowValue;                            //stopping the print if the range of the relavent breed exceeds
                }
            }
            handler.postDelayed(runnable, 5000);                //setting 5 second delay
        }
    };
//https://stackoverflow.com/questions/42379301/how-to-use-postdelayed-correctly-in-android-studio
    //https://stackoverflow.com/questions/16188398/how-to-stop-runnable-on-button-click-in-android

}
