package org.zh.mycomponents;



import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;
import android.widget.Toast;

import org.zh.mycomponents.R;

public class TimePickerActivity extends Activity {

	Chronometer chrno = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.time_picker);
		chrno =  (Chronometer)this.findViewById(R.id.chronometer);

		TimePicker tp =  (TimePicker)this.findViewById(R.id.time_picker);
		tp.setIs24HourView(true);
		tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				setTitle("sel: "+hourOfDay+":"+minute);
				Toast.makeText(TimePickerActivity.this, "sel: "+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
			}
		});

		Button startBtn =  (Button)this.findViewById(R.id.startChronoBtn);
		startBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				chrno.start();
			}});
		Button stopChronoBtn =  (Button)this.findViewById(R.id.stopChronoBtn);
		stopChronoBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				chrno.stop();
			}});
		Button resetChronoBtn =  (Button)this.findViewById(R.id.resetChronoBtn);
		resetChronoBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				chrno.setBase(SystemClock.elapsedRealtime());
				chrno.setFormat("格式化后的为:%s"); //%s
			}});

		chrno.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
			@Override
			public void onChronometerTick(Chronometer chronometer) {

				String str=chronometer.getText().toString();//是格式化后的字串
				Log.d("TimePickerActivity",str);
				/*
				//正则如何做
				Pattern p=Pattern.compile("([0-9]+:[0-9]+)");
				Matcher m=p.matcher(chronometer.getText().toString());
				MatchResult result=m.toMatchResult();
				String timeStr=result.group();
				
				*/
				if(str.endsWith("10"))
				{
					Log.d("TimePickerActivity","------OnChronometerTickListener---");
				}
			}
		});
	}
}