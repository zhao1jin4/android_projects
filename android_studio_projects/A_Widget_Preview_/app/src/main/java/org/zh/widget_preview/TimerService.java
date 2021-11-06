package org.zh.widget_preview;

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
		super.onDestroy();
		timer.cancel();
		timer=null;
	}
	public void onCreate()
	{
		super.onCreate();
		timer=new Timer();
		timer.schedule(new TimeUpdateTask(), 0, 1000);
	}
	class TimeUpdateTask extends TimerTask
	{
		public void run()
		{
			//SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat format =DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
			String date=format.format(new Date());

			RemoteViews views=new RemoteViews(getPackageName(),R.layout.my_widget_layout) ;
			views.setTextViewText(R.id.textView, date);

			Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:13011114444"));
			PendingIntent pendingIntent=  PendingIntent.getActivity(getApplicationContext(),123,intent, 0);
			views.setOnClickPendingIntent(R.id.textView, pendingIntent);//处理单击事件

			AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(getApplicationContext());
			appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(),MyAppWidgetProvider.class), views);//同类型的所有都会更新
		}
	}
}
