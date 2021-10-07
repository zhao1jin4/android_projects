package org.zhaojin.widgetUI.menu;

import java.util.ArrayList;
import java.util.List;

import org.zhaojin.widgetUI.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MenuActionMode extends Activity {
	ListView listView;
	ArrayAdapter<String> adapter;
	ActionMode mActionMode ;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_action_mode);
		listView=(ListView)findViewById(R.id.listView1);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData());
		listView.setAdapter(adapter);

		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,  int position, long id)
			{
				if (mActionMode != null) {
					return false;
				}
				mActionMode =startActionMode(mActionModeCallback);//长按ListView的item时在标题区显示菜单
				listView.setSelected(true);
				return true;
			}
		});
	}
	private List<String> getData()
	{
		List<String> list=new ArrayList<String>();
		for (int i=0;i<10;i++)
		{
			list.add("actionMode-data_"+i);
		}
		return list;
	}

	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback()
	{
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu)
		{
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.action_mode, menu);
			return true;
		}
		// Called each time the action mode is shown. Always called after onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false; // Return false if nothing is done
		}
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
				case R.id.action_menu_item1:
					Toast.makeText(MenuActionMode.this, "Action Mode 被单击了菜单项__"+item.getTitle(), Toast.LENGTH_SHORT).show();
					mode.finish(); // Action picked, so close the CAB
					return true;
				default:
					return false;
			}
		}
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			mActionMode = null;
		}
	};
}