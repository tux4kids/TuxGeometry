package com.tux4kids.android.tuxgeometry;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by Jason Barker on 4/9/2015.
 */
public class AreaHighScores extends Activity {

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
        numScores = sharedPref.getInt("numAreaScores", numScores);

        long tempScore = 0;
        String tempName = "";

        if (numScores >= 1) {
            tempScore = sharedPref.getLong("areaHighScore1", tempScore);
            tempName = sharedPref.getString("areaName1", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore1.setText("1. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 2) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore2", tempScore);
            tempName = sharedPref.getString("areaName2", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore2.setText("2. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 3) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore3", tempScore);
            tempName = sharedPref.getString("areaName3", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore3.setText("3. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 4) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore4", tempScore);
            tempName = sharedPref.getString("areaName4", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore4.setText("4. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 5) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore5", tempScore);
            tempName = sharedPref.getString("areaName5", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore5.setText("5. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 6) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore6", tempScore);
            tempName = sharedPref.getString("areaName6", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore6.setText("6. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 7) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore7", tempScore);
            tempName = sharedPref.getString("areaName7", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore7.setText("7. " + tempName + " " + tempScore + " seconds!");        }
        if (numScores >= 8) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore8", tempScore);
            tempName = sharedPref.getString("areaName8", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore8.setText("8. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 9) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore9", tempScore);
            tempName = sharedPref.getString("areaName9", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore9.setText("9. " + tempName + " " + tempScore + " seconds!");
        }
        if (numScores >= 10) {
            tempName = "";
            tempScore = sharedPref.getLong("areaHighScore10", tempScore);
            tempName = sharedPref.getString("areaName10", tempName);
            if(tempName.equals(""))
                tempName = "Anonymous";
            highScore10.setText("10. " + tempName + " " + tempScore + " seconds!");
        }
    }
}
