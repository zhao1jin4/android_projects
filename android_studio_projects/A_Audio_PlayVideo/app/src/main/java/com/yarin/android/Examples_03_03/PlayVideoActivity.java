package com.yarin.android.Examples_03_03;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
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
		surfaceView.getHolder().setFixedSize(176, 144);//设置分辨率
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

	//当有一个新的Activity在前面时(如有电话打进来),SurfaceView会被销毁,
	//当电话结束时再回来,SurfaceView会被自动创建,但可能会比较晚,为了在SurfaceView创建后,再开始做事,
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
		mediaPlayer.reset();//重置为初始状态//After calling this method, you will have to initialize it again by setting the data source and calling prepare().

//		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//8.0过时
		AudioAttributes attributes=new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MOVIE).build();//CONTENT_TYPE_MUSIC,CONTENT_TYPE_MOVIE
		mediaPlayer.setAudioAttributes(attributes);

		/* 设置Video影片以SurfaceHolder播放 */
		mediaPlayer.setDisplay(surfaceView.getHolder());

		//Toast.makeText(this,"mp4_dir:"+Environment.getExternalStorageDirectory(),Toast.LENGTH_LONG).show();
		// Environment.getExternalStorageDirectory() = /storage/emulated/0
		// /storage/sdcard0/
//		File videoFile = new File("/storage/sdcard0/", "movie.mp4");//模拟器是在/mnt/sdcard
//		mediaPlayer.setDataSource(videoFile.getAbsolutePath());
		//mediaPlayer.setDataSource(new FileInputStream(new File("/storage/sdcard0/bluetooth/movie.mp4.mp4")).getFD());//机内部存储


		AssetFileDescriptor fd=getResources().openRawResourceFd(R.raw.movie);
		mediaPlayer.setDataSource(fd.getFileDescriptor(),fd.getStartOffset(),fd.getLength());

		mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//构造器实例化MediaPlayer时这样调用
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
 				mediaPlayer.start();
			}
		});
		mediaPlayer.prepare();//MediaPlayer.create()的方式不要再调用prepare()
		mediaPlayer.start();//播放
	}
}