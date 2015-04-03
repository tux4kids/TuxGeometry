package com.tux4kids.android.tuxgeometry;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jason Barker on 4/1/2015.
 */
public class ShapeTextTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent){
            if (motionEvent.getAction()  == motionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else
                return false;
        }

}
