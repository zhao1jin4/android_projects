package org.zhaojin.ndk.basic;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
public class BasicNDKActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        System.loadLibrary("c-native");//libc-native.so
        
        NativeClass n=new NativeClass();
        int max=n.max(3, 5, 2);
        System.out.println("��NDK�е�ô�����ֵ��:"+max);//����OK
        
    }
}