package org.zhaojin.widget_preview;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class MyAppWidgetProvider extends AppWidgetProvider 
{
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) 
	{	
		System.out.println("onUpdate������");
//		RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.my_widget_layout) ;
//		views.setTextViewText(R.id.textView, date);
//		appWidgetManager.updateAppWidget(appWidgetIds[0], views);
//		//����ʹ���߳���������,�㲥�������ںܶ�,ʹ��Service
		context.startService(new Intent(context,TimerService.class));
	}
	public void onDeleted(Context context, int[] appWidgetIds) //ɾ��ʱ,�����������(android4.1��û���ֹ�ɾ���ķ���,���Զ���),���������� <receiver> 
	{
		System.out.println("onDelete������");
		context.stopService(new Intent(context,TimerService.class));
	}
	public void onDisabled(Context context)//ɾ�����һ��ʱ������  (û�в��Ե�ʲôʱ�����,����Ҫandroid����Դʱ)
	{
		System.out.println("onDisabled������");
	}
	public void onEnabled(Context context)  //��һ������ʱʱ������
	{	
		System.out.println("onEnabled������");
	}
}
