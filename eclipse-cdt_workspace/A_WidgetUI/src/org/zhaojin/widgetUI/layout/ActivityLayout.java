package org.zhaojin.widgetUI.layout;

import org.zhaojin.widgetUI.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ActivityLayout extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(linearLayout);
		
		
		
		LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout layoutLeft = (RelativeLayout) inflate.inflate(R.layout.layout_left, null);
		RelativeLayout layoutRight = (RelativeLayout) inflate.inflate(R.layout.layout_right, null);

		RelativeLayout.LayoutParams relParam = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, //w
				RelativeLayout.LayoutParams.WRAP_CONTENT);//h
		
		linearLayout.addView(layoutLeft, 100, 100);//w,h
		linearLayout.addView(layoutRight, relParam);
		
	}
}