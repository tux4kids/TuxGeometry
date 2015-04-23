package com.tux4kids.android.tuxgeometry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jason Barker on 3/22/2015.
 * Tux image created by Larry Ewing
 */
public class WelcomeActivity extends Activity {


    private static final String TAG = "WelcomeFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_welcome);

        //the buttons on the welcome screen
        Button matchingGameButton;
        Button highScoreButton;
        Button areaGameButton;

        //get the number of high scores for the Area Game and Matching Game
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int numAreaScores = 0;
        int numMatchingScores = 0;
        long temp = 0;
        String areaHighScore = "areaHighScore";
        String matchingHighScore = "matchingHighScore";
        for(int i = 1; i <= 10; i++)
        {
            areaHighScore += i;
            if(sharedPref.getLong(areaHighScore, temp) > 0)
                numAreaScores++;

            areaHighScore = "areaHighScore";

        }

        editor.putInt("numAreaScores", numAreaScores);
        editor.commit();

        //get the number of high scores for the Matching Game


        //what happens when the matching game button is clicked in fragment_welcome.xml
        matchingGameButton = (Button) findViewById(R.id.matching_button);
        matchingGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(MatchingGameActivity.class);

            }
        });

        //what happens when the highScoreGameButton is clicked in fragment_welcome.xml
        highScoreButton = (Button) findViewById(R.id.high_score_button);
        highScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessage(HighScoreActivity.class);

            }
        });

        //what happens when the areaGameButton is clicked in fragment_welcome.xml
        areaGameButton = (Button) findViewById(R.id.area_button);
        areaGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessage(AreaGameActivity.class);

            }
        });

    }

    //switch activities to the class given as an input
    protected void sendMessage(Class mClass){
        Intent i = new Intent(this, mClass);
        startActivity(i);

    }
}
