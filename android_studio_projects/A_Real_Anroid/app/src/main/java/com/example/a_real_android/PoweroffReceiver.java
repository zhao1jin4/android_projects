package  com.example.a_real_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PoweroffReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent arg1) {
//		Intent intent = new Intent(Intent.ACTION_SHUTDOWN);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(intent);

//		 Toast.makeText(context, "System will poweroff ", Toast.LENGTH_LONG).show();
		System.out.println("System will poweroff");
		Intent intent = new Intent(Intent.ACTION_SHUTDOWN);//android.permission.DEVICE_POWER,可能要得到root权限
		context.sendBroadcast(intent);
	}

}
