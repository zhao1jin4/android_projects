package com.yarin.android.Examples_03_03;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

public class BatteryReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent)
	{
		if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
		{
			int level=intent.getIntExtra("level",0);
			int scale=intent.getIntExtra("scale",0);

			AlertDialog.Builder builder=	new AlertDialog.Builder(context)
					.setMessage("电量为:"+((level+0.0)/scale *100)+"%")
					.setNegativeButton("关闭",new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			builder.create();
			builder.show();

			//context.registerReceiver(new BatteryReceiver(),new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		}
	}
}
