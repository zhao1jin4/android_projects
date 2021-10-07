package org.zhaojin.other;

import java.io.PushbackInputStream;
import java.util.concurrent.Executors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.test.AndroidTestCase;

public class OhterTest extends AndroidTestCase
{

	public void testAndroid() throws Exception
	{

//		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo netinfos[] = cm.getAllNetworkInfo();
//		for (NetworkInfo netinfo : netinfos)
//		{
//			String log=netinfo.getTypeName() +  netinfo.getState();
//		}
//		
//		String	verName =context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;

	}

	public void testJDK() throws Exception
	{

		java.net.InetAddress x=null;
		java.net.Socket  y=null;
		Runtime.getRuntime().availableProcessors();
		Executors.newFixedThreadPool(20);

		PushbackInputStream pushbackInputStream=null;
		byte[] array=new byte[2];
		pushbackInputStream.read(array);//PushbackInputStream
		pushbackInputStream.unread(array,1,1);  //PushBack缓冲区没有满，就可以使用unread()将数据推回,待下一次可以重新读取


	}
}
