package com.example.a_googleservice;


import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GeoCoderActivity extends Activity {

	TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoder);
        
        textView=(TextView)this.findViewById(R.id.textView1);
        
        
        
        //  http://maps.googleapis.com/maps/api/geocode/json?address=�Ϻ�&sensor=false  //���ݵ�ַ�龭ΰ��
        //  http://maps.googleapis.com/maps/api/geocode/json?latlng=31.11,121.29&sensor=false  //���ݾ�ΰ�Ȳ��ַ  ,�Ϻ�
        
        //http://maps.googleapis.com/maps/api/geocode/json?address=Winnetka&bounds=34.172684,-118.604794|34.236144,-118.500938&sensor=false
        //bounds=ָ����ΰ�ȵ�����
        
        //  http://maps.googleapis.com/maps/api/geocode/json?address=Toledo&sensor=false&region=es  
        //region���ݹ��Ҵ���
        
        
        
        //ʹ��HttpClient
        
        
        /*
         //������
        Geocoder geo=new Geocoder(this);
        try {
			List<Address> address=geo.getFromLocationName("SFO", 1);
			textView.setText(address.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
