package com.yarin.android.Examples_03_03;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class AudioActivity extends Activity {
    AudioManager audioMgr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_ajust);


        audioMgr=(AudioManager)this.getSystemService(Context.AUDIO_SERVICE);


        Button increaseVolumeButton = (Button)findViewById(R.id.increaseVolumeButton);
        Button deincreaseVolumnButton = (Button)findViewById(R.id.deincreaseVolumnButton);
        Button silentButton = (Button)findViewById(R.id.silentButton);
        Button normalButton = (Button)findViewById(R.id.normalButton);

        increaseVolumeButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                audioMgr.adjustVolume(AudioManager.ADJUST_RAISE, 0);//增大音量

            }
        });
        deincreaseVolumnButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                audioMgr.adjustVolume(AudioManager.ADJUST_LOWER, 0);//减小音量
            }
        });
        silentButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
//                NotificationManager    mNotificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
//                        && !mNotificationManager.isNotificationPolicyAccessGranted()) {
//                    Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);//跳到一个Do Not Distrub access设置页面
//                    AudioActivity.this.startActivity(intent);
//                }
                audioMgr.setRingerMode(AudioManager.RINGER_MODE_SILENT);//静音,只是图标有变化,无效果
                //新版本报 java.lang.SecurityException: Not allowed to change Do Not Disturb(打扰) state
            }
        });
        normalButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                audioMgr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//音量正常
            }
        });
//     audioMgr.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);//振动

    }

}
