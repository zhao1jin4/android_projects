package zhaojin.org.a_sqllite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ActivityMain extends Activity {
	OnClickListener listener1 = null;
	OnClickListener listener2 = null;
	OnClickListener listener3 = null;
	OnClickListener listener4 = null;
	OnClickListener listener5 = null;
	OnClickListener cursorListener = null;

	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button cursorButton;

	DatabaseHelper mOpenHelper;

	private static final String DATABASE_NAME = "dbForTest.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "diary";
	private static final String TITLE = "title";
	private static final String BODY = "body";

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			String sql = "CREATE TABLE " + TABLE_NAME + " (" + TITLE
					+ " text not null, " + BODY + " text not null " + ");";
			Log.i("haiyang:createDB=", sql);
			db.execSQL(sql);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		prepareListener();
		initLayout();
		mOpenHelper = new DatabaseHelper(this);

	}

	private void initLayout() {
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listener1);

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listener2);

		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(listener3);
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(listener4);

		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(listener5);

		cursorButton = (Button) findViewById(R.id.cursorButton);
		cursorButton.setOnClickListener(cursorListener);

	}

	private void prepareListener() {
		listener1 = new OnClickListener() {
			public void onClick(View v) {
				CreateTable();
			}
		};
		listener2 = new OnClickListener() {
			public void onClick(View v) {
				dropTable();
			}
		};
		listener3 = new OnClickListener() {
			public void onClick(View v) {
				insertItem();
			}
		};
		listener4 = new OnClickListener() {
			public void onClick(View v) {
				deleteItem();
			}
		};
		listener5 = new OnClickListener() {
			public void onClick(View v) {
				showItems();
			}
		};
		cursorListener = new OnClickListener() {
			public void onClick(View v) {


				ListView listView = new ListView(ActivityMain.this);
				Cursor cursorData= queryAllData()  ;//"_id"

				SimpleCursorAdapter adapter = new SimpleCursorAdapter(ActivityMain.this,android.R.layout.simple_list_item_2,
						cursorData,
						new String[] { TITLE, BODY },
						new int[] {	android.R.id.text1 , android.R.id.text2 }
						,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
				);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view, int position,	long id)
					{
						Object obj=parent.getItemAtPosition(position);
						String str=obj.getClass().getName();//android.database.sqlite.SQLiteCursor
						Cursor row=(Cursor)obj;
						row.moveToFirst();
						String title=row.getString(row.getColumnIndex(TITLE));
						Toast.makeText(ActivityMain.this,"title:"+title , Toast.LENGTH_SHORT).show();
					}

				});
				ActivityMain.this.setContentView(listView);
			}
		};
	}

	/*
	 * 重新建立数据表
	 */
	private void CreateTable() {
		//SQLiteDatabase db = mOpenHelper.getReadableDatabase();
//		db.setTransactionSuccessful();
//		db.beginTransaction();
//		db.endTransaction();
		//db.rawQuery("select ... ?",arg)
		//cur.getInt(cur.getColumnIndex("_id"));






		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + TITLE
				+ " text not null, " + BODY + " text not null " + ");";
		Log.i("haiyang:createDB=", sql);

		try {
			db.execSQL("DROP TABLE IF EXISTS diary");
			db.execSQL(sql);
			Toast.makeText(this, "数据表成功重建" , Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			Toast.makeText(this, "数据表重建错误" , Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * 删除数据表
	 */
	private void dropTable() {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		String sql = "drop table " + TABLE_NAME;
		try {
			db.execSQL(sql);
			Toast.makeText(this, "数据表成功删除：" + sql , Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			Toast.makeText(this, "数据表删除错误" , Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * 插入两条数据
	 */
	private void insertItem() {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		String sql1 = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
				+ ") values('haiyang', 'android的发展真是迅速啊');";
		String sql2 = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
				+ ") values('icesky', 'android的发展真是迅速啊');";
		try {
			Log.i("haiyang:sql1=", sql1);
			Log.i("haiyang:sql2=", sql2);
			db.execSQL(sql1);
			db.execSQL(sql2);
			Toast.makeText(this, "插入两条数据成功", Toast.LENGTH_LONG).show();
	 	} catch (SQLException e) {
			Toast.makeText(this, "插入两条数据失败", Toast.LENGTH_LONG).show();
		}
	}

	/*
	 * 删除其中的一条数据
	 */
	private void deleteItem() {
		try {
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			db.delete(TABLE_NAME, " title = 'haiyang'", null);
			Toast.makeText(this, "删除title为haiyang的一条记录" , Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {

		}

	}
	private Cursor queryAllData() {
		Cursor cursor=null;
		try {
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			cursor=db.query(TABLE_NAME, new String[]{"title as _id",TITLE,BODY}, null, null, null,null, null);//"title as _id",
		} catch (SQLException e) {

		}
		return cursor;
	}

	/*
	 * 在屏幕的title区域显示当前数据表当中的数据的条数。
	 */
	private void showItems() {

		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		String col[] = { TITLE, BODY };
		Cursor cur = db.query(TABLE_NAME, col, null, null, null, null, null);
		Integer num = cur.getCount();
		cur.close();
		Toast.makeText(this, Integer.toString(num) + " 条记录" , Toast.LENGTH_SHORT).show();
	}
}