package com.eoeandroid.toast_and_notification;

import com.eoeandroid.toast_and_notification.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

public class ActivityMainNotification extends Activity {
	private static int NOTIFICATIONS_ID = 12345;
	private NotificationManager mNotificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		Button button;

		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		button = (Button) findViewById(R.id.sun_1);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setWeather("ticker晴空万里", "T天气预报", "C晴空万里", R.drawable.sun);
			}
		});

		button = (Button) findViewById(R.id.cloudy_1);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setWeather("ticker阴云密布", "T天气预报", "C阴云密布", R.drawable.cloudy);
			}
		});

		button = (Button) findViewById(R.id.rain_1);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setWeather("ticker大雨连绵", "T天气预报", "C大雨连绵", R.drawable.rain);
			}
		});

		button = (Button) findViewById(R.id.defaultSound);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setDefault(Notification.DEFAULT_SOUND);
			}
		});

		button = (Button) findViewById(R.id.defaultVibrate);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setDefault(Notification.DEFAULT_VIBRATE);
			}
		});

		button = (Button) findViewById(R.id.defaultAll);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setDefault(Notification.DEFAULT_ALL);
			}
		});

		button = (Button) findViewById(R.id.clear);
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				mNotificationManager.cancel(NOTIFICATIONS_ID);
			}
		});

	}

	private void setWeather(String tickerText, String title, String content,
							int drawable) {
/*过时的方式

		Notification notification = new Notification(drawable, tickerText, System.currentTimeMillis());

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, ActivityMain.class), 0);

//		notification.setLatestEventInfo(this, title, content, contentIntent);
//
//		mNotificationManager.notify(NOTIFICATIONS_ID, notification);



		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentText(content);
		builder.setContentTitle(title);
		builder.setSmallIcon(R.mipmap.ic_launcher);
		builder.setSubText("secondText");
		builder.setWhen(System.currentTimeMillis() + 300);
		builder.setTicker("Ticker");
		builder.setContentIntent(contentIntent);
		builder.setAutoCancel(true);
		long[] vb = {0, 100, 1000, 100};
		notification=builder.build();
		notification.vibrate=vb;
		notification.ledARGB = Color.GREEN;
		notification.ledOnMS=1000;
		notification.ledOffMS=1000;
*/



		//通知渠道的ID
		String id = "channel_01";
//用户可以看到的通知渠道的名字
		CharSequence name = "我的通道";
//用户可看到的通知描述
		String description ="我的通道描述";
//构建NotificationChannel实例
		NotificationChannel notificationChannel =
				new NotificationChannel(id,name,NotificationManager.IMPORTANCE_HIGH);
//配置通知渠道的属性
		notificationChannel.setDescription(description);
//设置通知出现时的闪光灯
		notificationChannel.enableLights(true);//MIUI没用？？？ shouldShowLights()得到结果
		notificationChannel.setLightColor(Color.RED);
//设置通知出现时的震动
		notificationChannel.enableVibration(true);//MIUI没用？？？ shouldVibrate();得到结果
		notificationChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,100});

//		notificationChannel.getSound();//返回URI

//在notificationManager中创建通知渠道
		mNotificationManager.createNotificationChannel(notificationChannel);


		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, ActivityMain.class), 0);

		Notification notification  = new NotificationCompat.Builder(ActivityMainNotification.this,id)
				//Applicaton的名字不能替代
				.setTicker(tickerText)//没啥用？
				.setSubText("小标题")//没啥用？
				.setCategory("种类")//没啥用？
				.setContentInfo("内容ConentInfo")//没啥用？

				//指定通知的标题内容
				.setContentTitle(title)
				//设置通知的内容
				.setContentText(content)
				//指定通知被创建的时间
				.setWhen(System.currentTimeMillis())
				//设置通知的小图标
				.setSmallIcon(drawable)
				//设置通知的大图标
				.setLargeIcon(BitmapFactory.decodeResource(getResources(),
						drawable))
				//添加点击跳转通知跳转
				.setContentIntent(contentIntent)
				//实现点击跳转后关闭通知
				.setAutoCancel(true)
				.build();



		mNotificationManager.notify(NOTIFICATIONS_ID, notification);
	}

	private void setDefault(int defaults) {

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, ActivityMain.class), 0);

		String title = "天气预报";
		String content = "晴空万里";

		Notification notification = new Notification.Builder(this)
				.setContentTitle(title)
				.setContentText(content)
				// .setLargeIcon( )
				.setSmallIcon(R.drawable.sun)
				.setContentIntent(contentIntent)//跳到intent
				.build();

		notification.defaults = defaults;


		mNotificationManager.notify(NOTIFICATIONS_ID, notification);
	}
}