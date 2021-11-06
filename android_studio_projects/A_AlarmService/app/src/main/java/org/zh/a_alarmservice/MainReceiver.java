package org.zh.a_alarmservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MainReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		context.startService(new Intent(context, NotifyService.class));
		System.out.println("in Reiceiver------");
		Toast.makeText(context, "in Reiceiver------" , Toast.LENGTH_SHORT).show();
	}

}
