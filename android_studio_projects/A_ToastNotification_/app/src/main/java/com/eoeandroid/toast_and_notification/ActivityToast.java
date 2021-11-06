package com.eoeandroid.toast_and_notification;

import com.eoeandroid.toast_and_notification.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityToast extends Activity {

	OnClickListener listener1 = null;
	OnClickListener listener2 = null;
	Button button1;
	Button button2;
	private static int NOTIFICATIONS_ID = 54321;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listener1 = new OnClickListener() {
			public void onClick(View v) {
				setTitle("短时间显示Toast");
				showToast(Toast.LENGTH_SHORT);

			}
		};
		listener2 = new OnClickListener() {
			public void onClick(View v) {
				setTitle("长时间显示Toast");
				showToast(Toast.LENGTH_LONG);

			}
		};
		setContentView(R.layout.activity_toast);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listener1);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listener2);
	}

	protected void showToast(int type) {

		View view = inflateView(R.layout.toast);

		TextView tv = (TextView) view.findViewById(R.id.content);
		tv.setText("加入专业Android开发社区eoeAndroid.com，让你的应用开发能力迅速提高");

		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
	}

	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}

}