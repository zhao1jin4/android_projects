package cn.itcast.sms;

import java.util.ArrayList;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button = (Button)this.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText mobileText = (EditText)findViewById(R.id.mobile);
				EditText contentText = (EditText)findViewById(R.id.content);
				String mobile = mobileText.getText().toString();
				String content = contentText.getText().toString();
				//动态申请权限
				if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
				{
					ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
				}
				SmsManager smsManager = SmsManager.getDefault();//使用 android.telephony.SmsManager,不是gsm包也不是cdma包下
				ArrayList<String> texts = smsManager.divideMessage(content);//拆分短信
				for(String text : texts){
					smsManager.sendTextMessage(mobile, null, text, null, null);
				}
				Toast.makeText(MainActivity.this, R.string.success, Toast.LENGTH_LONG).show();//MainActivity.this外部类的对象
			}
		});
	}
}