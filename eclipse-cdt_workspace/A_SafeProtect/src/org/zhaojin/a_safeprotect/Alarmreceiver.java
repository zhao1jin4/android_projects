package org.zhaojin.a_safeprotect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarmreceiver extends BroadcastReceiver
{
	private static final String TAG = "Alarmreceiver";
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.i(TAG, "在  onReceive ");

		if (intent.getAction().equals("alarm.action")) 
		{
			Log.i(TAG, "启动TestService");
			Intent i = new Intent();
			i.setClass(context, TestService.class);
			// 启动service
			// 多次调用startService并不会启动多个service 而是会多次调用onStart
			context.startService(i);
		}
	}
}