package com.example.a_real_android;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


import android.app.Activity;
import android.content.Context;
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
	        strPrivoder.append("���е� provider��:");
	        
	        List<String> all=manager.getAllProviders();//���е� provider��,���ϲ��Ե���network,passive,gps����
	        for (Iterator iterator = all.iterator(); iterator.hasNext();) 
	        {
				strPrivoder.append( iterator.next()).append(",");
			}
	        textView.setText(strPrivoder.toString());
	        
	        //ģ�����ɲ���Ҫsend,�������Ҫ����������ź�,����ʾ���������ź�,���󵽾Ͳ�����
	        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 20, //3�������20�� �� һ���θ���(֪ͨlistener)
  					new LocationListener() 
				        { //ʹ��GPS��Provider,Ҳ��ʹ��NETWORK_PROVIDER,
							public void onStatusChanged(String provider, int status, Bundle extras) {
								Log.w("Location---","in GPS_PROVIDER onStatusChanged:"+provider);
							}
							public void onProviderEnabled(String provider) {
								textView.setText(textView.getText()+",GPS_PROVIDER�еı�������:"+provider);
							}
							public void onProviderDisabled(String provider) {
								 textView.setText(textView.getText()+",GPS_PROVIDER�еı����õ���:"+provider);
							}
							
							public void onLocationChanged(Location location) 
							{
								StringBuffer res=new StringBuffer();
								res.append("γ��:").append(location.getLatitude())// γ��
								.append(",γ��:").append(location.getLongitude());//����
								 textView.setText(textView.getText()+",GPS��λ:"+res.toString());
							}
						});
	      
	        if( manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))//�ƶ����翪��Ҳ�ǽ��õ�,wifi�����޴���δ��
	        	 textView.setText(textView.getText()+",NETWORK_PROVIDER�����õ�"); 
	        else
	        	 textView.setText(textView.getText()+",NETWORK_PROVIDER�ǽ��õ�"); 
	       
	        /*
	        Criteria criteria=new Criteria();//�����ʺ�������Ҫ�� Provider
	        criteria.setAccuracy(Criteria.ACCURACY_FINE);
	        criteria.setAltitudeRequired(true);//Ҫ�� ���θ߶� ��Ϣ
	        criteria.setCostAllowed(false);//�շ�
	        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);//����ʹ��
	        
	        criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);//��λ
	        criteria.setBearingRequired(true);
	        
	        criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);//�ٶ�
	        criteria.setSpeedRequired(true);
	        
	        String best= manager.getBestProvider(criteria, true);//���ʺ�provider
	        strPrivoder.append("\n���ʺ�provider��:").append(best);
	        
	        */
	        
//	        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//��һ��ȡ����ʱ����null
//	        double latitude = location.getLatitude();     //γ��
//	        double longitude = location.getLongitude(); //����
//	        double altitude =  location.getAltitude();     //����
//	        textView.setText(textView.getText()+",�����λ��:latitude="+latitude+",longitude="+longitude+",altitude="+altitude);
	        
	    }
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) 
	    {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        return true;
	    }
	    
}
