package org.zh.contentprovider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ContentProviderTest   {
	@Test
	public void testquery()
	{
		Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();//生成代码使用的
		Context appContext=InstrumentationRegistry.getInstrumentation().getContext();

		Uri base=Uri.parse("content://this.is.provider.unique.id/person");
		Uri uri=ContentUris.withAppendedId(base, 2);//   content://this.is.provider.unique.id/person/2
		
		Cursor cursor =context.getContentResolver().query(uri, null, null, null, "_id asc");
		boolean haveRecord=false;
		while(cursor.moveToNext())
		{
			haveRecord=true;
			int id=cursor.getInt(cursor.getColumnIndex("_id"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			Log.i("ContentProviderTest", "id="+id+".name="+name);
		}
		Assert.assertTrue(haveRecord);
	}
	@Test
	public void testInsert()
	{
		Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();//生成代码使用的
		Context appContext=InstrumentationRegistry.getInstrumentation().getContext();


		ContentValues values=new ContentValues();
		values.put("_id", "20");
		values.put("name", "zhangsan");
		Uri uri=Uri.parse("content://this.is.provider.unique.id/person");
		
		Uri result =context.getContentResolver().insert(uri,values);
		
		Assert.assertTrue(result.toString().equals("content://this.is.provider.unique.id/person/insertok"));
	}
}
