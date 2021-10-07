package com.yarin.android.Examples_03_03;  
 
import java.io.File;
import java.io.IOException;

import android.app.Service;  
import android.content.Context;
import android.content.Intent;  
import android.media.MediaPlayer;  
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.IBinder;  
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
 
public class RecorderAudioService extends Service  
{  
 
    public IBinder onBind(Intent arg0)  
    {  
        return null;  
    }  
 
	public void onCreate() {
		super.onCreate();
		Log.w("RecorderService","-------------onCreate");
		
		TelephonyManager telManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		telManager.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);
	}
	class MyPhoneStateListener extends PhoneStateListener
	{
		private String incomingNumber=null;
		private MediaRecorder recorder=null;
		private boolean isRecorder=false;
		public void onCallStateChanged(int state, String incomingNumber) 
		{
				super.onCallStateChanged(state, incomingNumber);
				try {
					
					switch (state)
					{
						case TelephonyManager.CALL_STATE_IDLE:
							if(isRecorder)
							{
								recorder.stop();
								recorder.release();
								isRecorder=false;
								Log.w("RecorderService","-------------CALL_STATE_IDLE");
							}
							break;
						case TelephonyManager.CALL_STATE_OFFHOOK://�ӵ绰ʱ
							Log.w("RecorderService","-------------CALL_STATE_OFFHOOK");
							isRecorder=true;
							recorder=new MediaRecorder(); //�ĵ�����ͼ, �κ�ʱ�����ʹ��reset()������initial״̬
							recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//����˷��ռ�����
							recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
							recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//3gp
							recorder.setOutputFile(getCacheDir().getAbsolutePath()+System.currentTimeMillis()+"recorder.arm.3gp");//����OnDestroyʱɾ��
							recorder.prepare();
							recorder.start();
							
							break;
						case TelephonyManager.CALL_STATE_RINGING://���绰ʱ
							Log.w("RecorderService","------------CALL_STATE_RINGING");
							this.incomingNumber=incomingNumber;
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	public void onStart(Intent intent, int startId)  
    {  
        //super.onStart(intent, startId);  
    }  
 
    public void onDestroy()  
    {  
        super.onDestroy();  
        File dir=new File(getCacheDir().getAbsolutePath());
        for (File file : dir.listFiles())
        	file.delete();
    }  
} 