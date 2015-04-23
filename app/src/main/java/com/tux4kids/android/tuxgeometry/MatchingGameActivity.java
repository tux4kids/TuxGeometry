package com.tux4kids.android.tuxgeometry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

/**
 * Created by Jason Barker on 3/21/2015.
 * This activity randomly generates shapes and has users match the names to the shapes.
 */
public class MatchingGameActivity extends Activity {

    private static final String TAG = "MatchingGameActivity";

    //variables for drag and drop
    private String shape1, shape2, shape3;  //these strings will represent the name of each shape
    ImageView image1, image2, image3;  //the images for the shapes
    private int numShapes = 9; //the number of shapes available for display
    private TextView answer1, answer2, answer3; //this is where the answers will be displayed
    private int matchCount; //counts how many correct matches the user has made this round
    private int roundCount = 0; //counts how many rounds the user has completed
    private Date startDate, endDate; //when the game starts and ends
    String userName;  //this will be set if the user gets a high score
    int position; //the place the user achieves in the high score list


    public void onCreate(final Bundle savedInstanceState){

        matchCount=0;

        Log.d(TAG, "onCreate called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_game_view);

        if(roundCount == 0) {
            startDate = new Date();
            Toast intro = Toast.makeText(this, "Drag the names of the shapes to the boxes", Toast.LENGTH_LONG);
            intro.show();
        }

        //set up and draw three random shapes and TextViews

        //the shapes
        image1 = (ImageView)findViewById(R.id.shape1);
        image2 = (ImageView)findViewById(R.id.shape2);
        image3 = (ImageView)findViewById(R.id.shape3);

        //views that will have the correct answers
        answer1 = (TextView)findViewById(R.id.answer1);
        answer2 = (TextView)findViewById(R.id.answer2);
        answer3 = (TextView)findViewById(R.id.answer3);

        //set the shape strings to random shape names and set the images for these shapes
        shape1 = randomShape(image1);
        shape2 = randomShape(image2);
        while(shape1.equals(shape2))  //make sure it isn't a duplicate
            shape2 = randomShape(image2);
        shape3 = randomShape(image3);
        while(shape1.equals(shape3) || shape2.equals(shape3))  //make sure shape3 isn't a duplicate
            shape3 = randomShape(image3);

        //set the answers textviews
        answer1.setText(shape1);
        answer2.setText(shape2);
        answer3.setText(shape3);

        //Put the text for each shape in a random spot
        Random r2 = new Random();
        int rand;
        final TextView tv1 = (TextView)findViewById(R.id.textView1);
        final TextView tv2 = (TextView)findViewById(R.id.textView2);
        final TextView tv3 = (TextView)findViewById(R.id.textView3);
        TextView[] tvArray = {tv1, tv2, tv3};

        for(int i = 3; i >= 1; i--){
            rand = r2.nextInt(i);
            if(i==1)
                tvArray[rand].setText(shape1);
            else if(i==2)
                tvArray[rand].setText(shape2);
            else
                tvArray[rand].setText(shape3);

            tvArray[rand] = tvArray[i-1];
        }



        final class AnswerDragListener implements View.OnDragListener{

            @SuppressLint("NewApi")
            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch(event.getAction()) {


                    case DragEvent.ACTION_DROP:

                        View view = (View) event.getLocalState();
                        TextView dropTarget = (TextView) v;
                        TextView dropped = (TextView) view;


                        if (dropTarget.getText().toString().equals(dropped.getText().toString())) {
                            view.setVisibility(View.INVISIBLE);
                            ((TextView) v).setTextColor(Color.GREEN);
                            dropTarget.setOnDragListener(null);
                            matchCount++;
                            if (matchCount == 3) {
                                roundCount++;
                                if(roundCount < 10)
                                    onCreate(savedInstanceState);
                                else
                                    winner();
                            }

                        } else {
                            Log.d(TAG, "not right");
                        }
                        break;
                    default:
                        break;
                }

                return true;
            }
        }

        //add the touch listeners
        tv1.setOnTouchListener(new ShapeTextTouchListener());
        tv2.setOnTouchListener(new ShapeTextTouchListener());
        tv3.setOnTouchListener(new ShapeTextTouchListener());

        //add the drag listeners
        answer1.setOnDragListener(new AnswerDragListener());
        tv1.setOnDragListener(new AnswerDragListener());
        answer2.setOnDragListener(new AnswerDragListener());
        tv2.setOnDragListener(new AnswerDragListener());
        answer3.setOnDragListener(new AnswerDragListener());
        tv3.setOnDragListener(new AnswerDragListener());

    }

    //returns the name of a random shape
    //and sets the image picture to that shape
    public String randomShape(ImageView image){

        //generate a random number
        Random r = new Random();
        int random = r.nextInt(numShapes);

        switch (random){
            case 0:
                image.setImageResource(R.drawable.circle);
                return "CIRCLE";
            case 1:
                image.setImageResource(R.drawable.ellipse);
                return "ELLIPSE";
            case 2:
                image.setImageResource(R.drawable.square);
                return "SQUARE";
            case 3:
                image.setImageResource(R.drawable.hexagon);
                return "HEXAGON";
            case 4:
                image.setImageResource(R.drawable.parallelogram);
                return "PARALLELOGRAM";
            case 5:
                image.setImageResource(R.drawable.star2);
                return "STAR";
            case 6:
                image.setImageResource(R.drawable.triangle);
                return "TRIANGLE";
            case 7:
                image.setImageResource(R.drawable.rectangle);
                return "RECTANGLE";
            case 8:
                image.setImageResource(R.drawable.pentagon);
                return "PENTAGON";
            default:
                return null;
        }

    }

    //runs when the player has won the game
    public void winner(){
        endDate = new Date();
        double finishTime = (endDate.getTime() - startDate.getTime())/1000;
        String finish = new String();
        finish += finishTime;
        finish = "You win! It took you " + finish + " seconds";

        Toast t = Toast.makeText(this, finish, Toast.LENGTH_LONG);
        t.show();


        //Check to see if this is a high score and if it is then add it to the list
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        position = 11; //holds what place the user gets
        int numScores = 0; //how many scores are in the high score list
        numScores = sharedPref.getInt("numMatchingScores", numScores);

        //run this if there are no scores in the high score list
        if (numScores == 0) {

            numScores++;
            editor.putLong("matchingHighScore1", (long) finishTime);
            position = 1;
        }
        else{ //this runs if there is at least one high score in the list

            //these will hold the names of the keys for the high scores being compared
            String highScore = "matchingHighScore";
            String oldHighScore = "matchingHighScore";

            //set to true if the score is added to the high score list
            boolean addedHighScore = false;

            long temp = 0;

            //a loop that iterates through all of the high scores for comparison
            for(int i = numScores; i > 0; i--){

                //set these strings to the right key values
                highScore += i;
                oldHighScore += (i + 1);

                //the high score in the list that we will check against
                temp = sharedPref.getLong(highScore, temp);

                //if the current time is faster then add the new high score to the list
                //and move the old high score one spot down the list
                if(finishTime < temp) {
                    editor.putLong(oldHighScore, temp);
                    editor.putLong(highScore, (long) finishTime);

                    position = i;

                    //the first time through the loop we need to note that a
                    //high score was added to the list
                    if(i == numScores) {
                        numScores++;
                        addedHighScore = true;
                    }
                }
                //do this part if the new score is not bigger than any older high scores
                //but there is room to add it to the end of the list
                else if (numScores < 10  && !addedHighScore) {
                    editor.putLong(oldHighScore, (long) finishTime);
                    numScores++;
                    position = numScores;
                    addedHighScore = true;
                    break;
                }
                else //otherwise this isn't a high score
                    break;

                //get these keys ready to be set to new values in the next iteration of the loop
                highScore = "matchingHighScore";
                oldHighScore = "matchingHighScore";

            }
        }

        //only 10 high scores are allowed
        if(numScores > 10)
            numScores = 10;


        //update the number of high scores in the list and commit all changes
        editor.putInt("numMatchingScores", numScores);
        editor.commit();


        setContentView(R.layout.get_name);

        EditText name = (EditText) findViewById(R.id.name);


        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    setName(v.getText());

                }

                return true;

            }
        });

    }

    //when the user inputs their name for the high score list do this
    private void setName(CharSequence text) {
        userName = text.toString();

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String setUser = "matchingName" + position;
        editor.putString(setUser, userName);
        editor.commit();

        Intent i = new Intent(this, MatchingHighScores.class);
        startActivity(i);
    }

}
