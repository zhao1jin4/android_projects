
package com.iceskysl.TestServiceHolder;
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

import androidx.core.app.NotificationCompat;

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
	public int onStartCommand( Intent intent, int flags,  int startId){
		int res=super.onStartCommand(intent,flags,startId);
		Log.e(TAG, "============> TestService.onStart");
		return res;
	}
//	public void onStart(Intent intent, int startId) {
//		Log.e(TAG, "============> TestService.onStart");
//	}

	public void onDestroy() {
		_nm.cancel(R.string.service_started);
		Log.e(TAG, "============> TestService.onDestroy");
	}

	public void showNotification()
	{

		//通知渠道的ID
		String  id  = "channel_01";
		//用户可以看到的通知渠道的名字
		CharSequence name = getString(R.string.app_name);
		//用户可看到的通知描述
		String description = getString(R.string.service_started);
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


		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, TestService.class), 0);

		Notification notification = new NotificationCompat.Builder(TestService.this, id)
				//指定通知的标题内容
				.setContentTitle("titile_Service started")
				//设置通知的内容
				.setContentText("content_Service started")
				//指定通知被创建的时间
				.setWhen(System.currentTimeMillis())
				//设置通知的小图标
				.setSmallIcon(R.drawable.face_1)
				//设置通知的大图标
				.setLargeIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher_background))
				//添加点击跳转通知跳转
				.setContentIntent(contentIntent)
				//实现点击跳转后关闭通知
				.setAutoCancel(true)
				.build();

		_nm.notify(R.string.service_started, notification);
	}
}
