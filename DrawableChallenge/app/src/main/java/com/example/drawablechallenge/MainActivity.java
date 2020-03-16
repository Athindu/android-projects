package com.example.drawablechallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView v1;
    Button button;
    Boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1 = findViewById(R.id.view);
        button = (Button) findViewById(R.id.button);
    }

    public void clickImg(View view) {
            if(flag==false)
            {
                v1.setImageResource(R.drawable.welsh_springer_spaniel_08203);

                flag=true;
            }
            else
            {
                v1.setImageResource(R.drawable.brittany_02625);
                flag=false;
            }
    }
}
