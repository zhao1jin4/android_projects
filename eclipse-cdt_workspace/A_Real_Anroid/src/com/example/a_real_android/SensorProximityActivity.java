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
	  
	 //ָ����,�����Զ���ת��Ļ  <activity android:name=".SensorActivity" android:screenOrientation="portrait"></activity>
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_proximity);
	
		manager=(SensorManager)getSystemService(SENSOR_SERVICE);
		proxmity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);  
		listener=new MyProximitySensorListener();
		List<Sensor> deviceSensors = manager.getSensorList(Sensor.TYPE_ALL);//�ֻ�������֧�ֵĴ�����
		//HuaWei֧������,LIS3DH 3-axis Accelerometer, CM3602 Proximity sensor,CM3602 Light sensor
		for(Sensor s : deviceSensors )
		{
			Log.e("---supported-sensor:",s.getName());
		}
        txtPromity= (TextView)this.findViewById(R.id.txtPromity);
 
    }
    protected void onResume() 
	{
    	super.onResume();
		 manager.registerListener(listener, proxmity, SensorManager.SENSOR_DELAY_NORMAL);//����Ƶ��
		 //�ӿ쵽��SENSOR_DELAY_FASTEST,SENSOR_DELAY_GAME,SENSOR_DELAY_NORMAL,SENSOR_DELAY_UI 
	}

	protected void onPause()//���õ�ʱ��,�Ͳ�ȡֵ,��ʡ��, 
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
				case Sensor.TYPE_PROXIMITY:// ���봫����,���ڵ绰�ڶ���ʱ,����
					float max=proxmity.getMaximumRange();//��9 ,��0��9����ֵ
					txtPromity.setText("����Ϊ:"+event.values[0]);//0��9
					System.out.println("max����Ϊ :"+max);
					break;
 
			}
		}
    }

} 
 