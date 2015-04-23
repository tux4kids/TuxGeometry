package com.tux4kids.android.tuxgeometry;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Jason Barker on 4/9/2015.
 */
public class MatchingHighScores extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.high_score_fragment);

        TextView highScore1, highScore2, highScore3, highScore4, highScore5, highScore6,
                highScore7, highScore8, highScore9, highScore10;

        highScore1 = (TextView) findViewById(R.id.highScore1);
        highScore2 = (TextView) findViewById(R.id.highScore2);
        highScore3 = (TextView) findViewById(R.id.highScore3);
        highScore4 = (TextView) findViewById(R.id.highScore4);
        highScore5 = (TextView) findViewById(R.id.highScore5);
        highScore6 = (TextView) findViewById(R.id.highScore6);
        highScore7 = (TextView) findViewById(R.id.highScore7);
        highScore8 = (TextView) findViewById(R.id.highScore8);
        highScore9 = (TextView) findViewById(R.id.highScore9);
        highScore10 = (TextView) findViewById(R.id.highScore10);

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        int numScores = 0;
        numScores = sharedPref.getInt("numMatchingScores", numScores);

        long tempScore = 0;
        String tempName = "";

        if (numScores >= 1) {
            tempScore = sharedPref.getLong("matchingHighScore1", tempScore);
            tempName = sharedPref.getString("matchingName1", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore1.setText("1.  " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 2) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore2", tempScore);
            tempName = sharedPref.getString("matchingName2", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore2.setText("2. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 3) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore3", tempScore);
            tempName = sharedPref.getString("matchingName3", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore3.setText("3. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 4) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore4", tempScore);
            tempName = sharedPref.getString("matchingName4", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore4.setText("4. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 5) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore5", tempScore);
            tempName = sharedPref.getString("matchingName5", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore5.setText("5. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 6) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore6", tempScore);
            tempName = sharedPref.getString("matchingName6", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore6.setText("6. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 7) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore7", tempScore);
            tempName = sharedPref.getString("matchingName7", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore7.setText("7. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 8) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore8", tempScore);
            tempName = sharedPref.getString("matchingName8", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore8.setText("8. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 9) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore9", tempScore);
            tempName = sharedPref.getString("matchingName9", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore9.setText("9. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 10) {
            tempName = "";
            tempScore = sharedPref.getLong("matchingHighScore10", tempScore);
            tempName = sharedPref.getString("matchingName10", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore10.setText("10. " + tempName + " " + tempScore + " seconds!");
        }
    }
}

