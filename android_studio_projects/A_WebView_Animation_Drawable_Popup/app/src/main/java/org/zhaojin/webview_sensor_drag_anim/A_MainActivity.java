package org.zhaojin.webview_sensor_drag_anim;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class A_MainActivity extends Activity
{

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

//        //自定义标题
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//android 4.1报错,在setContentView(...)方法前使用
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		//使用 <activity android:theme="titleThem" 方式

		setContentView(R.layout.main);


		//--
		// Debug.startMethodTracing("traceFile");//写入SD卡目录,/mnt/sdcard/
		//--


		try {
			//--得到  <meta-data>

			ActivityInfo activityInfo= this.getPackageManager().getActivityInfo(new ComponentName(this,A_MainActivity.class),PackageManager.GET_META_DATA);
			Bundle bundle=activityInfo.metaData;
			String name=bundle.getString("my.props.name");
			int id=bundle.getInt("my.props.id");
			int name_R_id=bundle.getInt("my.props.name.R.id");

			Toast.makeText(this,id+":"+name+":"+Integer.toHexString(name_R_id), Toast.LENGTH_LONG).show();



		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}


		Button webviewButton = (Button) findViewById(R.id.webviewButton);
		webviewButton.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(A_MainActivity.this, WebViewUIActivity.class);
				startActivity(intent);
			}
		});


		Button animButton = (Button) findViewById(R.id.animButton);
		animButton.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(A_MainActivity.this, AnimActivity.class);
				startActivity(intent);

				//Activity切换动画
				A_MainActivity.this.overridePendingTransition(R.anim.enter_activity, R.anim.leave_activity);

			}
		});

		Button drawableButton = (Button) findViewById(R.id.drawbleButton);
		drawableButton.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(A_MainActivity.this, DrawableActivity.class);
				startActivity(intent);
			}
		});

		Button popupButton = (Button) findViewById(R.id.popupButton);
		popupButton.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(A_MainActivity.this, PopupActivity.class);
				startActivity(intent);
			}
		});

		Button unLockButton = (Button) findViewById(R.id.unLockButton);
		unLockButton.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(A_MainActivity.this, UnlockActivity.class);
				startActivity(intent);
			}
		});

	}

	protected void onDestroy() {
		//	Debug.stopMethodTracing();//停止采集
		super.onDestroy();
	}


} 
