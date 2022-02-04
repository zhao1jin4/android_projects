package org.zh.mycomponents;



import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import org.zh.mycomponents.R;

public class DatePickerActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.date_picker);
		DatePicker dp =  (DatePicker)this.findViewById(R.id.date_picker);
		dp.init(2009, 5, 17, new DatePicker.OnDateChangedListener()
			{
				@Override
				public void onDateChanged(DatePicker view, int year,
						int monthOfYear, int dayOfMonth)
				{
					setTitle("sel:"+year+"/"+monthOfYear+"/"+dayOfMonth);
					Toast.makeText(DatePickerActivity.this, "sel:"+year+"/"+monthOfYear+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
				}
			});
	}
	

 
}