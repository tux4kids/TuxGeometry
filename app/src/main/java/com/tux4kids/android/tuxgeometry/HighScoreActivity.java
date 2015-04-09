package com.tux4kids.android.tuxgeometry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jason Barker on 3/16/2015.
 * This activity will display a list of high scores for all games
 */
public class HighScoreActivity extends Activity {

    private static final String TAG = "HighScoreActivity: ";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.high_score_choice);

        Button areaGame = (Button) findViewById(R.id.areaHighScoreButton);

        areaGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAreaScores(AreaHighScores.class);
            }
        });

        Button matchingGame = (Button) findViewById(R.id.mathingHighScoreButton);

        matchingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAreaScores(MatchingHighScores.class);
            }
        });


    }

    private void showAreaScores(Class startActivity) {

        Intent i = new Intent(this, startActivity);
        startActivity(i);
    }
}
