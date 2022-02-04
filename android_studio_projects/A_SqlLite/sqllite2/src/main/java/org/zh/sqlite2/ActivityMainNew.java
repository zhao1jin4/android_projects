package org.zh.sqlite2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

/*
ListActivity 过时 使用 ListFragment 或 RecyclerView (都是androidx)
*/
public class ActivityMainNew extends Activity {
	ListFragment f;//androidx
	RecyclerView r;//androidx

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;

	private DiaryDbAdapter mDbHelper;
	private Cursor mDiaryCursor;
	ListView listView ;
	static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,
			ContactsContract.Data.DISPLAY_NAME};
	static final String SELECTION = "((" +
			ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
			ContactsContract.Data.DISPLAY_NAME + " != '' ))";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_list);
		mDbHelper = new DiaryDbAdapter(this);
		mDbHelper.open();



		listView = (ListView) findViewById(R.id.mylistView);
		ListAdapter listAdapter=renderListView();
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
						// 需要对position和id进行一个很好的区分
						// position指的是点击的这个ViewItem在当前ListView中的位置
						// 每一个和ViewItem绑定的数据，肯定都有一个id，通过这个id可以找到那条数据。
						Cursor c = mDiaryCursor;
						c.moveToPosition(position);
						Intent intent = new Intent(ActivityMainNew.this, ActivityDiaryEdit.class);
						intent.putExtra(DiaryDbAdapter.KEY_ROWID, id);
						intent.putExtra(DiaryDbAdapter.KEY_TITLE, c.getString(c
								.getColumnIndexOrThrow(DiaryDbAdapter.KEY_TITLE)));
						intent.putExtra(DiaryDbAdapter.KEY_BODY, c.getString(c
								.getColumnIndexOrThrow(DiaryDbAdapter.KEY_BODY)));
						startActivityForResult(intent, ACTIVITY_EDIT);
					}
				}
		);
	}
	private ListAdapter renderListView() {
		mDiaryCursor = mDbHelper.getAllNotes();
		 //startManagingCursor(mDiaryCursor);//过时

		//CursorLoader loader=new CursorLoader();

		String[] from = new String[] { DiaryDbAdapter.KEY_TITLE,
				DiaryDbAdapter.KEY_CREATED };
		int[] to = new int[] { R.id.text1, R.id.created };
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.diary_row, mDiaryCursor, from, to,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER );

		return notes;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_insert);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case INSERT_ID:
				createDiary();
				return true;
			case DELETE_ID:
				mDbHelper.deleteDiary(listView.getSelectedItemId());
				ListAdapter listAdapter=renderListView();
				listView.setAdapter(listAdapter);
				return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void createDiary() {
		Intent i = new Intent(this, ActivityDiaryEdit.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode,
									Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		ListAdapter listAdapter=renderListView();
		listView.setAdapter(listAdapter);
	}
}
