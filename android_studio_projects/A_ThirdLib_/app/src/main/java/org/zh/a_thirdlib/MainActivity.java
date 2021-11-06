package org.zh.a_thirdlib;

import org.zh.R;
import org.zh.ksoap2.KSoap2MainActivity;
import org.zh.oauth1.OAuth1MainActivity;
import org.zh.zxing_android.GenerateQRCodeActivity;
import org.zh.zxing_android.ScanLineCodeActivity;
import org.zh.zxing_android.ScanQRCodeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    //引用第三方jar，不使用网络，而是本地的jar包,libs目录和src目录同级
    //implementation fileTree(includes: ["*.jar"],dir: 'libs')
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------OAuth1 signpost-commonshttp4-1.2.1.2.jar,signpost-core-1.2.12
        Button oauth1Btn = (Button) findViewById(R.id.oauth1Btn);
        oauth1Btn.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, OAuth1MainActivity.class);
                startActivity(intent);
            }
        });
        //------------------OAuth2

        //------------------KSoap2 ksoap2-android-assembly-3.0.0-RC.4-jar-with-dependencies.jar
        Button ksoap2Btn = (Button) findViewById(R.id.ksoap2Btn);
        ksoap2Btn.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, KSoap2MainActivity.class);
                startActivity(intent);
            }
        });
        //------------------二维码

        Button gnerateQRCodeBtn = (Button) findViewById(R.id.gnerateQRCodeBtn);
        gnerateQRCodeBtn.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GenerateQRCodeActivity.class);
                startActivity(intent);
            }
        });
        Button scanQRCodeBtn = (Button) findViewById(R.id.scanQRCodeBtn);
        scanQRCodeBtn.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScanQRCodeActivity.class);
                startActivity(intent);
            }
        });
        //------------------条形码
        Button scanLineCodeBtn = (Button) findViewById(R.id.scanLineCodeBtn);
        scanLineCodeBtn.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScanLineCodeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
