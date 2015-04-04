package com.tux4kids.android.tuxgeometry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Jason Barker on 3/16/2015.
 * This activity is a game that has users find the area of shapes
 */
public class AreaGameActivity extends Activity {

    private static final String TAG = "AreaGameActivity";
    private TextView answer1, answer2, answer3, height, width;  //the possible answers
    private EditText area;   //where to enter the answers
    String shape;
    private int numShapes = 5;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rectangle_area);

        //get all the views from the layout
        width = (TextView) findViewById(R.id.width);
        height = (TextView) findViewById(R.id.height);
        area = (EditText) findViewById(R.id.area);
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

            area.setHint("Area = height * width");

            areaAnswer = heightInt * widthInt;

            if(shape.equals("TRIANGLE")) {
                areaAnswer *= .5;
                area.setHint("Area = 1/2 * height * width");
            }



        }


        //set up circle
        if(shape.equals("CIRCLE")) {
            height.setVisibility(View.INVISIBLE);
            width.setText("Radius: " + widthInt);
            area.setHint(Html.fromHtml("Area = pi * r<sup><small>2</small></sup>"));
            areaAnswer = widthInt * widthInt * 3.14;

        }

        TextView[] tvArray = {answer1, answer2, answer3};

        String wrongAnswer1 = new String();
        wrongAnswer1 += areaAnswer + mRandom.nextInt(10) + 1;
        String rightAnswer = new String();
        rightAnswer += areaAnswer;

        //this could come out to zero or negative.  fix this
        String wrongAnswer2 = new String();
        wrongAnswer2 += areaAnswer - mRandom.nextInt(10);


        for(int i = 3; i >= 1; i--){
            int rand = mRandom.nextInt(i);
            if(i==1)
                tvArray[rand].setText(wrongAnswer1);
            else if(i==2)
                tvArray[rand].setText(rightAnswer);
            else
                tvArray[rand].setText(wrongAnswer2);

            tvArray[rand] = tvArray[i-1];
        }

        //set up the private DragListener
        final String rightTarget = rightAnswer;
        final class AnswerDragListener implements View.OnDragListener{

            @SuppressLint("NewApi")
            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch(event.getAction()) {


                    case DragEvent.ACTION_DROP:
                    {
                        //do stuff here
                        Log.d(TAG, "ACTION_DROP");

                        View view = (View) event.getLocalState();
                        TextView dropTarget = (TextView) v;
                        TextView dropped = (TextView) view;

                        if (rightTarget.equals(dropped.getText().toString())) {

                            dropTarget.setText(rightTarget);



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
}
