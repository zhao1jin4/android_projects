package com.eoeandroid.broadcastReceiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
	Context context;
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		Object[] pdus=(Object[])intent.getExtras().get("pdus");
		for(Object pdu:pdus)
		{
			byte[]pduMsg=(byte[])pdu;
			SmsMessage sms=SmsMessage.createFromPdu(pduMsg);//soon deprecated 
			String mobile=sms.getOriginatingAddress();//发来的号码
			String msg=sms.getMessageBody();
			Date date= new  Date(sms.getTimestampMillis());
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			Log.w("SMSReceiver","mobile:"+mobile+",messaeg:"+msg+",date:"+time);
		}


		//abortBroadcast();		
	}

}
