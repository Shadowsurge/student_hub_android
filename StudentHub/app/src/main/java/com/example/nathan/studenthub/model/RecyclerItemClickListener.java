package com.example.nathan.studenthub.model;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Nathan on 16/04/2017.
 */

public class RecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener
{
    private static final String TAG = "RecyclerItemClickListen";

    // Callback to say something has been clicked
    public interface OnClickListener
    {
        void onItemClick( View view, int position);
        void onItemLongClick(View view, int position);
    }

    private final OnClickListener listener;
    private final GestureDetectorCompat gestureDetector;

    // Gesture detector needs the context of the caller
    // A reference to the Recycler View is needed to know which one was being clicked
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnClickListener clickListener)
    {
        this.listener = clickListener;

        // create an instance of the Simple Gesture class, to override the SingleTap method for clicking
        gestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent event)
            {
                Log.d(TAG, "onSingleTapUp was called");

                // Get the view that was clicked
                View view = recyclerView.findChildViewUnder(event.getX(), event.getY());

                // if the view and listener exist - pass the view and it's position to the listener
                if(view != null && listener != null)
                {
                    listener.onItemClick(view, recyclerView.getChildAdapterPosition(view));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent event)
            {
                // Get the view that was clicked
                View view = recyclerView.findChildViewUnder(event.getX(), event.getY());

                // if the view and listener exist - pass the view and it's position to the listener
                if(view != null && listener != null)
                {
                    listener.onItemLongClick(view, recyclerView.getChildAdapterPosition(view));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
    {
        if(gestureDetector != null)
        {
            return gestureDetector.onTouchEvent(e);
        }
        else
        {
            return false;
        }
    }
}
