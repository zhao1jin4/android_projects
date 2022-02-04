package org.zh.widget_call;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.widget.RemoteViews;

public class TimerService extends Service
{
	Timer timer;
	public IBinder onBind(Intent arg0) {
		return null;
	}
	public void onDestroy() {
		System.out.println("========TimerService onDestroy");
		super.onDestroy();
		timer.cancel();
		timer=null;
	}
	public void onCreate()
	{
		System.out.println("========TimerService onCreate");
		super.onCreate();
		timer=new Timer();
		timer.schedule(new TimeUpdateTask(), 0, 1000);
	}
	class TimeUpdateTask extends TimerTask
	{
		public void run()
		{
			System.out.println("========TimeUpdateTask run");

			//SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat format =DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
			String date=format.format(new Date());

			//RemoteViews给我们提供了一种可以在其他进程中生成View并进行更新的机制
			RemoteViews views=new RemoteViews(getPackageName(),R.layout.my_widget_layout) ;
			views.setTextViewText(R.id.textView, date);

			System.out.println("========TimeUpdateTask run 1111 ");
			Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:13011114444"));
			PendingIntent pendingIntent=  PendingIntent.getActivity(getApplicationContext(),123,intent, 0);
			views.setOnClickPendingIntent(R.id.textView, pendingIntent);//处理单击事件,无效？？
			//views.setPendingIntentTemplate(R.id.textView, pendingIntent);//也没测试出效果
			System.out.println("========TimeUpdateTask run 2222");
			AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(getApplicationContext());
			appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(),MyAppWidgetProvider.class), views);//同类型的所有都会更新
		}
	}
}
