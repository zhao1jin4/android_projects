package com.example.a_real_android;


import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class SensorProximityActivity extends Activity
{
	MyProximitySensorListener listener=null;
	SensorManager manager=null;
	Sensor proxmity=null;
	TextView txtPromity= null;

	//指南针,不能自动旋转屏幕  <activity android:name=".SensorActivity" android:screenOrientation="portrait"></activity>
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_proximity);

		manager=(SensorManager)getSystemService(SENSOR_SERVICE);
		proxmity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);//proximity 接近
		listener=new MyProximitySensorListener();
		List<Sensor> deviceSensors = manager.getSensorList(Sensor.TYPE_ALL);//手机中所有支持的传敏器
		for(Sensor s : deviceSensors )
		{
			Log.e("---supported-sensor:",s.getName());
		}
		txtPromity= (TextView)this.findViewById(R.id.txtPromity);

	}
	protected void onResume()
	{
		super.onResume();
		manager.registerListener(listener, proxmity, SensorManager.SENSOR_DELAY_NORMAL);//采样频率
		//从快到慢SENSOR_DELAY_FASTEST,SENSOR_DELAY_GAME,SENSOR_DELAY_NORMAL,SENSOR_DELAY_UI
	}

	protected void onPause()//不用的时候,就不取值,会省电,
	{
		super.onPause();
		manager.unregisterListener(listener, proxmity);
	}

	class MyProximitySensorListener implements SensorEventListener
	{
		public void onAccuracyChanged(Sensor sensor, int accuracy)
		{
		}
		public void onSensorChanged(SensorEvent event)
		{
			switch (event.sensor.getType())
			{
				case Sensor.TYPE_PROXIMITY:// 距离传感器,用于电话在耳边时,锁屏
					float max=proxmity.getMaximumRange();
					txtPromity.setText("距离为:"+event.values[0]);//值只有0或1
					System.out.println("max距离为 :"+max);//1
					break;

			}
		}
	}

} 
 