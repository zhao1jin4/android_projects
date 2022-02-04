package com.example.a_real_android.camera2;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.a_real_android.R;

import java.nio.ByteBuffer;
import java.util.Arrays;

//https://zhuanlan.zhihu.com/p/158866446
//用 AppCompatActivity 必须对就用 Theme.AppCompat或子 主题
public class Camera2Activity extends Activity {
    TextureView textureView;
    TextureView.SurfaceTextureListener surfaceTextureListener;
    CameraManager cameraManager;
    CameraDevice.StateCallback cam_stateCallback;
    CameraDevice opened_camera;
    Surface texture_surface;
    CameraCaptureSession.StateCallback cam_capture_session_stateCallback;
    CameraCaptureSession.CaptureCallback still_capture_callback;
    CameraCaptureSession cameraCaptureSession;
    CaptureRequest.Builder requestBuilder;
    CaptureRequest.Builder requestBuilder_image_reader;
    ImageReader imageReader;
    Surface imageReaderSurface;
    Bitmap bitmap;
    CaptureRequest request;
    CaptureRequest takephoto_request;
    Button takephoto_btn;
    ImageView takephoto_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera2_main);
        textureView=findViewById(R.id.camera2TextureView);
        takephoto_btn=findViewById(R.id.camera2Photo);
        takephoto_imageView= findViewById(R.id.camera2ImageView);
        surfaceTextureListener=new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                texture_surface=new Surface(textureView.getSurfaceTexture());
                openCamera();
            }
            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            }
            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }
            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }
        };
        textureView.setSurfaceTextureListener(surfaceTextureListener);
        //B1. 准备工作：初始化ImageReader
        imageReader = ImageReader.newInstance(1000  ,1000, ImageFormat.JPEG,2);
        //B2. 准备工作：设置ImageReader收到图片后的回调函数
        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                //B2.1 接收图片：从ImageReader中读取最近的一张，转成Bitmap
                Image image= reader.acquireLatestImage();
                ByteBuffer buffer= image.getPlanes()[0].getBuffer();
                int length= buffer.remaining();
                byte[] bytes= new byte[length];
                buffer.get(bytes);
                image.close();
                bitmap = BitmapFactory.decodeByteArray(bytes,0,length);
                //B2.2 显示图片
                takephoto_imageView.setImageBitmap(bitmap);
            }
        },null);
        //B3 配置：获取ImageReader的Surface
        imageReaderSurface = imageReader.getSurface();

        //B4. 相机点击事件
        takephoto_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //B4.1 配置request的参数 拍照模式(这行代码要调用已启动的相机 opened_camera，所以不能放在外面
                try {
                    requestBuilder_image_reader = opened_camera.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                requestBuilder_image_reader.set(CaptureRequest.JPEG_ORIENTATION,90);
                requestBuilder_image_reader.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
                //B4.2 配置request的参数 的目标对象
                requestBuilder_image_reader.addTarget(imageReaderSurface );
                try {
                    //B4.3 触发拍照
                    cameraCaptureSession.capture(requestBuilder_image_reader.build(),null,null);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openCamera() {
        cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);  // 初始化
        cam_stateCallback=new CameraDevice.StateCallback() {
            @Override
            public void onOpened(@NonNull CameraDevice camera) {
                opened_camera=camera;
                try {
                    requestBuilder = opened_camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                    requestBuilder.addTarget(texture_surface);
                    request = requestBuilder.build();
                    cam_capture_session_stateCallback=new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession session) {
                            cameraCaptureSession=session;
                            try {
                                session.setRepeatingRequest(request,null,null);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                        }
                    };
                    opened_camera.createCaptureSession( Arrays.asList(texture_surface,imageReaderSurface), cam_capture_session_stateCallback,null);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onDisconnected(@NonNull CameraDevice camera) {
            }
            @Override
            public void onError(@NonNull CameraDevice camera, int error) {
            }
        };
        checkPermission();
        try {
            cameraManager.openCamera(cameraManager.getCameraIdList()[0],cam_stateCallback,null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void checkPermission() {
        // 检查是否申请了权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 如果 textureView可用，就直接打开相机
        if(textureView.isAvailable()){
            openCamera();
        }else{
            // 否则，就开启它的可用时监听。
            textureView.setSurfaceTextureListener(surfaceTextureListener);
        }
    }
    @Override
    protected void onPause() {
        // 先把相机的session关掉
        if(cameraCaptureSession!=null){
            cameraCaptureSession.close();
        }
        // 再关闭相机
        if(null!=opened_camera){
            opened_camera.close();
        }
        // 最后关闭ImageReader
        if(null!=imageReader){
            imageReader.close();
        }
        // 最后交给父View去处理
        super.onPause();
    }

}