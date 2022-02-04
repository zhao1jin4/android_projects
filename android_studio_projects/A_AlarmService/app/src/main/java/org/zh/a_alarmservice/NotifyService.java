
package org.zh.a_alarmservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

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

	public void onCreate() {
		Log.e(TAG, "============> TestService.onCreate");
		_nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification();
	}
	public int onStartCommand( Intent intent, int flags,  int startId){
		int res=super.onStartCommand(intent,flags,startId);
		Log.e(TAG, "============> TestService.onStart");
		return res;
	}
	//	public void onStart(Intent intent, int startId) {//过时
//		Log.e(TAG, "============> TestService.onStart");
//	}
	public boolean onUnbind(Intent i) {
		Log.e(TAG, "============> TestService.onUnbind");
		Toast.makeText(NotifyService.this, "onUnbind" , Toast.LENGTH_SHORT).show();
		return false;
	}

	public void onRebind(Intent i) {
		Log.e(TAG, "============> TestService.onRebind");
		Toast.makeText(NotifyService.this, "onRebind" , Toast.LENGTH_SHORT).show();

	}


	public void onDestroy() {
		_nm.cancel(R.string.service_started);
		Toast.makeText(NotifyService.this, "onDestroy" , Toast.LENGTH_SHORT).show();

		Log.e(TAG, "============> TestService.onDestroy");
	}

	public void showNotification() {
		//通知渠道的ID
		String  id  = "channel_01";
		//用户可以看到的通知渠道的名字
		CharSequence name = getString(R.string.app_name);
		//用户可看到的通知描述
		String description = getString(R.string.hello);
		//构建NotificationChannel实例
		NotificationChannel notificationChannel = new NotificationChannel(id,name,NotificationManager.IMPORTANCE_HIGH);
		//配置通知渠道的属性
		 notificationChannel.setDescription(description);
		//设置通知出现时的闪光灯
		notificationChannel.enableLights(true);
		notificationChannel.setLightColor(Color.RED);
		//设置通知出现时的震动
		notificationChannel.enableVibration(true);
		notificationChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,100});
		//在notificationManager中创建通知渠道
		_nm.createNotificationChannel(notificationChannel);

		//Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE be specified when creating a PendingIntent.
		//PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_IMMUTABLE);


		Notification notification = new NotificationCompat.Builder(NotifyService.this, id)
		//指定通知的标题内容
		.setContentTitle("This is content title")
				//设置通知的内容
				.setContentText("This is content text")
				//指定通知被创建的时间
				.setWhen(System.currentTimeMillis())
				//设置通知的小图标
				.setSmallIcon(R.drawable.ic_launcher_foreground)
				//设置通知的大图标
				.setLargeIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher_background))
				//添加点击跳转通知跳转
				 .setContentIntent(contentIntent)
				//实现点击跳转后关闭通知
				.setAutoCancel(true)
				.build();






//		 Notification notification = new Notification.Builder(NotifyService.this)
//		Notification notification = new Notification.Builder(NotifyService.this,"default")
//         .setContentTitle("Test Service")
//         .setContentText("Service started")
//         .setSmallIcon(R.mipmap.ic_launcher)
//         //.setLargeIcon()
//         .build();

		//Notification notification = new Notification(R.mipmap.ic_launcher,"Service started", System.currentTimeMillis());


		// must set this for content view, or will throw a exception
//		notification.setLatestEventInfo(this, "Test Service",
//				"Service started", contentIntent);

		_nm.notify(R.string.service_started, notification);
		
		//---------------------------------------------
//		Toast.makeText(this, "Service connected", Toast.LENGTH_SHORT).show(); 
	}
}
