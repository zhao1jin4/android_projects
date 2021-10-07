package com.yarin.android.Examples_03_02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class MySQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

	public MySQLiteOpenHelper(Context context) {
		super(context, "mydb.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		String sql = " create table  person (_id int,name varchar(20));";
		Log.i("haiyang:createDB=", sql);
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
