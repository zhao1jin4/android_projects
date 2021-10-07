
package com.ex_alarmService_1;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class NotifyService extends Service {

	private static final String TAG = "TestService";
	private NotificationManager _nm;

	public IBinder onBind(Intent i) {
		Log.e(TAG, "============> TestService.onBind");
		return null;
	}

	public class LocalBinder extends Binder {
		NotifyService getService() {
			return NotifyService.this;
		}
	}
	public boolean onUnbind(Intent i) {
		Log.e(TAG, "============> TestService.onUnbind");
		return false;
	}

	public void onRebind(Intent i) {
		Log.e(TAG, "============> TestService.onRebind");
	}

	public void onCreate() {
		Log.e(TAG, "============> TestService.onCreate");
		_nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification();
	}

	public void onStart(Intent intent, int startId) {
		Log.e(TAG, "============> TestService.onStart");
	}

	public void onDestroy() {
		_nm.cancel(R.string.service_started);
		Log.e(TAG, "============> TestService.onDestroy");
	}

	public void showNotification() {

		 Notification notification = new Notification.Builder(NotifyService.this)
         .setContentTitle("Test Service")
         .setContentText("Service started")
         .setSmallIcon(R.drawable.ic_launcher)
         //.setLargeIcon()
         .build();
		 
//		Notification notification = new Notification(R.drawable.ic_launcher,
//				"Service started", System.currentTimeMillis());

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

		// must set this for content view, or will throw a exception
//		notification.setLatestEventInfo(this, "Test Service",
//				"Service started", contentIntent);

		_nm.notify(R.string.service_started, notification);
		
		//---------------------------------------------
//		Toast.makeText(this, "Service connected", Toast.LENGTH_SHORT).show(); 
	}
}
