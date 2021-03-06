package org.zh.mycomponents;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import org.zh.mycomponents.R;

public class MatrixView extends View {
	Bitmap bitmap=null;
	Matrix matrix=new Matrix();//graphic包下的
	public MatrixView(Context context,AttributeSet attrs) {
		super(context,attrs);

		bitmap=  BitmapFactory.decodeResource(getResources(), R.drawable.eoe_android);//这里不能使用mipmap
		matrix.preScale(2, 2);
		matrix.preTranslate(50,50);
		matrix.preRotate(60);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap, matrix, null);
	}

}
