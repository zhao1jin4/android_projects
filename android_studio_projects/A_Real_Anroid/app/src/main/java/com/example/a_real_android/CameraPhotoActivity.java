package com.example.a_real_android;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class CameraPhotoActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

	private static final String TAG = "TakeActivity";
	private SurfaceView surfaceView;
	private Camera camera;
	private boolean preview;

	boolean useFontCamera=false;
	SurfaceCallback fontCallback=new SurfaceCallback(true);
	SurfaceCallback backCallback=new SurfaceCallback(false);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//----------系统界面,可切换前后面的摄像头,但
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri outputFileUri = Uri.fromFile(new File( Environment.getExternalStorageDirectory(),"myImg.png"));//"/storage/sdcard0/myImg.gif"
		intent.putExtra(MediaStore.EXTRA_OUTPUT,outputFileUri );
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

		//---------自定义界面, 切换摄像头失败？？？
        /*
        Window window = getWindow();
    	requestWindowFeature(Window.FEATURE_NO_TITLE);//没有标题,必须在setContentView之前完成
    	window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
    	window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//高亮

        setContentView(R.layout.camera_photo);

        Button switchCamera = (Button) findViewById(R.id.switchCamera);
        switchCamera.setOnClickListener(new Button.OnClickListener()
			        {
			        	public void onClick(View v) {

			        	}
			        });
        Button cameraPhoto = (Button) findViewById(R.id.cameraPhoto);
        cameraPhoto.setOnClickListener(new Button.OnClickListener()
			        {
			        	public void onClick(View v) {
			        		camera.autoFocus(null);//自动对焦
			        		camera.takePicture(null, null, new TakePictureCallback());//处理过的,原始的,压缩
			        	}
			        });

        surfaceView =(SurfaceView)this.findViewById(R.id.cameraPhotoSurfaceView);
        surfaceView.getHolder().setFixedSize(176, 144);	//设置分辨率
        surfaceView.getHolder().addCallback(backCallback);
      */

	}

	protected void onDestroy() {
		super.onDestroy();
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(this, "Image saved", Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Log.w("CameraRecord----","canceled");
			} else {
				Log.w("CameraRecord----","failed");
			}
		}
	}


	private final class SurfaceCallback implements SurfaceHolder.Callback{
		boolean _useFontCamera=false;
		public  SurfaceCallback(boolean _useFontCamera) {
			this._useFontCamera=_useFontCamera;
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		}

		public void surfaceCreated(SurfaceHolder holder) {
			try {

				if (!CameraPhotoActivity.this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
					Toast.makeText(CameraPhotoActivity.this, "本机没有摄像头",Toast.LENGTH_LONG).show();
				}

				//Camera.getNumberOfCameras();
				//CameraInfo cameraInfo = new CameraInfo();
				//if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) //CameraInfo.CAMERA_FACING_FRONT
				if(_useFontCamera)
					camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);//默认是CameraInfo.CAMERA_FACING_BACK
				else
					camera = Camera.open(CameraInfo.CAMERA_FACING_BACK);

				if(camera==null)
				{
					Toast.makeText(CameraPhotoActivity.this, "摄像头不可用",Toast.LENGTH_LONG).show();
					return;
				}

				WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
				Display display = wm.getDefaultDisplay();

				/*
				Camera.Parameters parameters = camera.getParameters();
				//parameters.setPreviewSize(display.getWidth(), display.getHeight());//设置预览照片的大小
				//parameters.setPictureSize(display.getWidth(), display.getHeight());//设置照片的大小
				Point outSize=new Point();
				display.getSize(outSize);
				parameters.setPreviewSize(outSize.x,outSize.y);
				parameters.setPictureSize(outSize.x,outSize.y);
				parameters. setPreviewFpsRange(3,5);//每秒3-5帧
				parameters.setPictureFormat( ImageFormat.JPEG);//设置照片的输出格式
				//parameters.set("jpeg-quality", 85);
				parameters.setJpegQuality(85);//照片质量
				camera.setParameters(parameters);//这里报错???
				*/

				camera.setPreviewDisplay(surfaceView.getHolder());//通过SurfaceView显示取景画面
				camera.startPreview();//开始预览
				preview = true;
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}

		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			if(camera!=null){
				if(preview) camera.stopPreview();
				camera.release();
			}
		}

	}

	private final class TakePictureCallback implements PictureCallback{
		public void onPictureTaken(byte[] data, Camera camera) {
			try {
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				//File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
				File file = new File("/storage/sdcard0/bluetooth", System.currentTimeMillis()+".jpg");
				FileOutputStream outStream = new FileOutputStream(file);
				bitmap.compress(CompressFormat.JPEG, 100, outStream);//压缩输出,写出到文件
				outStream.close();
				camera.stopPreview();
				camera.startPreview();//开始预览
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}
		}
	}

}