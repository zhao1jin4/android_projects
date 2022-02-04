package org.zh.mycomponents;


import android.app.Activity;
import android.os.Bundle;

import org.zh.mycomponents.R;

public class MatrixActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//动态建立
		setContentView(R.layout.matrix);

	}
}