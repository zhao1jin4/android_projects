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
    //MediaPlayer对象
    private MediaPlayer player=null;

    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        player= MediaPlayer.create(this, R.raw.ring); //包中带的

//		player= new MediaPlayer();
//		try {
//			player.setDataSource(Environment.getExternalStorageDirectory()+"/ring.mp3");//文件系统中
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
        // stopSelf();//调用onDestroy()
    }

    public void onDestroy()
    {
        super.onDestroy();
        //停止音乐--停止Service
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