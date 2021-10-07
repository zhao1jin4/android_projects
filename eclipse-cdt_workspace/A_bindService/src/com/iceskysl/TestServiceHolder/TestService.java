
package com.iceskysl.TestServiceHolder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

	private static final String TAG = "TestService";
	private NotificationManager _nm;
	
	public IBinder onBind(Intent i) {
		Log.e(TAG, "============> TestService.onBind");
		return new LocalBinder();//返回的Bunder是用来向Activity提供Service中的方法调用.
	}

	class LocalBinder extends Binder {
		TestService getService() 
		{
			return TestService.this;
		}
		public String getHello()
		{
			return TestService.this.getHello();
		}
	}
	public String getHello()
	{
		return "how do you do!";
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
	
	private void showNotification()
	{
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, TestServiceHolder.class), 0);
		
//		Notification notification = new Notification(R.drawable.face_1,
//				"Service started", System.currentTimeMillis());

		// must set this for content view, or will throw a exception
//		notification.setLatestEventInfo(this, "Test Service", "Service started", contentIntent);
				
		Notification notification = new Notification.Builder(this)
        .setContentTitle("titile_Service started")
        .setContentText("content_Service started")
        // .setLargeIcon( )
        .setSmallIcon(R.drawable.face_1)
        .setContentIntent(contentIntent)
        .build();

		_nm.notify(R.string.service_started, notification);
	}
}
