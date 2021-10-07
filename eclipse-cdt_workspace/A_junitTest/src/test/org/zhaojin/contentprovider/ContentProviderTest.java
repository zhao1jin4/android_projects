package test.org.zhaojin.contentprovider;

import junit.framework.Assert;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.test.AndroidTestCase;
import android.util.Log;

public class ContentProviderTest extends AndroidTestCase {
	public void testquery()
	{
		Uri base=Uri.parse("content://this.is.provider.unique.id/person");
		Uri uri=ContentUris.withAppendedId(base, 2);//   content://this.is.provider.unique.id/person/2
		
		Cursor cursor =this.getContext().getContentResolver().query(uri, null, null, null, "_id asc");
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
	
	public void testInsert()
	{
		ContentValues values=new ContentValues();
		values.put("_id", "20");
		values.put("name", "zhangsan");
		Uri uri=Uri.parse("content://this.is.provider.unique.id/person");
		
		Uri result =this.getContext().getContentResolver().insert(uri,values);
		
		Assert.assertTrue(result.toString().equals("content://this.is.provider.unique.id/person/insertok"));
	}
}
