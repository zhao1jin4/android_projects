package org.zh.safeprotect;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TestService extends Service 
{

	private static final String TAG = "TestService";
	
	public IBinder onBind(Intent i) {
		Toast.makeText(TestService.this,"服务onBind了", Toast.LENGTH_SHORT).show();
		Log.e(TAG, "============> TestService.onBind");
		return new LocalBinder();//返回的Bunder是用来向Activity提供Service中的方法调用.
	}

	class LocalBinder extends Binder 
	{
		TestService getService() 
		{
			return TestService.this;
		}
		public String getHello()
		{
			return TestService.this.getHello();
		}
	}
	public String getHello()
	{
		return "how do you do!";
	}
	
	
	public boolean onUnbind(Intent i) {
		Toast.makeText(TestService.this,"服务onUnbind了", Toast.LENGTH_SHORT).show();
		Log.e(TAG, "============> TestService.onUnbind");
		return false;
	}

	public void onRebind(Intent i) {
		Log.e(TAG, "============> TestService.onRebind");
	}

	public void onCreate() {
		Log.e(TAG, "============> TestService.onCreate");
		Toast.makeText(TestService.this,"服务创建了", Toast.LENGTH_SHORT).show();
	}

	public void onStart(Intent intent, int startId) {
		Toast.makeText(TestService.this,"服务启动了", Toast.LENGTH_SHORT).show();
		Log.e(TAG, "============> TestService.onStart");
	}

	public void onDestroy() {
		Toast.makeText(TestService.this,"服务销毁了", Toast.LENGTH_SHORT).show();
		Log.e(TAG, "============> TestService.onDestroy");
	}
	
 
}
