package com.eoeandroid.broadcastReceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class EoeAndroidReceiver2 extends BroadcastReceiver {
	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		DeleteNotification();
	}

	private void DeleteNotification() {
		
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(EoeAndroidReceiver1.NOTIFICATION_ID);
	
	}

}
