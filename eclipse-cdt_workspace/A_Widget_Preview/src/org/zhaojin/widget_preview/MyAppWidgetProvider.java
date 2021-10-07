package org.zhaojin.widget_preview;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class MyAppWidgetProvider extends AppWidgetProvider 
{
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) 
	{	
		System.out.println("onUpdate被调用");
//		RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.my_widget_layout) ;
//		views.setTextViewText(R.id.textView, date);
//		appWidgetManager.updateAppWidget(appWidgetIds[0], views);
//		//不能使用线程来做更新,广播生命周期很短,使用Service
		context.startService(new Intent(context,TimerService.class));
	}
	public void onDeleted(Context context, int[] appWidgetIds) //删除时,调用这个方法(android4.1中没有手工删除的方法,是自动的),不用再配置 <receiver> 
	{
		System.out.println("onDelete被调用");
		context.stopService(new Intent(context,TimerService.class));
	}
	public void onDisabled(Context context)//删除最后一个时被调用  (没有测试到什么时候调用,可能要android清资源时)
	{
		System.out.println("onDisabled被调用");
	}
	public void onEnabled(Context context)  //第一次增加时时被调用
	{	
		System.out.println("onEnabled被调用");
	}
}
