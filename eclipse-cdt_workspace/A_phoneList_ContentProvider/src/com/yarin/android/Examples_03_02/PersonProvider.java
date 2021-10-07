package com.yarin.android.Examples_03_02;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider{

	MySQLiteOpenHelper sqliteHelper =null;
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getType(Uri uri) {
		UriMatcher matcher =new UriMatcher(UriMatcher.NO_MATCH);
		String authroites="this.is.provider.unique.id";
		matcher.addURI(authroites, "person", 1);
		matcher.addURI(authroites, "person/#", 2);
		String result =null;
		switch (matcher.match(uri)) 
		{	
		case 1:
			result="vnd.android.cursor.dir/";
			break;
		case 2:
			result="vnd.android.cursor.item/";
			break;
		}
		return result;
	}


	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase db=sqliteHelper.getWritableDatabase();
		UriMatcher matcher =new UriMatcher(UriMatcher.NO_MATCH);//没有找到返回UriMatcher.NO_MATCH
		String authroites="this.is.provider.unique.id";
		matcher.addURI(authroites, "person", 1);  //    如"person/#" 是通配符 ,生成content://this.is.a.unique.id/person
		
		switch (matcher.match(uri)) //如果uri的<authorities>和<path> 部分匹配返回对应的码(addURI的第三个参数)
		{	
		case 1:
			db.insert("person", null, values);
		break;
		
		}
		this.getContext().getContentResolver().notifyChange(uri, null);//改变可以发生通知,对uri监听的都会知道
		
		Uri result=Uri.parse("content://this.is.provider.unique.id/person/insertok");
		return result;
	}

	public boolean onCreate() {
		 sqliteHelper =new MySQLiteOpenHelper(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
						String[] selectionArgs, String sortOrder) {
		
		Cursor result=null;
		MySQLiteOpenHelper help =new MySQLiteOpenHelper(this.getContext());
		SQLiteDatabase db=help.getWritableDatabase();
		
		
		UriMatcher matcher =new UriMatcher(UriMatcher.NO_MATCH);
		String authroites="this.is.provider.unique.id";
		matcher.addURI(authroites, "person/#", 2);  
		
		
		switch (matcher.match(uri)) 
		{	
		case 2:
			String path=uri.getPath();//   /person/2
			String id=path.split("/")[2];
			
			Cursor bb= db.rawQuery("select _id,name from person where _id=?",new String[]{id});
			Cursor cc= db.query("person", new String[]{"_id","name"}, "_id=?", new String[]{id}, null, null, null);
			//为什么加参数就没有返回记录呢???????
			
			result=db.query("person",  new String[]{"_id","name"}, null, null, null,null, null);
			
		break;
		
		}
		return result;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
