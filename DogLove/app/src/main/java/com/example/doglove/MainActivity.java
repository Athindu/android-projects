package com.example.doglove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Button button;
    String check;
    Switch switchTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchTime=(Switch)findViewById(R.id.timer);
    }


    public void breedClick(View view) {
        Intent intent = new Intent(this,IdentifyBreedActivity.class);
            //https://abhiandroid.com/ui/switch
            //https://stackoverflow.com/questions/41361626/how-to-pass-a-boolean-value-between-activities-with-intents - tried but didn't work

        if(switchTime.isChecked())                //check if the switch is on or off
            check = "true";
        else
            check = "false";
            intent.putExtra("timer", check);    //passing the value to next activity
            startActivity(intent);
    }

    public void dogClick(View view) {
        Intent intent = new Intent(this, IdentifyDogActivity.class);
        if(switchTime.isChecked())                  //check if the switch is on or off
            check = "true";
        else
            check = "false";
            intent.putExtra("timer", check);        //passing the value to next activity
            startActivity(intent);
    }

    public void searchClick(View view) {
        Intent intent = new Intent(this,SearchBreedActivity.class);
        startActivity(intent);
    }



    //https://www.youtube.com/watch?v=nlPtfncjOWA&t=405s - button styles
    //https://www.youtube.com/watch?v=A9rcKZUm0zM - action bar remove
    //https://www.youtube.com/watch?v=GnGIcxuuwbM
}
