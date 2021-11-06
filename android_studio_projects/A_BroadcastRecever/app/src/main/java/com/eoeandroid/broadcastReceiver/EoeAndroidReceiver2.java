package com.eoeandroid.broadcastReceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class EoeAndroidReceiver2 extends BroadcastReceiver {
	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("ERROR+LOG","-------onReceive EoeAndroidReceiver2-------");
		this.context = context;
		Toast.makeText(context, "onReceive EoeAndroidReceiver2" , Toast.LENGTH_SHORT).show();

		DeleteNotification();
	}

	private void DeleteNotification() {
		
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(EoeAndroidReceiver1.NOTIFICATION_ID);
	
	}

}
