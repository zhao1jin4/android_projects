package org.zh;

import org.zh.aidl.AIDLActivity;
import org.zh.ndk.basic.BasicNDKActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class A_MainActivity extends Activity {

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