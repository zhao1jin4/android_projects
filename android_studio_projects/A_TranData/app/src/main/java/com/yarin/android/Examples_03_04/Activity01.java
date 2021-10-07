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
                //隐式意图
                Intent intent = new Intent();
                //intent.setAction("my.intent.Example");
                intent.setAction("my.intent.AnotherExample");//任何一个就OK

                intent.addCategory("my.category.AnotherExample");

//            	intent.setData(Uri.parse("myrmi://localhost:8080/test"));
//            	intent.setType("image/gif");//会清除前面调用setData()
                intent.setDataAndType(Uri.parse("myrmi://localhost:8080/test"), "image/gif");

                //intent.setClass(Activity01.this, Activity03.class);
                startActivity(intent);

            }
        });

        /* 监听button的事件信息 */
        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)
            {
                //显示意图,知道哪个Activity类名
                Intent intent = new Intent();
                intent.setClass(Activity01.this, Activity02.class); //方法一

//                Intent intent1=new Intent(Activity01.this,Activity02.class);//方法二
//
//                Intent intent2=new Intent();
//                intent2.setComponent(new ComponentName(Activity01.this,Activity02.class));//方法三




                /* 启动一个新的Activity */
                //   startActivity(intent);

                intent.putExtra("username","张三");
                startActivityForResult(intent, 1);

                /* 关闭当前的Activity */
                //  Activity01.this.finish();
            }
        });
        /******************************/
        Button button3 = (Button) findViewById(R.id.button3);
        /* 监听button的事件信息 */
        button3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)
            {
                /* 关闭当前的Activity */
                Activity01.this.finish();
            }
        });
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.w(TAG,"restore name:"+savedInstanceState.getString("name"));
    }
    //按home,ctrl+f12,onPause->onSaveInstanceState
    //当这个Activity运行,如果有突然的电话接入,系统内在不足是可能会杀死这个Activity,为防止数据丢失
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