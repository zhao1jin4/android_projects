package org.zh.mycomponents;


import android.app.Activity;
import android.os.Bundle;

import org.zh.mycomponents.R;

public class MyViewActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myview);
	}
}