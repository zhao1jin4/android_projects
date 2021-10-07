package org.zhaojin.widgetUI.menu;

import java.util.ArrayList;
import java.util.List;

import org.zhaojin.widgetUI.R;

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
	public boolean onCreateOptionsMenu(Menu menu) { //���ֻ��ϵ�menu�˵�ʱ
//		super.onOptionsMenuClosed(menu);
    	getMenuInflater().inflate(R.menu.main, menu);
		
    	MenuItem item=menu.findItem(R.id.menu_item1); //��ʾ�ڶ������Ͻ�����ͼ��,���뷽ʽ 
		item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(MenuXML.this, "Listener_�������˲˵���__"+item.getTitle(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		return true;
	}
    
    //android:onClick="myOnSubMenuItemClicked"  �Ḳ�� onOptionsItemSelected �� onMenuItemSelected
	 public void myOnSubMenuItemClicked(MenuItem item) //XML ���÷�ʽ
	 {
		 Toast.makeText(MenuXML.this, "XML�¼�_�������˲˵���__"+item.getTitle(), Toast.LENGTH_SHORT).show();
	 }
	 
	 @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {  //���ֻ��ϵ�menu�˵�ʱ ��Ӧ ��1��
		 switch (item.getItemId()) 
		 {
			case R.id.menu_sub_item1:
				Toast.makeText(MenuXML.this, "onMenuItemSelected_�������˲˵���__"+item.getTitle(), Toast.LENGTH_SHORT).show();
				break;
			case R.id.menu_itemCopy:
				Toast.makeText(MenuXML.this, "onMenuItemSelected_�������˲˵���_����_"+item.getTitle(), Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		//return true;
		return super.onMenuItemSelected(featureId, item);//����Ӧ onOptionsItemSelected  
	}


	public boolean onOptionsItemSelected(MenuItem item) { //���ֻ��ϵ�menu�˵�ʱ ��Ӧ ��2��
		 if(item.getItemId()==R.id.menu_sub_item1)
			 Toast.makeText(MenuXML.this, "onOptionsItemSelected_�������˲˵���__"+item.getTitle(), Toast.LENGTH_SHORT).show();
		return true;
	}
	 
	//----------�����Ĳ˵�    Ҫ���� super.onMenuItemSelected
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) //���� GridView��ListView �ĳ�������ʽ�˵� 
	{
		getMenuInflater().inflate(R.menu.context, menu);
		super.onCreateContextMenu(menu, v, menuInfo); 
	}
	 @Override
	public boolean onContextItemSelected(MenuItem item) {//���� GridView��ListView �ĵĳ�������ʽ�˵�  �¼���Ӧ 
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		String value=adapter.getItem(info.position);
		switch (item.getItemId()) {
		case R.id.context_item1:
			Toast.makeText(MenuXML.this, "ContextItemSelected �������˲˵���__"+value, Toast.LENGTH_SHORT).show();
			break;
		case R.id.context_item2:
			Toast.makeText(MenuXML.this, "ContextItemSelected �������˲˵���__"+value, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}