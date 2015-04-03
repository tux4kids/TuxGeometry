package com.tux4kids.android.tuxgeometry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Jason Barker on 3/16/2015.
 * This activity is a game that has users find the area of shapes
 */
public class AreaGameActivity extends Activity {

    private TextView answer1, answer2, answer3;  //the possible answers
    private EditText width, height, area;   //where to enter the answers
    String shape;
    private int numShapes = 5;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        setContentView(R.layout.rectangle_area);

        ImageView shapeImage = (ImageView)findViewById(R.id.theShape);
        shape = randomShape(shapeImage);


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
