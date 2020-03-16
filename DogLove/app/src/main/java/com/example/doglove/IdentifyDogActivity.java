package com.example.doglove;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;


public class IdentifyDogActivity extends AppCompatActivity {

    TextView selectedBreed;
    TextView answer;
    Switch timer;
    TextView time;
    ImageView dogImg1;
    ImageView dogImg2;
    ImageView dogImg3;
    int imagNum;
    int check;
    int breedNum;
    String timeB;

    private String breedArray[] = {"Rottweiler", "Bullmastiff", "Airedale", "Basset", "Beagle", "Boxer",
            "Collie", "Dhole", "Doberman", "Keeshond", "Blenheim Spaniel", "Golden retriever",
            "Labrador retriever", "Siberian husky", "Silky terrier"};               //creating an array with the breeds used in the app


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);
        time = (TextView) findViewById(R.id.time2);
        timer=findViewById(R.id.timer);

        if (getIntent().getExtras() != null) {                          //check if any value is obtaine from main activity
            timeB = getIntent().getStringExtra("timer");
        }
        if(timeB.equals("true")){
            timeCal(10,time);                   ////calling the method to create the countdown
        }



        selectedBreed = (TextView) findViewById(R.id.selectedBreed);
        answer = (TextView) findViewById(R.id.answer);
        dogImg1 =(ImageView) findViewById(R.id.dogImg1);
        dogImg2 =(ImageView) findViewById(R.id.dogImg2);
        dogImg3 =(ImageView) findViewById(R.id.dogImg3);
        TextView timer=findViewById(R.id.time2);

        getRandomNumberInRange(0,14);           //calling the method to generate a number between 0 and 14 as 15 breeds are used in the app

    }


    private void getRandomNumberInRange(int min, int max) {
        if (min >= max) {                                                           //method to generate a random number in a range regarding the breed from array
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random randNum = new Random();
        breedNum = randNum.nextInt((max - min) + 1) + min;

        System.out.println(breedNum);
        System.out.println(breedArray[breedNum]);
        selectRange();

    }


    private void selectRange(){

            getRandomNumberInRange2(1,50);              //setting 1-50 images to the first imageview by generating a random num and obtaining and setting and image relevant to that number
            System.out.println(imagNum);
            int str1  = getResources().getIdentifier("drawable/" + "img_" + imagNum, null, getPackageName());
            dogImg1.setImageResource(str1);


            getRandomNumberInRange2(51,100);               //setting 51-100 images to the first imageview by generating a random num and obtaining and setting and image relevant to that number
            System.out.println(imagNum);
            if (imagNum>=51 && imagNum<=60){
                selectedBreed.setText("Blenheim Spaniel");
            }
            else if (imagNum>=61 && imagNum<=70){
                selectedBreed.setText("Boxer");
            }                                                     //setting the texteview text according to the breed on the 2nd imageview
            else if (imagNum>=71 && imagNum<=80){
                selectedBreed.setText("Collie");
            }
            else if (imagNum>=81 && imagNum<=90){
                selectedBreed.setText("Dhole");
            }
            else if (imagNum>=91 && imagNum<=100){
                selectedBreed.setText("Doberman");
            }
            int str2  = getResources().getIdentifier("drawable/" + "img_" + imagNum, null, getPackageName());
            dogImg2.setImageResource(str2);



            getRandomNumberInRange2(101,150);       //setting 101-150 images to the first imageview by generating a random num and obtaining and setting and image relevant to that number
            System.out.println(imagNum);
            int str3  = getResources().getIdentifier("drawable/" + "img_" + imagNum, null, getPackageName());
            dogImg3.setImageResource(str3);


    }


    private void getRandomNumberInRange2(int min, int max) {                //random number generate method to set a image between a range of numbers
        if (min >= max) {
            throw new IllegalArgumentException("Maximum must be greater than minimum");
        }
        Random randNum = new Random();
        imagNum = randNum.nextInt((max - min) + 1) + min;

    }


    public void image1Click(View view) {                                        //giving the answer according to the picture selected
        answer.setText("Wrong");
        answer.setTextColor(Color.parseColor("#e60000"));
        dogImg2.setClickable(false);                                    //turn off the clickable nature of the not selected photo as only one image should be clickable
        dogImg3.setClickable(false);
    }

    public void image2Click(View view) {
        answer.setText("Correct");
        answer.setTextColor(Color.parseColor("#00802b"));
        dogImg1.setClickable(false);
        dogImg3.setClickable(false);                            //https://stackoverflow.com/questions/4384890/how-to-disable-an-android-button
    }

    public void image3Click(View view) {
        answer.setText("Wrong");
        answer.setTextColor(Color.parseColor("#e60000"));
        dogImg1.setClickable(false);
        dogImg2.setClickable(false);
    }

    public void timeCal(int Seconds,final TextView textView){           //https://stackoverflow.com/questions/10032003/how-to-make-a-countdown-timer-in-android
                                                                        //https://developer.android.com/reference/android/os/CountDownTimer.html
                                                                        //https://stackoverflow.com/questions/35708453/how-to-automatically-click-a-button-in-android-after-a-5-second-delay
        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                textView.setText(""+(seconds-1));
            }

            public void onFinish() {
                nextDog();
            }
        }.start();
    }

    //next images after button click
    public void nextImg(View view) {

        nextDog();
    }

    public void nextDog(){              //creating another method so it can be accessed in timer so can act as a next button
        answer.setText("");
        getRandomNumberInRange(0,14);
        dogImg1.setClickable(true);
        dogImg2.setClickable(true);
        dogImg3.setClickable(true);
    }
}
