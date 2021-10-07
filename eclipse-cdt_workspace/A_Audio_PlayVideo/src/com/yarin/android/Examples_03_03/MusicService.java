package com.yarin.android.Examples_03_03;  
 
import java.io.IOException;

import android.app.Service;  
import android.content.Context;
import android.content.Intent;  
import android.media.MediaPlayer;  
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;  
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
 
public class MusicService extends Service  
{  
    //MediaPlayer����  
    private MediaPlayer player=null;  
 
    public IBinder onBind(Intent arg0)  
    {  
        return null;  
    }  
 
	public void onCreate() {
		super.onCreate();
		player= MediaPlayer.create(this, R.raw.ring); //���д���
		
//		player= new MediaPlayer();
//		try {
//			player.setDataSource(Environment.getExternalStorageDirectory()+"/ring.mp3");//�ļ�ϵͳ��
//		} catch ( Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
	
	
	public void onStart(Intent intent, int startId)  
    {  
        super.onStart(intent, startId);  
        //player= MediaPlayer.create(this, R.raw.test);//bug, 
        try {
			player.prepare();
		} catch ( Exception e) 
		{
			e.printStackTrace();
		} 
        player.start();  
       // stopSelf();//����onDestroy()
    }  
 
    public void onDestroy()  
    {  
        super.onDestroy();  
        //ֹͣ����--ֹͣService  
        player.stop();
        player.release();//new MediaPlayer();
        /*
        player.pause();
        player.seekTo(sec);
        player.reset();
        player.getDuration();
        player.getCurrentPosition();
        */
        
    }  
} 