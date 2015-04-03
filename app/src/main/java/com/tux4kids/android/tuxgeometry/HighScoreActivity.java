package com.tux4kids.android.tuxgeometry;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jason Barker on 3/16/2015.
 * This activity will display a list of high scores for all games
 */
public class HighScoreActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.high_score_fragment);
    }
}
