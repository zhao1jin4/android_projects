package org.zhaojin.widgetUI.layout;

import org.zhaojin.widgetUI.R;

import android.app.Activity;
import android.os.Bundle;

public class ActivityRelativeLayout extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.layout_relative);
		
	}
}