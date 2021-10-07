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
							   	text.setText("����״̬");
							   break;
						   	case WifiManager.WIFI_STATE_ENABLING:
								text.setText("��������״̬");
							   break;   
							case WifiManager.WIFI_STATE_DISABLED:
								text.setText("����״̬");
							   break;
							case WifiManager.WIFI_STATE_DISABLING:
								text.setText("���ڽ���״̬");
							   break;
							case WifiManager.WIFI_STATE_UNKNOWN:
								text.setText("δ֪״̬");
							   break;
							default:
								text.setText("����״̬");
								 break;
						}
					}
				});
       enableWifi= (Button)  this.findViewById(R.id.enableWifiState);
       enableWifi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) 
			{
				WifiManager wifiManager=(WifiManager)WiFiActivity.this.getSystemService(Service.WIFI_SERVICE);
				wifiManager.setWifiEnabled(true);
				text.setText("��������״̬");
			}
		});
       
       disableWifi= (Button)  this.findViewById(R.id.disableWifiState);
       disableWifi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) 
			{
				WifiManager wifiManager=(WifiManager)WiFiActivity.this.getSystemService(Service.WIFI_SERVICE);
				wifiManager.setWifiEnabled(false);
				text.setText("���ý���״̬");
			}
		});
        
        
        
      
     
    }
    
}
