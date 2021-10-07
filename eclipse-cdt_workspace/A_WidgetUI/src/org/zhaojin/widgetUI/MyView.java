package org.zhaojin.widgetUI;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		
	}
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint paint=new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawCircle(50, 50, 25, paint);
		
		
		Rect rect=new Rect(20,100,200,300);
		paint.setStyle(Style.STROKE);
		canvas.drawRect(rect, paint);
		
		paint.setColor(Color.RED);
		canvas.drawLine(100, 100, 200, 200, paint);
		
		Path path=new Path();
		path.moveTo(150,150);
		path.lineTo(50,350);
		canvas.drawPath(path, paint);
		
		RectF rectf=new RectF(22.2f,102.2f,180.5f,280.5f);
		canvas.drawRect(rectf, paint);
		
		
		paint.setTextSize(18);
		canvas.drawText("中华人民共和国", 20, 300, paint);
		
		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		canvas.drawBitmap(bitmap , 50 , 500 , paint);
	}

}
