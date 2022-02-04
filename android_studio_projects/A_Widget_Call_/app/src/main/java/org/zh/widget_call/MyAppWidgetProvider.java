package org.zh.widget_call;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

//Android Studio运行时,要把app项目设置中的Launch:默认的Default Activity 修改为Nothing,长按桌面下方出按钮->Widgets->找最下面刚安装(卸载和app一样)
//但没有办法debug?? 点击事件没有？？
public class MyAppWidgetProvider extends AppWidgetProvider
{
	@RequiresApi(api = Build.VERSION_CODES.O)
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		System.out.println("onUpdate被调用");
//		RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.my_widget_layout) ;
//		views.setTextViewText(R.id.textView, date);
//		appWidgetManager.updateAppWidget(appWidgetIds[0], views);


		//不能使用线程来做更新,广播生命周期很短,使用Service(新版本报，不能在Receiver中启动Service)


		//Android 8.0 不再允许后台service/receiver 直接通过startService方式去启动
		//context.startService(new Intent(context,TimerService.class));//这里失败???

		//解决方法，但要求在所在方法上加@RequiresApi(api = Build.VERSION_CODES.O)
		context.startForegroundService(new Intent(context,TimerService.class));
		//报  Didn't find class "androidx.core.app.CoreComponentFactory",
		//解决方法在 app/gradle.build中增加 implementation 'androidx.core:core:1.3.1'

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
