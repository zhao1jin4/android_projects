package com.example.a_googleservice;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Button geocoderButton = (Button) findViewById(R.id.geocoderButton);
        geocoderButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, GeoCoderActivity.class);
			        		startActivity(intent);
			        	}
			        });
        
        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) {
			        		Intent intent = new Intent();
			        		intent.setClass(MainActivity.this, GoogleMapActivity.class);
			        		startActivity(intent);
			        	}
			        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
