package com.eoeandroid.broadcastReceiver;

import java.util.Date;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class EoeAndroidReceiver1 extends BroadcastReceiver {
	Context context;
	public static int NOTIFICATION_ID = 21321;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("ERROR+LOG","-------onReceive EoeAndroidReceiver1-------");
		this.context = context;
		Toast.makeText(context, "onReceive EoeAndroidReceiver1__" , Toast.LENGTH_SHORT).show();
		showNotification();//真机MIUI 没有显示notifaction？？？ ,模拟器是有的

	}

	private void showNotification() {
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, ActivityMain.class), 0);

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);


//		Notification notification = new Notification.Builder(context)
//				.setContentTitle("titile")
//				.setContentText("content_在EoeAndroidReceiver1中")
//				// .setLargeIcon( )
//				.setSmallIcon(R.mipmap.ic_launcher)
//				.setContentIntent(contentIntent)//跳到intent
//				.build();

		//通知渠道的ID
		String  id  = "channel_01";
		//用户可以看到的通知渠道的名字
		CharSequence name ="名字";//getString(R.string.app_name)
		//用户可看到的通知描述
		String description = "描述";
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
		notificationManager.createNotificationChannel(notificationChannel);



		Notification notification = new NotificationCompat.Builder(context, id)
				//指定通知的标题内容
				.setContentTitle("This is content title")
				//设置通知的内容
				.setContentText("This is content text")
				//指定通知被创建的时间
				.setWhen(System.currentTimeMillis())
				//设置通知的小图标
				.setSmallIcon(R.drawable.ic_launcher_foreground)
				//设置通知的大图标
				.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ic_launcher_background))
				//添加点击跳转通知跳转
				.setContentIntent(contentIntent)
				//实现点击跳转后关闭通知
				.setAutoCancel(true)
				.build();


		notificationManager.notify(NOTIFICATION_ID, notification);
	}

}
