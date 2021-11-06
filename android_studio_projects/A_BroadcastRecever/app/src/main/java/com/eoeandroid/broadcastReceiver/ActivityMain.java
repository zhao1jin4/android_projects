package com.eoeandroid.broadcastReceiver;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.core.app.ActivityCompat;

public class ActivityMain extends Activity {

	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	static final String ACTION_1 = "com.eoeandroid.action.NEW_BROADCAST_1";
	static final String ACTION_2 = "com.eoeandroid.action.NEW_BROADCAST_2";

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		//动态申请权限
		if(ActivityCompat.checkSelfPermission(ActivityMain.this,Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED ||
		   ActivityCompat.checkSelfPermission(ActivityMain.this,Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(ActivityMain.this,new String[]{Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS},1);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM0, 0, "显示Notification");
		menu.add(0, ITEM1, 0, "清除Notification");
		menu.findItem(ITEM1);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case ITEM0:
				actionClickMenuItem1();
				break;
			case ITEM1:
				actionClickMenuItem2();
				break;

		}
		return true;
	}


	private void actionClickMenuItem1() {
//		Intent intent = new Intent(ACTION_1);//新版本必须是显示指定类名
		Intent intent = new Intent(ActivityMain.this,EoeAndroidReceiver1.class);

		sendBroadcast(intent);
		//sendOrderedBroadcast(intent, receiverPermission);//如何设置优先级????????????android:permission="",

	}


	private void actionClickMenuItem2() {
//		Intent intent = new Intent(ACTION_2);
		Intent intent = new Intent(ActivityMain.this,EoeAndroidReceiver2.class);

		sendBroadcast(intent);

	}

}