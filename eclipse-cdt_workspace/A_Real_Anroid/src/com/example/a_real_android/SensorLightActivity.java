package com.example.a_real_android;


import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class SensorLightActivity extends Activity
{
	TextView txtLight= null;
	MyLightSensorListener listener=null;
	SensorManager manager=null;
	Sensor light=null;
	ImageView compass;
  
	 //ָ����,�����Զ���ת��Ļ  <activity android:name=".SensorActivity" android:screenOrientation="portrait"></activity>
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_light);
        
        listener=new MyLightSensorListener();
       
        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        light=manager.getDefaultSensor(Sensor.TYPE_LIGHT);//�ƹ�
        
        List<Sensor> deviceSensors = manager.getSensorList(Sensor.TYPE_ALL);//�ֻ�������֧�ֵĴ�����
        //HuaWei֧������,LIS3DH 3-axis Accelerometer, CM3602 Proximity sensor,CM3602 Light sensor
        for(Sensor s : deviceSensors )
        {
        	Log.e("---supported-sensor:",s.getName());
       
        }
        
        txtLight= (TextView)this.findViewById(R.id.txtLight);
       
    }
    protected void onResume() 
	{
    	super.onResume();
    	manager.registerListener(listener, light, SensorManager.SENSOR_DELAY_NORMAL);//����Ƶ��
		 //�ӿ쵽��SENSOR_DELAY_FASTEST,SENSOR_DELAY_GAME,SENSOR_DELAY_NORMAL,SENSOR_DELAY_UI 
	}

	protected void onPause()//���õ�ʱ��,�Ͳ�ȡֵ,��ʡ��, 
	{
		super.onPause();
        manager.unregisterListener(listener,light);
	}

	
    class MyLightSensorListener implements SensorEventListener
    {
		public void onAccuracyChanged(Sensor sensor, int accuracy) 
		{
		}
		public void onSensorChanged(SensorEvent event) 
		{
			switch (event.sensor.getType())
			{
				 case Sensor.TYPE_LIGHT:
					 txtLight.setText("�ƹ�ֵΪ:" + event.values[0] );//0-1000,һ��Ϊ 300-600
					 break;
			}
		}

		
    }
}
