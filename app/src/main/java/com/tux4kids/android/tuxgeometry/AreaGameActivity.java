package com.tux4kids.android.tuxgeometry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
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
 * Created by Jason Barker on 3/16/2015.
 * This activity is a game that has users find the area of shapes
 */
public class AreaGameActivity extends Activity {

    private static final String TAG = "AreaGameActivity";
    private TextView answer1, answer2, answer3, height, width, area;
    String shape; //the name of the shape that will be displayed
    private int numShapes = 5; //the number of shapes that are possible to display
    private int roundCount = 0; //counts the number of rounds the user has completed
    Date startDate, endDate; //used to see how long it takes to complete the game
    String userName;  //this will be set if the user gets a high score
    int position; //the place the user achieves in the high score list

    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_game);

        //run this the first round to give instructions
        if(roundCount == 0) {
            Toast intro = Toast.makeText(this, "Tap the correct answer.", Toast.LENGTH_LONG);
            intro.show();
            startDate = new Date();
        }

        //get all the views from the layout
        width = (TextView) findViewById(R.id.width);
        height = (TextView) findViewById(R.id.height);
        area = (TextView) findViewById(R.id.area);
        answer1 = (TextView) findViewById(R.id.answer1);
        answer2 = (TextView) findViewById(R.id.answer2);
        answer3 = (TextView) findViewById(R.id.answer3);

        //add the OnTouchListeners
        answer1.setOnTouchListener(new ShapeTextTouchListener());
        answer2.setOnTouchListener(new ShapeTextTouchListener());
        answer3.setOnTouchListener(new ShapeTextTouchListener());



        //get random numbers for height and width
        Random mRandom = new Random();
        double heightInt = mRandom.nextInt(10) + 1;
        double widthInt = mRandom.nextInt(10) + 1;
        double areaAnswer = 0;

        //add a random shape to the view
        ImageView shapeImage = (ImageView)findViewById(R.id.theShape);
        shape = randomShape(shapeImage);

        //set up rectangle, square, or parallelogram
        if(shape.equals("SQUARE") || shape.equals("RECTANGLE") || shape.equals("PARALLELOGRAM") || shape.equals("TRIANGLE")){
            if(heightInt > widthInt)
                widthInt += 10;
            if(shape.equals("SQUARE"))
                widthInt = heightInt;

            width.setText("Width: " + widthInt);
            height.setText("Height: " + heightInt);

            area.setText("Area = height * width");

            areaAnswer = heightInt * widthInt;

            if(shape.equals("TRIANGLE")) {
                areaAnswer *= .5;
                area.setText("Area = 1/2 * height * width");
            }

        }


        //set up circle
        if(shape.equals("CIRCLE")) {
            height.setVisibility(View.INVISIBLE);
            width.setText("Radius: " + widthInt);
            area.setText(Html.fromHtml("Area = pi * r<sup><small>2</small></sup>"));
            areaAnswer = widthInt * widthInt * 3.14;

        }

        TextView[] tvArray = {answer1, answer2, answer3};

        //generate the wrong answers
        double wrongDouble1 = areaAnswer - 5 + mRandom.nextInt(10);
        while(wrongDouble1 == areaAnswer || wrongDouble1 < 1)
            wrongDouble1 = areaAnswer - 5 + mRandom.nextInt(10);
        double wrongDouble2 = areaAnswer - 5 + mRandom.nextInt(10);
        while(wrongDouble2 == areaAnswer || wrongDouble2 < 1 || wrongDouble2 == wrongDouble1)
            wrongDouble2 = areaAnswer - 5 + mRandom.nextInt(10);


        //load the answers into random spots
        for(int i = 3; i >= 1; i--){
            int rand = mRandom.nextInt(i);
            if(i==1)
                tvArray[rand].setText(String.format("%.1f", wrongDouble1));
            else if(i==2)
                tvArray[rand].setText(String.format( "%.1f", areaAnswer ));
            else
                tvArray[rand].setText(String.format( "%.1f", wrongDouble2 ));

            tvArray[rand] = tvArray[i-1];
        }

        //set up the private DragListener
        final String rightTarget = String.format( "%.1f", areaAnswer );
        final class AnswerDragListener implements View.OnDragListener{

            @SuppressLint("NewApi")
            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch(event.getAction()) {


                    case DragEvent.ACTION_DROP:
                    {

                        View view = (View) event.getLocalState();
                        TextView dropTarget = (TextView) v;
                        TextView dropped = (TextView) view;

                        if (rightTarget.equals(dropped.getText().toString())) {

                            dropTarget.setText(rightTarget);

                            //if the game isn't over then load another shape
                            if(roundCount < 10) {
                                roundCount++;
                                onCreate(savedInstanceState);
                            }
                            else //the game is over
                                winner();
                        }


                        break;
                    }
                    default:
                        break;
                }

                return true;
            }
        }

        //add the DragListeners
        answer1.setOnDragListener(new AnswerDragListener());
        answer2.setOnDragListener(new AnswerDragListener());
        answer3.setOnDragListener(new AnswerDragListener());
        area.setOnDragListener(new AnswerDragListener());

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
                image.setImageResource(R.drawable.square);
                return "SQUARE";
            case 2:
                image.setImageResource(R.drawable.parallelogram);
                return "PARALLELOGRAM";
            case 3:
                image.setImageResource(R.drawable.triangle);
                return "TRIANGLE";
            case 4:
                image.setImageResource(R.drawable.rectangle);
                return "RECTANGLE";
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
        numScores = sharedPref.getInt("numAreaScores", numScores);

        //run this if there are no scores in the high score list
        if (numScores == 0) {

            numScores++;
            editor.putLong("areaHighScore1", (long) finishTime);
            position = 1;
        }
        else{ //this runs if there is at least one high score in the list

            //these will hold the names of the keys for the high scores being compared
            String highScore = "areaHighScore";
            String oldHighScore = "areaHighScore";

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
                highScore = "areaHighScore";
                oldHighScore = "areaHighScore";

            }
        }

        //only 10 high scores are allowed
        if(numScores > 10)
            numScores = 10;


        //update the number of high scores in the list and commit all changes
        editor.putInt("numAreaScores", numScores);
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

        String setUser = "areaName" + position;
        editor.putString(setUser, userName);
        editor.commit();

        Intent i = new Intent(this, AreaHighScores.class);
        startActivity(i);
    }

}
