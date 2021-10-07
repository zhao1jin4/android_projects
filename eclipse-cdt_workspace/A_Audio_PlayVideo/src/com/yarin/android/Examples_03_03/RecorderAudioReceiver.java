package  com.yarin.android.Examples_03_03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RecorderAudioReceiver extends BroadcastReceiver 
{
	public void onReceive(Context context, Intent intent) 
	{
		Log.w("RecorderReceiver", "-------------use the android.intent.action.BOOT_COMPLETED");
		context.startService(new Intent(context, RecorderAudioService.class));
	}

}
