package org.zhaojin.a_safeprotect;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	String TAG=this.getClass().getName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button intervalStartServiceButton=(Button)this.findViewById(R.id.intervalStartServiceButton);
		intervalStartServiceButton.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				Context context=MainActivity.this;
				 
				Intent intent = new Intent(context, Alarmreceiver.class);
				intent.setAction("alarm.action");
				PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
				long firstime = SystemClock.elapsedRealtime();
				AlarmManager am = (AlarmManager) context .getSystemService(Context.ALARM_SERVICE);

				// 20秒一个周期，不停的发送广播
				am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 20 * 1000, sender);
			}
		});
		
		
		Button poweroffButton=(Button)this.findViewById(R.id.poweroffButton);
		poweroffButton.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent intent = new Intent();  
				intent.setAction(Intent.ACTION_SHUTDOWN);
				startActivity(intent);
			}
		});
		
		
		Button rebootButton=(Button)this.findViewById(R.id.rebootButton);
		rebootButton.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
//				PowerManager pm = (PowerManager)MainActivity.this.getSystemService(Context.POWER_SERVICE);
//				pm.reboot("手动测试 重启");
				
				Intent intent = new Intent();  
				intent.setAction(Intent.ACTION_REBOOT);
				startActivity(intent);
				
			}
		});
	}
 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) { //按手机上的menu菜单时
		Log.i(TAG, "onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {  //按手机上的menu菜单时 响应 第1次
		 switch (item.getItemId()) 
		 {
			case R.id.menuExit:
				Toast.makeText(MainActivity.this, "onMenuItemSelected_被单击了菜单项__"+item.getTitle(), Toast.LENGTH_SHORT).show();
				//System.exit(0);
				break;
			default:
				break;
		}
		//return true;
		return super.onMenuItemSelected(featureId, item);//会响应 onOptionsItemSelected  
	}
	 
	public boolean onOptionsItemSelected(MenuItem item) { //按手机上的menu菜单时 响应 第2次
		 if(item.getItemId()==R.id.menuExit)
		 {
			 Toast.makeText(MainActivity.this, "onOptionsItemSelected_被单击了菜单项__"+item.getTitle(), Toast.LENGTH_SHORT).show();
			 stopService(new Intent(this, TestService.class));//如不调用,退出也不会停止
			 //System.exit(0);//不会调用onDestroy方法
			 this.finish();
		 }
		 return true;
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(MainActivity.this, "onDestroy",Toast.LENGTH_SHORT).show();
	}
	
	 //XML 配置方式 为什么 加 android:onClick="myOnSubMenuItemClicked"  不行的???
	public boolean onExitButtonClicked(Menu menu) {
		System.exit(0);
		return true;
	}
}
