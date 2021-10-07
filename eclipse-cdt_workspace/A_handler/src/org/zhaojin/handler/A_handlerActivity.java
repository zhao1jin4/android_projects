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
    //��Handler��������������������ĵ�ǰ�̵߳���Ϣ���У�������������Ϣ���з�����Ϣ
    //��Ϣ�����е���Ϣ�ɵ�ǰ�߳��ڲ����д���
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
    //���߳�(UI�߳�)
    //ҵ���߼���ȷ�����Ǹó������е�ʱ��������
    //������ʾ�ؼ��Ľ������ֻ����UI�̸߳���������ڷ�UI�̸߳��¿ؼ�������ֵ�����º����ʾ���治�ᷴӳ����Ļ��
    private void download(final String path, final File savedir) {
    	new Thread(new Runnable() {			
			public void run() 
			{
				for(int i=0;i<10;i++)
				{
			    	progressBar.setMax(100);//���ý����������̶�Ϊ�ļ��ĳ���
			    	
			    	
			    	//progressBar.setProgress(i*10);//�����ǿ��Ը��½����
			    	//resultView.setText( (i*10) + "%");//����ͱ��쳣��
			    	
			    	//��һ��д��
			    	Message msg = new Message();
					msg.what = 1;
					msg.getData().putInt("size", i*10);
					handler.sendMessage(msg);//������Ϣ
			    	msg.what=1;//Ϊ���ж����Ϣ��handleMessage����������
			    	Handler handler_cc=msg.getTarget();
					if(handler_cc==handler)//true
					{
				    	Log.w("A_handlerActivity", "Yes:SameHandler");
					}
			    	
			    	//�ڶ���д��
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