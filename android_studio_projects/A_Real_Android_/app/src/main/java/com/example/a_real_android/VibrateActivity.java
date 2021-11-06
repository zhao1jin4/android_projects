package com.example.a_real_android;


import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class VibrateActivity extends Activity
{
	Vibrator vibrator=null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vibrate);
		vibrator=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);//权限

		Button startVibrate = (Button) findViewById(R.id.startVibrate);
		startVibrate.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				//vibrator.vibrate(new long[]{1000,100,1000,200}, 0);//-1表示不重复
				VibrationEffect vibrationEffect=  VibrationEffect.createWaveform(new long[]{1000,100,1000,200}, 0);//-1表示不重复
				vibrator.vibrate(vibrationEffect );
			}
		});


		Button stopVibrate = (Button) findViewById(R.id.stopVibrate);
		stopVibrate.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				vibrator.cancel();
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
