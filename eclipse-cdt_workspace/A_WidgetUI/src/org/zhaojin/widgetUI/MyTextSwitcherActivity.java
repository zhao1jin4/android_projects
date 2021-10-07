package org.zhaojin.widgetUI;

import java.text.SimpleDateFormat;
import java.util.Locale;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class MyTextSwitcherActivity extends Activity 
{
	TextSwitcher myTextSwitcher = null;
	Button myTextSwitcherBtn =  null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_switcher);
		
		myTextSwitcherBtn = (Button) findViewById(R.id.myTextSwitcherBtn);
		myTextSwitcher = (TextSwitcher) findViewById(R.id.myTextSwitcher);
		
		myTextSwitcher.setFactory(new MyViewFactory());
		myTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(MyTextSwitcherActivity.this,android.R.anim.fade_in));
		myTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MyTextSwitcherActivity.this,android.R.anim.fade_out));
		
		myTextSwitcherBtn.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.CHINESE);
				String dataStr=format.format(new java.util.Date());
				myTextSwitcher.setText(dataStr);//要在SetFactory之后调用
			}
		});
	}
	class MyViewFactory implements ViewFactory
	{
		@Override
		public View makeView() 
		{
			TextView txt=new TextView(MyTextSwitcherActivity.this);
			txt.setBackgroundColor(Color.WHITE);
			txt.setTextColor(Color.BLACK);
			//txt.setLayoutParams(new TextSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			txt.setLayoutParams(new TextSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			//要使用TextSwitcher.LayoutParams
			txt.setTextSize(30f);
			return txt;
		}
	}
}