package com.yarin.android.Examples_03_03;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
    MediaPlayer player=null;
    SurfaceView surfaceView=null;
    int position;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button_start = (Button)findViewById(R.id.start);
        Button button_stop = (Button)findViewById(R.id.stop);
        button_start.setOnClickListener(start);
        button_stop.setOnClickListener(stop);

        Button playVideo = (Button)findViewById(R.id.playVideo);
        playVideo.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PlayVideoActivity.class);
                startActivity(intent);
            }
        });
        Button audioChangeButton = (Button)findViewById(R.id.audioChangeButton);
        audioChangeButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AudioActivity.class);
                startActivity(intent);
            }
        });


        Button batteryButton = (Button)findViewById(R.id.batteryButton);
        batteryButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                MainActivity.this.registerReceiver(new BatteryReceiver(),new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            }
        });

    }

    private OnClickListener start = new OnClickListener()
    {
        public void onClick(View v)
        {
            startService(new Intent("com.yarin.Android.MUSIC"));
            // Activity01.this.bindService(new Intent("com.yarin.Android.MUSIC"), conn, flags);
        }
    };
    private OnClickListener stop = new OnClickListener()
    {
        public void onClick(View v)
        {
            stopService(new Intent("com.yarin.Android.MUSIC"));

//             unbindService();
//             bindService(service, conn, flags)
        }
    };
}