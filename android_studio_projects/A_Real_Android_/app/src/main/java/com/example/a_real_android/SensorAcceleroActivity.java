package com.example.a_real_android;


import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


public class SensorAcceleroActivity extends Activity
{

	MySensorListener listener=null;
	SensorManager manager=null;
	Sensor accelero=null;
	TextView txt;

	ImageView img;
	Bitmap mbitmapBall ;
	Canvas mCanvas;
	Paint mPaint;

	//<uses-feature android:name="android.hardware.sensor.accelerometer"  android:required="true" />
	//手机屏幕中心中原点,Z轴正方向是面向用户的,y轴正方向是向上(指向听筒),x轴正方向右, 和OpenGL一样的
	//指南针,不能自动旋转屏幕  <activity android:name=".SensorActivity" android:screenOrientation="portrait"></activity>
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_accelero);

		manager=(SensorManager)getSystemService(SENSOR_SERVICE);
		accelero=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		listener=new MySensorListener();

		List<Sensor> deviceSensors = manager.getSensorList(Sensor.TYPE_ALL);//手机中所有支持的传敏器
		//HuaWei支持三个,LIS3DH 3-axis Accelerometer, CM3602 Proximity sensor,CM3602 Light sensor
		// RedMi9A支持14个
		for(Sensor s : deviceSensors )
		{
			Log.e("---supported-sensor:",s.getName());
		}

		img= (ImageView)this.findViewById(R.id.accelero_compass);
		txt=(TextView)this.findViewById(R.id.txtAccelerometer);
		img.setKeepScreenOn(true);//不锁屏

        mbitmapBall =  BitmapFactory.decodeResource(SensorAcceleroActivity.this.getResources(), R.drawable.compass);
        mCanvas = new Canvas();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);

	}
	protected void onResume()
	{
		super.onResume();
		manager.registerListener(listener, accelero, SensorManager.SENSOR_DELAY_NORMAL);//采样频率
		//从快到慢SENSOR_DELAY_FASTEST,SENSOR_DELAY_GAME,SENSOR_DELAY_NORMAL,SENSOR_DELAY_UI
	}

	protected void onPause()//不用的时候,就不取值,会省电,
	{
		super.onPause();
		manager.unregisterListener(listener,accelero);
	}

	class MySensorListener implements SensorEventListener
	{
		public void onAccuracyChanged(Sensor sensor, int accuracy)
		{
			System.out.println("MySensorListener onAccuracyChanged");
		}
		public void onSensorChanged(SensorEvent event)
		{

			System.out.println("MySensorListener onSensorChanged event.sensor.getType()="+event.sensor.getType());//1
			switch (event.sensor.getType())
			{
				//官方示例代码
				case Sensor.TYPE_ACCELEROMETER://重力方向(正向放在桌上,是z)值约为9.8,球在迷宫里

					//因放在桌步动值是9.8,要做下面的算法才对,是从官方文档上复制的,使用linear_acceleration
					final float alpha = 0.8f;
					float[] gravity=new float[3];
					float[] linear_acceleration=new float[3];
					//重力加速度
					gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
					gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
					gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
					//直线加速度,要减去重力加速度
					linear_acceleration[0] = event.values[0] - gravity[0];
					linear_acceleration[1] = event.values[1] - gravity[1];
					linear_acceleration[2] = event.values[2] - gravity[2];


					//---------
					 txt.setText(linear_acceleration[0]+","+linear_acceleration[1]+","+linear_acceleration[2]);
//					 //img
					 mCanvas.drawBitmap(mbitmapBall, linear_acceleration[0],linear_acceleration[1], mPaint);//不能很好的移动物体
					break;

			}
		}
	}

}

