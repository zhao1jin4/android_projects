package org.zh;

import org.zh.aidl.AIDLActivity;
import org.zh.ndk.basic.BasicNDKActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class A_MainActivity extends Activity {

	/*
到自己的项目目录(是app目录里面),运行ndk-build(会读./jni/Android.mk文件)会生成目录obj,libs
win10上可成功运行ndk-build
	生成4个目录,每个里面有 libc-native.so
	libs\armeabi-v7a
	libs\arm64-v8a
	libs\x86
	libs\x86_64

win7上运行ndk-build
	报ld: error: failed to write to the output file: Permission denied ???


app目录下 的build.gradle文件中在android{}里面增加如下语句
	 sourceSets {
        main {
            jniLibs.srcDirs = ['libs']
        }
    }
用命令gradle build 或者 用Android Studio 2020.3.1构建(jniLib显示的图标和res目录是一样的),win10下idea 2021.3.1 点构建按钮不行

打包后的apk就有lib目录了,使用模拟器里面只有x86_64目录,而不是全部

如lib目录要有armeabi-v7a(最早只生成armeabi)和arm64-v8a
android {
    defaultConfig {
		//在app目录下 的build.gradle文件中android.defaultConfig下增加(写的时候没有完全提示)，如不增加x86_64，启动模拟x86_64的模拟器才有弹窗提示
		ndk{
            abiFilters "armeabi-v7a", "arm64-v8a","x86_64"
        }
	}
}

	 */
	//.c文件另存为UTF-8
    public void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        
        
        Button ndkButton = (Button) findViewById(R.id.ndkButton);
        ndkButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(A_MainActivity.this, BasicNDKActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        
        Button aidlButton = (Button) findViewById(R.id.aidlButton);
        aidlButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(A_MainActivity.this, AIDLActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
    }

}