package com.example.a_real_android;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class LocationActivity extends Activity
{
	TextView textView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);

		textView =(TextView)this.findViewById(R.id.textView1);

		LocationManager manager= (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		StringBuffer strPrivoder=new StringBuffer();
		strPrivoder.append("所有的 provider有:");

		List<String> all=manager.getAllProviders();//所有的 provider有,真上测试的有network,passive,gps三个
		for (Iterator iterator = all.iterator(); iterator.hasNext();)
		{
			strPrivoder.append( iterator.next()).append(",");
		}
		textView.setText(strPrivoder.toString());
		//动态申请权限
		if(ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(LocationActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
		}
		//模拟器可测试要send,真机测试要到室外才有信号,闪表示正在请求信号,请求到就不闪了
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 20, //3秒后或大于20米 做 一更次更新(通知listener)
				new LocationListener()
				{ //使用GPS做Provider,也可使用NETWORK_PROVIDER,
					//过时Android Q 及以上不会调用
					public void onStatusChanged(String provider, int status, Bundle extras) {
						Log.w("Location---","in GPS_PROVIDER onStatusChanged:"+provider);
					}
					public void onProviderEnabled(String provider) {
						textView.setText(textView.getText()+",GPS_PROVIDER中的被启用是:"+provider);
					}
					public void onProviderDisabled(String provider) {
						textView.setText(textView.getText()+",GPS_PROVIDER中的被禁用的是:"+provider);
					}

					public void onLocationChanged(Location location)
					{
						StringBuffer res=new StringBuffer();
						res.append("纬度:").append(location.getLatitude())// 纬度
								.append(",纬度:").append(location.getLongitude());//经度
						textView.setText(textView.getText()+",GPS定位:"+res.toString());
					}
				});

		if( manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))//移动网络开启也是禁用的,wifi网络无代理未测
			textView.setText(textView.getText()+",NETWORK_PROVIDER是启用的");
		else
			textView.setText(textView.getText()+",NETWORK_PROVIDER是禁用的");

	        /*
	        Criteria criteria=new Criteria();//查找适合自已需要的 Provider
	        criteria.setAccuracy(Criteria.ACCURACY_FINE);
	        criteria.setAltitudeRequired(true);//要有 海拔高度 信息
	        criteria.setCostAllowed(false);//收费
	        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);//电量使用

	        criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);//方位
	        criteria.setBearingRequired(true);

	        criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);//速度
	        criteria.setSpeedRequired(true);

	        String best= manager.getBestProvider(criteria, true);//最适合provider
	        strPrivoder.append("\n最适合provider是:").append(best);

	        */

//	        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//第一次取不到时返回null
//	        double latitude = location.getLatitude();     //纬度
//	        double longitude = location.getLongitude(); //经度
//	        double altitude =  location.getAltitude();     //海拔
//	        textView.setText(textView.getText()+",最近的位置:latitude="+latitude+",longitude="+longitude+",altitude="+altitude);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
