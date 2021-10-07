package com.example.a_real_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
 
	public void onReceive(Context context, Intent arg1) {
		 Toast.makeText(context, "short alarm", Toast.LENGTH_LONG).show();
	}

}
