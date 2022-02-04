package org.zh.a_junit4test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class TestActivity extends Activity {
    /*

----src/androidTest/java/里的方法   新建的项目不能运行？？
   原因为\caches\modules-2\files-2.1\androidx.test.ext\junit\1.1.2\xxx\junit-1.1.2.aar\AndroidManifest.xml 里面有定义
  <uses-sdk
        android:minSdkVersion="14"
        android:targetSdkVersion="28" />
  build.gradle 中也要修改对应的值，即 targetSdk 28 ，对应的minSdk 最多只能是28
  警告中有说 The minSdk version should not be declared in the android manifest file.

build.gradle中  android { }内部增加
  packagingOptions {
        exclude 'META-INF/DEPENDENCIES'
  }
 AndroidManifest.xml中的包名和代码包名一样也可以运行

win10 administrator运行正常
win7上普通用户运行提示
You need the android.permission.INSTALL_GRANT_RUNTIME_PERMISSIONS permission to use the PackageManager.INSTALL_GRANT_RUNTIME_PERMISSIONS flag
但这个是系统App的权限
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pre = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString("username", "hr");
        editor.commit();

        //this.getSharedPreferences("myfilename", MODE_PRIVATE);

        setContentView(R.layout.main);
    }
}
