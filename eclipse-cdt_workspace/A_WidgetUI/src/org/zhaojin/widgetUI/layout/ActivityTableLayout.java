package org.zhaojin.widgetUI.layout;

import org.zhaojin.widgetUI.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ActivityTableLayout extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);;
		setContentView(R.layout.layout_table);

	}
}