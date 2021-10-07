package com.yarin.android.Examples_03_03;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
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
                audioMgr.setRingerMode(AudioManager.RINGER_MODE_SILENT);//静音,只是图标有变化,无效果
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
