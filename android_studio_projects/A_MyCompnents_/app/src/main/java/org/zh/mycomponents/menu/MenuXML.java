package org.zh.mycomponents.menu;

import java.util.ArrayList;
import java.util.List;

import org.zh.mycomponents.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;


public class MenuXML extends Activity {
	ListView listView;
	ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_context);
		listView=(ListView)findViewById(R.id.listView1);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData());
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
	}
	private List<String> getData()
	{
		List<String> list=new ArrayList<String>();
		for (int i=0;i<10;i++)
		{
			list.add("XML data_"+i);
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { //按手机上的menu菜单时
//		super.onOptionsMenuClosed(menu);
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem item=menu.findItem(R.id.menu_item1); //显示在顶部右上角区的图标,编码方式
		item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(MenuXML.this, "Listener_被单击了菜单项__"+item.getTitle(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		return true;
	}

	//android:onClick="myOnSubMenuItemClicked"  会覆盖 onOptionsItemSelected 和 onMenuItemSelected
	public void myOnSubMenuItemClicked(MenuItem item) //XML 配置方式
	{
		Toast.makeText(MenuXML.this, "XML事件_被单击了菜单项__"+item.getTitle(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {  //按手机上的menu菜单时 响应 第1次
		switch (item.getItemId())
		{
			case R.id.menu_sub_item1:
				Toast.makeText(MenuXML.this, "onMenuItemSelected_被单击了菜单项__"+item.getTitle(), Toast.LENGTH_SHORT).show();
				break;
			case R.id.menu_itemCopy:
				Toast.makeText(MenuXML.this, "onMenuItemSelected_被单击了菜单项_复制_"+item.getTitle(), Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		//return true;
		return super.onMenuItemSelected(featureId, item);//会响应 onOptionsItemSelected
	}


	public boolean onOptionsItemSelected(MenuItem item) { //按手机上的menu菜单时 响应 第2次
		if(item.getItemId()==R.id.menu_sub_item1)
			Toast.makeText(MenuXML.this, "onOptionsItemSelected_被单击了菜单项__"+item.getTitle(), Toast.LENGTH_SHORT).show();
		return true;
	}

	//----------上下文菜单    要调用 super.onMenuItemSelected
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) //基于 GridView或ListView 的长按弹出式菜单
	{
		getMenuInflater().inflate(R.menu.context, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {//基于 GridView或ListView 的的长按弹出式菜单  事件响应
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		String value=adapter.getItem(info.position);
		switch (item.getItemId()) {
			case R.id.context_item1:
				Toast.makeText(MenuXML.this, "ContextItemSelected 被单击了菜单项__"+value, Toast.LENGTH_SHORT).show();
				break;
			case R.id.context_item2:
				Toast.makeText(MenuXML.this, "ContextItemSelected 被单击了菜单项__"+value, Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		return super.onContextItemSelected(item);
	}
}