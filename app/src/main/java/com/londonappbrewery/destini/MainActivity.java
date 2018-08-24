package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView storyView;
    Button topButton;
    Button botButton;
    Integer curStoryResID = R.string.T1_Story;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:


        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:




        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:


    }

    private void initial() {

        storyView = findViewById(R.id.storyTextView);
        topButton = findViewById(R.id.buttonTop);
        botButton = findViewById(R.id.buttonBottom);

        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("StoryGame", "Top button is pushed!");
                updateText(view);
            }
        });

        botButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("StoryGame", "Bottom button is pushed!");
                updateText(view);
            }
        });

    }

    private void updateText(View v){
        int id = v.getId();
        int topButtonResID = R.string.T1_Ans1;
        int botButtonResID = R.string.T1_Ans2;
        switch(curStoryResID){
            case R.string.T1_Story:
                Log.d("Destiny", "Now is story T1, curStoryResID is: " + getResources().getString(curStoryResID));
                if(id == R.id.buttonTop){
                    topButtonResID = R.string.T3_Ans1;
                    botButtonResID  = R.string.T3_Ans2;
                    curStoryResID = R.string.T3_Story;
                } else {
                    topButtonResID = R.string.T2_Ans1;
                    botButtonResID = R.string.T2_Ans2;
                    curStoryResID = R.string.T2_Story;}
                break;
            case R.string.T2_Story:
                if(id == R.id.buttonTop){
                    topButtonResID = R.string.T3_Ans1;
                    botButtonResID  = R.string.T3_Ans2;
                    curStoryResID = R.string.T3_Story;
                } else endStory(R.string.T4_End);
                break;
            default:
                if(id == R.id.buttonTop) endStory(R.string.T6_End);
                else endStory(R.string.T5_End);
        }
        Log.d("Destiny", "Now is before setting new storyView, curStoryResID is: " + getResources().getString(curStoryResID));
        storyView.setText(curStoryResID);
        topButton.setText(topButtonResID);
        botButton.setText(botButtonResID);
    }

    private void endStory(int resID) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.The_End).setMessage(resID).setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
    }
}
