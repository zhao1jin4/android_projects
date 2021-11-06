package com.example.a_real_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.a_real_android.opengl.OpenGLES20Basic;

public class MainActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        
        
      
        
        Button dragButton = (Button) findViewById(R.id.dragButton);
        dragButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, DragActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        
        
        Button blueToothButton = (Button) findViewById(R.id.blueToothButton);
        blueToothButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, BlueToothActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        
        
        
        Button locationButton = (Button) findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, LocationActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        
        Button wifiButton = (Button) findViewById(R.id.wifiButton);
        wifiButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, WiFiActivity.class);
			        		startActivity(intent);
			        	}
			        });   
        
        Button vibrateButton = (Button) findViewById(R.id.vibrateButton);
        vibrateButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, VibrateActivity.class);
			        		startActivity(intent);
			        	}
			        });   
        Button cameraPhotoButton = (Button) findViewById(R.id.cameraPhotoButton);
        cameraPhotoButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, CameraPhotoActivity.class);
			        		startActivity(intent);
			        	}
			        });  
        Button cameraRecordButton = (Button) findViewById(R.id.cameraRecordButton);
        cameraRecordButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, CameraRecordActivity.class);
			        		startActivity(intent);
			        	}
			        });
        Button sensorProximityButton = (Button) findViewById(R.id.sensorProximityButton);
        sensorProximityButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, SensorProximityActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        Button sensorAcceleroButton = (Button) findViewById(R.id.sensorAcceleroButton);
        sensorAcceleroButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, SensorAcceleroActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        
        Button sensorLightButton = (Button) findViewById(R.id.sensorLightButton);
        sensorLightButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, SensorLightActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        Button basicGLButton = (Button) findViewById(R.id.basicGLButton);
        basicGLButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, OpenGLES20Basic.class);
			        		startActivity(intent);
			        	}
			        });
        Button poweroffAlarmButton = (Button) findViewById(R.id.poweroffAlarmButton);
        poweroffAlarmButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, PoweroffAlarmActivity.class);
			        		startActivity(intent);
			        	}
			        });
        Button exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		System.exit(0);
			        	}
			        });  
        

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
