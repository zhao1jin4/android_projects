package com.example.a_real_android;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CameraRecordActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	
	private static final String TAG = "MainActivity";
    private SurfaceView surfaceView;
    private MediaRecorder mediaRecorder;
    private boolean record;
    boolean useFontCamera=false;
    Button stopButton ;
    Button recordButton;
    Button  switchCamera ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_record);
        /*
      //----------ϵͳ����,���л�ǰ���������ͷ   ,û������??
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        Uri outputFileUri = Uri.fromFile(new File( Environment.getExternalStorageDirectory(),"myMovie.mp4"));//"/storage/sdcard0/myMovie.mp4"
        intent.putExtra(MediaStore.EXTRA_OUTPUT,outputFileUri );
		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // ��Χ0-1
		intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,60);//1 minute
		startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
		 */ 
       
        //---------�Զ������,ֻ�к��������ͷ���л�����ͷʧ�ܣ�����
      
        mediaRecorder = new MediaRecorder();
        surfaceView = (SurfaceView) this.findViewById(R.id.recordVideoSurfaceView);
       //��������Surface��ά���Լ��Ļ����������ǵȴ���Ļ����Ⱦ���潫�������͵��û���ǰ
       //this.surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//android 3.0�汾��ǰҪ����
        this.surfaceView.getHolder().setFixedSize(320, 240);//���÷ֱ���

        ButtonClickListener listener = new ButtonClickListener();
         stopButton = (Button) this.findViewById(R.id.stopRecord);
         recordButton = (Button) this.findViewById(R.id.cameraRecord);
        stopButton.setOnClickListener(listener);
        recordButton.setOnClickListener(listener);   
      
        switchCamera = (Button) this.findViewById(R.id.switchCamera);
        switchCamera.setOnClickListener(new Button.OnClickListener() 
        {
        	public void onClick(View v) {
        		
        		useFontCamera = (! useFontCamera );
        		mediaRecorder.stop();
        		Camera camera ;
        		if(useFontCamera)
        		{
        			camera=Camera.open(CameraInfo.CAMERA_FACING_FRONT);//Ĭ����CameraInfo.CAMERA_FACING_BACK)
        			 
        		}else
        		{ 
        			camera=Camera.open(CameraInfo.CAMERA_FACING_BACK);
        		}
        		camera.unlock();
        		
        		mediaRecorder.setCamera(camera);
        		mediaRecorder.start();//����
        	}
        }); 
    	stopButton.setEnabled(false);
		switchCamera.setEnabled(false);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved", Toast.LENGTH_LONG).show();
                
            } else if (resultCode == RESULT_CANCELED) {
            	Log.w("CameraRecord----","canceled");
            } else {
            	Log.w("CameraRecord----","failed");
            }
        }
    }
    @Override
	protected void onDestroy() {
    	super.onDestroy();
		mediaRecorder.stop();
    	mediaRecorder.release();
	}

	private final class ButtonClickListener implements View.OnClickListener{
		public void onClick(View v) {
//			if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//				Toast.makeText(CameraRecordActivity.this, "SDCard�����ڻ���д����", 1).show();
//				return ;
//			}
			try {
				switch (v.getId()) {
				case R.id.cameraRecord:
					recordButton.setEnabled(false);
					stopButton.setEnabled(true);
					switchCamera.setEnabled(true);
					 
					mediaRecorder.reset();
					mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); //��������ɼ���Ƶ
					mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); 
					mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mediaRecorder.setVideoSize(320, 240);
					mediaRecorder.setVideoFrameRate(3); //ÿ��3֡
					mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H263); //������Ƶ���뷽ʽ
					mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					//File videoFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".3gp");
					File videoFile = new File("/storage/sdcard0/bluetooth", System.currentTimeMillis()+".3gp");
					mediaRecorder.setOutputFile(videoFile.getAbsolutePath());
					mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
					mediaRecorder.prepare();//Ԥ��׼��
					mediaRecorder.start();//��ʼ��¼
					record = true;
					break;

				case R.id.stopRecord:
					recordButton.setEnabled(true);
					switchCamera.setEnabled(false);
					stopButton.setEnabled(false);
					
					if(record){
						mediaRecorder.stop();
						mediaRecorder.release();
						record = false;
					}
					break;
				}
			} catch (Exception e) {
				Toast.makeText(CameraRecordActivity.this, "��¼ʧ��", 1).show();
				Log.e(TAG, e.toString());
				e.printStackTrace();
			}
		}
    	
    }
}