package com.example.a_real_android;


import android.app.Activity;
import android.app.Service;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WiFiActivity extends Activity
{
	Button getwifi;
	Button enableWifi;
	Button disableWifi;
	TextView text;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi);

		text= (TextView)this.findViewById(R.id.textViewWifi);
		getwifi= (Button)this.findViewById(R.id.getWifiState);
		getwifi.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				WifiManager wifiManager=(WifiManager)WiFiActivity.this.getSystemService(Service.WIFI_SERVICE);
				int state=wifiManager.getWifiState();
				switch (state)
				{
					case WifiManager.WIFI_STATE_ENABLED:
						text.setText("启用状态");
						break;
					case WifiManager.WIFI_STATE_ENABLING:
						text.setText("正在启用状态");
						break;
					case WifiManager.WIFI_STATE_DISABLED:
						text.setText("禁用状态");
						break;
					case WifiManager.WIFI_STATE_DISABLING:
						text.setText("正在禁用状态");
						break;
					case WifiManager.WIFI_STATE_UNKNOWN:
						text.setText("未知状态");
						break;
					default:
						text.setText("其它状态");
						break;
				}
			}
		});
		enableWifi= (Button)  this.findViewById(R.id.enableWifiState);
		enableWifi.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				WifiManager wifiManager=(WifiManager)WiFiActivity.this.getSystemService(Service.WIFI_SERVICE);
				wifiManager.setWifiEnabled(true);//过时 Q及以上版本不可以修改wifi状态 ，总是返回false
				// api-29开始过时，官网api没有说替代方案，不允许，为了安全吧
				text.setText("设置启用状态");
			}
		});

		disableWifi= (Button)  this.findViewById(R.id.disableWifiState);
		disableWifi.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				WifiManager wifiManager=(WifiManager)WiFiActivity.this.getSystemService(Service.WIFI_SERVICE);
				wifiManager.setWifiEnabled(false);//过时 Q及以上版本不可以修改wifi状态 ，总是返回false
				text.setText("设置禁用状态");
			}
		});





	}

}
