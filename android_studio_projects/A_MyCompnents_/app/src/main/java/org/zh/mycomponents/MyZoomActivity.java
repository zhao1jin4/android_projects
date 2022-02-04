package org.zh.mycomponents;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ZoomControls;

import org.zh.mycomponents.R;

public class MyZoomActivity extends Activity {
	public TextView zoomText;
	ZoomControls zoom;
	public float zoomScale;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zoom_control);
		
		zoomText=(TextView)this.findViewById(R.id.scaleText);
		zoomScale=zoomText.getTextSize();
				
		zoom=(ZoomControls)this.findViewById(R.id.zoom_controls);
		
		zoom.setOnZoomInClickListener(new ZoomControls.OnClickListener() {
			@Override
			public void onClick(View v) {
				zoomScale+=2;
				zoomText.setTextSize(zoomScale);
			}
		});
		zoom.setOnZoomOutClickListener(new ZoomControls.OnClickListener() {
			@Override
			public void onClick(View v) {
				zoomScale-=2;
				zoomText.setTextSize(zoomScale);
			}
		});
	}
}