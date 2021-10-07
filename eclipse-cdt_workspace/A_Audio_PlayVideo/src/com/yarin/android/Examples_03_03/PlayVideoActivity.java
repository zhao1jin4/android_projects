package com.yarin.android.Examples_03_03;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PlayVideoActivity extends Activity {
	private static final String TAG = "PlayActivity";
    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private int position;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video);
        
        
        surfaceView = (SurfaceView) this.findViewById(R.id.playVideoSurfaceView);
        surfaceView.getHolder().setFixedSize(176, 144);//���÷ֱ���
        //surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//deprecated,ignored, this value is set automatically when needed.
        surfaceView.getHolder().addCallback(new SurfaceCallback());
        
        //mediaPlayer= MediaPlayer.create(this, R.raw.movie);//move.mp4 is OK, prepare() will already have been called and must not be called again. 
       //mediaPlayer= MediaPlayer.create(this, R.raw.movie);//move.ogg return null????
        mediaPlayer =   new MediaPlayer();
        ButtonClickListener listener = new ButtonClickListener();
        ImageButton playButton = (ImageButton)this.findViewById(R.id.play);
        ImageButton pauseButton = (ImageButton)this.findViewById(R.id.pause);
        ImageButton resetButton = (ImageButton)this.findViewById(R.id.reset);
        ImageButton stopButton = (ImageButton) this.findViewById(R.id.stop);
        playButton.setOnClickListener(listener);
        pauseButton.setOnClickListener(listener);
        resetButton.setOnClickListener(listener);
        stopButton.setOnClickListener(listener);
    }
    
	//����һ���µ�Activity��ǰ��ʱ(���е绰�����),SurfaceView�ᱻ����,
   	//���绰����ʱ�ٻ���,SurfaceView�ᱻ�Զ�����,�����ܻ�Ƚ���,Ϊ����SurfaceView������,�ٿ�ʼ����,
    private final class SurfaceCallback implements SurfaceHolder.Callback{
		public void surfaceCreated(SurfaceHolder holder) {
			try {
			if(position>0 ){
					play();
					mediaPlayer.seekTo(position);
					position = 0;
			}else
				play();
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}
		}
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
		}
		public void surfaceDestroyed(SurfaceHolder holder) {
			if(mediaPlayer.isPlaying()){
				position = mediaPlayer.getCurrentPosition();
				mediaPlayer.stop();
			}
		}    	
    }

	private final class ButtonClickListener implements View.OnClickListener{
		public void onClick(View v) {
			try {
				switch (v.getId()) {
				case R.id.play:
					play();					
					break;

				case R.id.pause:
					if(mediaPlayer.isPlaying()){
						mediaPlayer.pause();
					}else{
						mediaPlayer.start();
					}
					break;
				case R.id.reset:
					if(mediaPlayer.isPlaying()){
						mediaPlayer.seekTo(0);
					}else{
						play();
					}
					break;
				case R.id.stop:
					if(mediaPlayer.isPlaying()) mediaPlayer.stop();
					break;
				}
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}
		}
    }

	private void play() throws IOException 
	{
		mediaPlayer.reset();//����Ϊ��ʼ״̬//After calling this method, you will have to initialize it again by setting the data source and calling prepare().
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		/* ����VideoӰƬ��SurfaceHolder���� */
		mediaPlayer.setDisplay(surfaceView.getHolder());

		File videoFile = new File(Environment.getExternalStorageDirectory(), "movie.mp4");//ģ��������/mnt/sdcard
		
		mediaPlayer.setDataSource(videoFile.getAbsolutePath());
		//mediaPlayer.setDataSource(new FileInputStream(new File("/storage/sdcard0/bluetooth/movie.mp4.mp4")).getFD());//���ڲ��洢
		
		mediaPlayer.prepare();//MediaPlayer.create()�ķ�ʽ��Ҫ�ٵ���prepare()
		mediaPlayer.start();//���� OK
	}
}