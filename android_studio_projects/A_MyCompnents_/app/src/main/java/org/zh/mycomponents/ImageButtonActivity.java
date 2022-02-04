package org.zh.mycomponents;


import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;

import org.zh.mycomponents.R;

public class ImageButtonActivity extends Activity {
	CheckBox plain_cb;
	CheckBox serif_cb;
	CheckBox italic_cb;
	CheckBox bold_cb;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("ImageButtonActivity");
		setContentView(R.layout.image_button);
//		find_and_modify_text_view();
	}

 

 
}