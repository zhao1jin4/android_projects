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
//	  Bitmap mbitmapBall ;
//	  Canvas mCanvas; 
//	  Paint mPaint;
  
	  //<uses-feature android:name="android.hardware.sensor.accelerometer"  android:required="true" />
	  //�ֻ���Ļ������ԭ��,Z���������������û���,y��������������(ָ����Ͳ),x����������, ��OpenGLһ����
	 //ָ����,�����Զ���ת��Ļ  <activity android:name=".SensorActivity" android:screenOrientation="portrait"></activity>
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_accelero);
       
        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        accelero=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        listener=new MySensorListener();
       
        List<Sensor> deviceSensors = manager.getSensorList(Sensor.TYPE_ALL);//�ֻ�������֧�ֵĴ�����
        //HuaWei֧������,LIS3DH 3-axis Accelerometer, CM3602 Proximity sensor,CM3602 Light sensor
        for(Sensor s : deviceSensors )
        {
        	Log.e("---supported-sensor:",s.getName());
        }
        
        img= (ImageView)this.findViewById(R.id.accelero_compass);
        txt=(TextView)this.findViewById(R.id.txtAccelerometer);
        img.setKeepScreenOn(true);//������
        
//        mbitmapBall =  BitmapFactory.decodeResource(SensorAcceleroActivity.this.getResources(), R.drawable.compass);
//        mCanvas = new Canvas();  
//        mPaint = new Paint();  
//        mPaint.setColor(Color.WHITE);  
        
    }
    protected void onResume() 
   	{
       	super.onResume();
   		 manager.registerListener(listener, accelero, SensorManager.SENSOR_DELAY_NORMAL);//����Ƶ��
   		 //�ӿ쵽��SENSOR_DELAY_FASTEST,SENSOR_DELAY_GAME,SENSOR_DELAY_NORMAL,SENSOR_DELAY_UI 
   	}

   	protected void onPause()//���õ�ʱ��,�Ͳ�ȡֵ,��ʡ��, 
   	{
   		super.onPause();
        manager.unregisterListener(listener,accelero);
   	}
    
    class MySensorListener implements SensorEventListener
    {
		public void onAccuracyChanged(Sensor sensor, int accuracy) 
		{
		}
		public void onSensorChanged(SensorEvent event) 
		{
			
			switch (event.sensor.getType())
			{	
				//�ٷ�ʾ������
				case Sensor.TYPE_ACCELEROMETER://��������(�����������,��z)ֵԼΪ9.8,�����Թ���
					 
					//�����������ֵ��9.8,Ҫ��������㷨�Ŷ�,�Ǵӹٷ��ĵ��ϸ��Ƶ�,ʹ��linear_acceleration
					 final float alpha = 0.8f;
					 float[] gravity=new float[3];
					 float[] linear_acceleration=new float[3];
					 //�������ٶ�
					  gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
					  gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
					  gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
					//ֱ�߼��ٶ�,Ҫ��ȥ�������ٶ�
					  linear_acceleration[0] = event.values[0] - gravity[0];
					  linear_acceleration[1] = event.values[1] - gravity[1];
					  linear_acceleration[2] = event.values[2] - gravity[2];
					  //---------
//					 txt.setText(linear_acceleration[0]+","+linear_acceleration[1]+","+linear_acceleration[2]);
//					 //img
//					 mCanvas.drawBitmap(mbitmapBall, linear_acceleration[0],linear_acceleration[1], mPaint);  
					break;
				 
			}
		}
    }

}

