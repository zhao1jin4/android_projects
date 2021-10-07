package com.example.a_real_android;


import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class PoweroffAlarmActivity extends Activity
{
	  TimePicker alarmTimePicker;
	  TimePicker poweroffTimePicker;
	 
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poweroff_alarm);
        
        Calendar calendar = Calendar.getInstance();  
    	int hour=calendar.get(Calendar.HOUR_OF_DAY);
    	int minute=calendar.get(Calendar.MINUTE);
    	
        alarmTimePicker=(TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTimePicker.setIs24HourView(true);
        alarmTimePicker.setCurrentHour(hour);
        alarmTimePicker.setCurrentMinute(minute);
        
    	poweroffTimePicker=(TimePicker)findViewById(R.id.poweroffTimePicker);
    	poweroffTimePicker.setIs24HourView(true);
    	poweroffTimePicker.setCurrentHour(hour);
    	poweroffTimePicker.setCurrentMinute(minute);
    	
        Button poweroffButton = (Button) findViewById(R.id.poweroffButton);
        poweroffButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
							int hour =poweroffTimePicker.getCurrentHour();
							int minutes =poweroffTimePicker.getCurrentMinute();
			        		    
		        		    Calendar myCal = Calendar.getInstance();  
		        		    myCal.set(Calendar.HOUR_OF_DAY,hour);  
		        		    myCal.set(Calendar.MINUTE,minutes);  
		        		    long  futureTime = myCal.getTimeInMillis();   
		        		    
		        		    Context context=PoweroffAlarmActivity.this;
		        		    
		        		    Intent intent = new Intent(context,PoweroffReceiver.class);
		         		    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);  //getService ,getBroadcast ,getActivity
		        		    
		         		    AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);  
		        		    alarm.set(AlarmManager.RTC,123123 , pendingIntent);   
		        		 
			        	}
			        });
        
        Button alarmButton = (Button) findViewById(R.id.alarmButton);
        alarmButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		alarmTimePicker.setIs24HourView(true);
			        		int hour =alarmTimePicker.getCurrentHour();
			        		int minutes =alarmTimePicker.getCurrentMinute();
		        		     
		        		    Calendar myCal = Calendar.getInstance();  
		        		    myCal.set(Calendar.HOUR_OF_DAY,hour);  
		        		    myCal.set(Calendar.MINUTE,minutes);  
		        		    long  futureTime = myCal.getTimeInMillis();   
		        		    
		        		    Context context=PoweroffAlarmActivity.this;
		        		    Intent intent = new Intent(context,AlarmReceiver.class);
		        		    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);  //getService ,getBroadcast ,getActivity
		        		   
		        		    AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);  
		        		    alarm.set(AlarmManager.RTC,futureTime, pendingIntent);
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
