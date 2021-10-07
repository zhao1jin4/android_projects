package com.yarin.android.Examples_03_04;  
 
import android.app.Activity;  
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;  
import android.net.Uri;
import android.os.Bundle;  
import android.util.Log;  
import android.view.View;  
import android.widget.Button;  
import android.widget.Toast;
 
public class Activity01 extends Activity  
{  
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1&&resultCode==1 )
		{
			setTitle(data.getExtras().get("store").toString());
			
		}
		
	}

	private static final String TAG = "Activity01";  
 
    public void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
        Log.v(TAG, "onCreate");  
          
        Button button1 = (Button) findViewById(R.id.button1);  
        Button button2 = (Button) findViewById(R.id.button2);  
        
        button2.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View v)  
            { 
            	//��ʽ��ͼ
            	Intent intent = new Intent();  
            	//intent.setAction("my.intent.Example");
            	intent.setAction("my.intent.AnotherExample");//�κ�һ����OK
            	
            	intent.addCategory("my.category.AnotherExample");
            	
//            	intent.setData(Uri.parse("myrmi://localhost:8080/test"));
//            	intent.setType("image/gif");//�����ǰ�����setData()
                intent.setDataAndType(Uri.parse("myrmi://localhost:8080/test"), "image/gif");
            	
            	//intent.setClass(Activity01.this, Activity03.class); 
            	startActivity(intent);
            	
            }
        });
        
        /* ����button���¼���Ϣ */  
        button1.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View v)  
            {  
            	 //��ʾ��ͼ,֪���ĸ�Activity����
                Intent intent = new Intent();  
                intent.setClass(Activity01.this, Activity02.class); //����һ

//                Intent intent1=new Intent(Activity01.this,Activity02.class);//������
//
//                Intent intent2=new Intent();
//                intent2.setComponent(new ComponentName(Activity01.this,Activity02.class));//������

                
                
                
                /* ����һ���µ�Activity */  
             //   startActivity(intent);
                
                intent.putExtra("username","����");
                startActivityForResult(intent, 1);
                
                /* �رյ�ǰ��Activity */  
              //  Activity01.this.finish();  
            }  
        });  
        /******************************/  
        Button button3 = (Button) findViewById(R.id.button3);  
        /* ����button���¼���Ϣ */  
        button3.setOnClickListener(new Button.OnClickListener() {  
            public void onClick(View v)  
            {  
                /* �رյ�ǰ��Activity */  
                Activity01.this.finish();  
            }  
        });  
    }  
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.w(TAG,"restore name:"+savedInstanceState.getString("name"));
	}
    //��home,ctrl+f12,onPause->onSaveInstanceState
    //�����Activity����,�����ͻȻ�ĵ绰����,ϵͳ���ڲ����ǿ��ܻ�ɱ�����Activity,Ϊ��ֹ���ݶ�ʧ
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("name", "lisi");
	}  
    public void onStart()  
    {  
        super.onStart();  
        Log.v(TAG, "onStart");  
    }  
      
    public void onResume()  
    {  
        super.onResume();  
        Log.v(TAG, "onResume");  
    }  
      
    public void onPause()  
    {  
        super.onPause();  
        Log.v(TAG, "onPause");  
    }  
      
    public void onStop()  
    {  
        super.onStop();  
        Log.v(TAG, "onStop");  
    }  
 
    public void onDestroy()  
    {  
        super.onDestroy();  
        Log.v(TAG, "onDestroy");  
    }  
 
    public void onRestart()  
    {  
        super.onRestart();  
        Log.v(TAG, "onReStart");  
    }

	
      
} 