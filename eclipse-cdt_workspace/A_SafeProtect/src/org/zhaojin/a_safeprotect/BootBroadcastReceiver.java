package org.zhaojin.a_safeprotect;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver
{
	private static final String TAG = "BootBroadcast";
	
	@Override
	public void onReceive(Context context, Intent mintent) 
	{
		Log.i(TAG, "在  BootBroadcastReceiver ");
		if (Intent.ACTION_BOOT_COMPLETED.equals(mintent.getAction())) 
		{
			Log.i(TAG, "在  ACTION_BOOT_COMPLETED ");
			// 启动完成
			Intent intent = new Intent(context, Alarmreceiver.class);
			intent.setAction("alarm.action");
			PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
			long firstime = SystemClock.elapsedRealtime();
			AlarmManager am = (AlarmManager) context .getSystemService(Context.ALARM_SERVICE);

			// 10秒一个周期，不停的发送广播
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 10 * 1000, sender);
		}

	}
}