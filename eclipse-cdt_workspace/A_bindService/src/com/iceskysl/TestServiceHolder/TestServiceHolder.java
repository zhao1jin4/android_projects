package com.iceskysl.TestServiceHolder;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestServiceHolder extends Activity {
	private boolean _isBound;
	private TestService _boundService;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTitle("Service Test");

		initButtons();

	}
	
    private ServiceConnection _connection = new ServiceConnection()
    {  
    
        public void onServiceConnected(ComponentName className, IBinder binder) //会先调用Service的onBind方法返回Binder,为了调用Service中的方法
        { 
        	//如果配置为 android:process=":remote",则在onServiceConnected方法中是BinderProxy
        	Log.e("ServerConn","--------The Server is onServiceConnected ");	
        	TestService.LocalBinder localBinder=(TestService.LocalBinder)binder;
            _boundService = localBinder.getService();  
           
            String hello2=localBinder.getHello();
            String hello=_boundService.getHello();
            
            Toast.makeText(TestServiceHolder.this, "Service connected,hello="+hello,Toast.LENGTH_SHORT).show();  
        }  
  
        public void onServiceDisconnected(ComponentName className) 
        {  
        	Log.e("ServerConn","---=====The Server is onServiceDisconnected ");	
            // unexpectedly disconnected,we should never see this happen.  
            _boundService = null;  
            Toast.makeText(TestServiceHolder.this, "Service disconnected",Toast.LENGTH_SHORT).show();  
        }  
    };  
    
    private void initButtons() {  
        Button buttonStart = (Button) findViewById(R.id.start_service);  
        buttonStart.setOnClickListener(new OnClickListener() {  
            public void onClick(View arg0) {  
                startService();  
            }  
        });  
  
        Button buttonStop = (Button) findViewById(R.id.stop_service);  
        buttonStop.setOnClickListener(new OnClickListener() {  
            public void onClick(View arg0) {  
                stopService();  
            }  
        });  
  
        Button buttonBind = (Button) findViewById(R.id.bind_service);  
        buttonBind.setOnClickListener(new OnClickListener() {  
            public void onClick(View arg0) {  
                bindService();  
            }  
        });  
  
        Button buttonUnbind = (Button) findViewById(R.id.unbind_service);  
        buttonUnbind.setOnClickListener(new OnClickListener() {  
            public void onClick(View arg0) {  
                unbindService();  
            }  
        });  
    }  
  
    private void startService() {  
        Intent i = new Intent(this, TestService.class);  
        this.startService(i);
        Toast.makeText(TestServiceHolder.this, "Toast.makeText", Toast.LENGTH_SHORT).show();
    }  
    
    private void stopService() {  
        Intent i = new Intent(this, TestService.class);  
        this.stopService(i);  
    }  
  
    private void bindService() {  
        Intent i = new Intent(this, TestService.class);  
         bindService(i, _connection, Context.BIND_AUTO_CREATE);
         _isBound = true;  
    }  
  
    private void unbindService() {  
        if (_isBound) {  
            unbindService(_connection);  
            _isBound = false;  
        }  
    }  
}