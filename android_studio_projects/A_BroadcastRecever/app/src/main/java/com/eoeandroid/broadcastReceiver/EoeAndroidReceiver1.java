package com.eoeandroid.broadcastReceiver;

import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class EoeAndroidReceiver1 extends BroadcastReceiver {
	Context context;
	public static int NOTIFICATION_ID = 21321;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;

		showNotification();
	}

	private void showNotification() {
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, ActivityMain.class), 0);

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);


		Notification notification = new Notification.Builder(context)
				.setContentTitle("titile")
				.setContentText("content_在EoeAndroidReceiver1中")
				// .setLargeIcon( )
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(contentIntent)//跳到intent
				.build();

		notificationManager.notify(NOTIFICATION_ID, notification);
	}
}
