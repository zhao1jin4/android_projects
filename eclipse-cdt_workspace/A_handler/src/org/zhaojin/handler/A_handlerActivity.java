package org.zhaojin.handler;

import java.io.File;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class A_handlerActivity extends Activity {
    private EditText downloadpathText;
    private TextView resultView;
    private ProgressBar progressBar;
    //当Handler被创建会关联到创建它的当前线程的消息队列，该类用于往消息队列发送消息
    //消息队列中的消息由当前线程内部进行处理
    private Handler handler = new Handler()
    	{
			public void handleMessage(Message msg) {			
				switch (msg.what) {
				case 1:				
					progressBar.setProgress(msg.getData().getInt("size"));
					float num = (float)progressBar.getProgress()/(float)progressBar.getMax();
					int result = (int)(num*100);
					resultView.setText(result+ "%");
					if(progressBar.getProgress()==progressBar.getMax()){
						Toast.makeText(A_handlerActivity.this, R.string.success, 1).show();
					}
					break;
	
				case -1:
					Toast.makeText(A_handlerActivity.this, R.string.error, 1).show();
					break;
				}
			}
    	};
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        downloadpathText = (EditText) this.findViewById(R.id.downloadpath);
        progressBar = (ProgressBar) this.findViewById(R.id.downloadbar);
        resultView = (TextView) this.findViewById(R.id.result);
        Button button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {	
        	
			public void onClick(View v) {
				String path = downloadpathText.getText().toString();
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					download(path, Environment.getExternalStorageDirectory());
				}else{
					Toast.makeText(A_handlerActivity.this, R.string.sdcarderror, 1).show();
				}
				
			}
		});
    }
    //主线程(UI线程)
    //业务逻辑正确，但是该程序运行的时候有问题
    //对于显示控件的界面更新只是由UI线程负责，如果是在非UI线程更新控件的属性值，更新后的显示界面不会反映到屏幕上
    private void download(final String path, final File savedir) {
    	new Thread(new Runnable() {			
			public void run() 
			{
				for(int i=0;i<10;i++)
				{
			    	progressBar.setMax(100);//设置进度条的最大刻度为文件的长度
			    	
			    	
			    	//progressBar.setProgress(i*10);//这样是可以更新界面的
			    	//resultView.setText( (i*10) + "%");//这个就报异常了
			    	
			    	//第一种写法
			    	Message msg = new Message();
					msg.what = 1;
					msg.getData().putInt("size", i*10);
					handler.sendMessage(msg);//发送消息
			    	msg.what=1;//为了有多个消息在handleMessage方法中区分
			    	Handler handler_cc=msg.getTarget();
					if(handler_cc==handler)//true
					{
				    	Log.w("A_handlerActivity", "Yes:SameHandler");
					}
			    	
			    	//第二种写法
//					Message empMsg=handler.obtainMessage(2);//.sendTarget();
//					empMsg.sendToTarget();
					
			    	
			    	Log.w("A_handlerActivity", "Progress:="+(i*10));
			    	
			    	
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}