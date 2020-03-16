package com.example.doglove;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;


public class IdentifyBreedActivity extends AppCompatActivity {

    Random random;
    Spinner mySpinner;
    public int randNum;
    String dogBr;
    TextView quizStatus;
    Button buttonSubmit;
    TextView correctBreed;
    String timeB;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);


        time=(TextView)findViewById(R.id.time);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        correctBreed = (TextView) findViewById(R.id.correctBreed);
        quizStatus = (TextView) findViewById(R.id.quizStatus);


        if (getIntent().getExtras() != null) {
            timeB = getIntent().getStringExtra("timer");     //check if any value is obtaine from main activity
        }
        if(timeB.equals("true")){
            timeCal(10,time);               //calling the method to create the countdown
        }

        setImageView();
        buttonSubmit.setText("Submit");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(IdentifyBreedActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.breeds));    //accessing the string array created to make the spinner
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

    }


    public void setImageView(){

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        int []images_id = new int[150];      //inserting all the images to the array using for loop
        for(int i=1; i<150; i++){
            images_id[i] = getResources().getIdentifier("drawable/"+"img_"+i, null, getPackageName());
        }

        random=new Random();
        randNum = random.nextInt(150);
        imageView.setImageResource(images_id[randNum]);     //creating a random no according to the size of the array

    }


    public void submitBreed(View view) {
        submitBreed();
    }


    public void submitBreed(){                             //creating another method so it can be accessed in timer so can act as a submit button
        if (buttonSubmit.getText().equals("Submit")) {         //check the text on the button

            String selected = mySpinner.getSelectedItem().toString();   //take the selected string from the spinner


            if (randNum >= 1 && randNum <= 10) {                    //check for the random generated number and assigning it to a variable so it can be compared with the selected breed from spinner
                dogBr = "Rottweiler";
            } else if (randNum >= 11 && randNum <= 20) {
                dogBr = "Bullmastiff";
            } else if (randNum >= 21 && randNum <= 30) {
                dogBr = "Airedale";
            } else if (randNum >= 31 && randNum <= 40) {
                dogBr = "Basset";
            } else if (randNum >= 41 && randNum <= 50) {
                dogBr = "Beagle";
            } else if (randNum >= 51 && randNum <= 60) {
                dogBr = "Blenheim Spaniel";
            } else if (randNum >= 61 && randNum <= 70) {
                dogBr = "Boxer";
            } else if (randNum >= 71 && randNum <= 80) {
                dogBr = "Collie";
            } else if (randNum >= 81 && randNum <= 90) {
                dogBr = "Dhole";
            } else if (randNum >= 91 && randNum <= 100) {
                dogBr = "Doberman";
            } else if (randNum >= 101 && randNum <= 110) {
                dogBr = "Golden retriever";
            } else if (randNum >= 111 && randNum <= 120) {
                dogBr = "Keeshond";
            } else if (randNum >= 121 && randNum <= 130) {
                dogBr = "Labrador retriever";
            } else if (randNum >= 131 && randNum <= 140) {
                dogBr = "Siberian husky";
            } else if (randNum >= 141 && randNum <= 150) {
                dogBr = "Silky terrier";
            }


            if (dogBr.equals(selected)) {               //checking whether the selected and the random num generated values are equal or not and setting the text and color accordingly
                quizStatus.setText("Correct");
                quizStatus.setTextColor(Color.parseColor("#00802b"));       //https://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code

            } else {
                quizStatus.setText("Wrong");
                quizStatus.setTextColor(Color.parseColor("#e60000"));

                correctBreed.setText("Dog's breed is " + dogBr);
                correctBreed.setTextColor(Color.parseColor("#0000ff"));

            }

            buttonSubmit.setText("Next");               //change the value of the text as things which need to be done from submit text is over
        }
        else {
            correctBreed.setText("");
            quizStatus.setText("");
            buttonSubmit.setText("Submit");             //change the button text again to submit as new image should be set on the image view and hence called the setImageView method
            setImageView();

        }

    }

    public void timeCal(int Seconds,final TextView textView){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                textView.setText(""+(seconds-1));
            }

            public void onFinish() {
                submitBreed();

            }
        }.start();
    }

}
