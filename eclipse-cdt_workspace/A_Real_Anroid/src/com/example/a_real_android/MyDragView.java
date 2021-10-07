package com.example.a_real_android;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class MyDragView extends View {
	public MyDragView(Context context)
	{
		super(context);
	}

	public boolean onTouchEvent(MotionEvent event) 
	{
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
			break;
			case MotionEvent.ACTION_MOVE:
			break;
			case MotionEvent.ACTION_UP:
				break;
		}
		return super.onTouchEvent(event);
	}

}
