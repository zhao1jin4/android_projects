package org.zhaojin.widgetUI.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewList2 extends Activity {

	private List<Map<String, Object>> data;
	private ListView listView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PrepareData();
		listView = new ListView(this);
		// ����ϵͳ��layout��ʾһ��
		 SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_1, new String[] { "����" },  new int[] { android.R.id.text1 });
		// ����ϵͳ��layout��ʾ����
//		 SimpleAdapter adapter = new SimpleAdapter(this, data,
//		 android.R.layout.simple_list_item_2, new String[] { "����","�Ա�" },
//		 new int[] { android.R.id.text1 , android.R.id.text2});
		// �����Լ���layout��������ʾ����
//		SimpleAdapter adapter = new SimpleAdapter(this, data,
//				R.layout.list_item, new String[] { "����", "�Ա�" }, new int[] {
//						R.id.mview1, R.id.mview2 });
		listView.setAdapter(adapter);
		setContentView(listView);	
		
		OnItemClickListener listener = new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				setTitle(parent.getItemAtPosition(position).toString());
				parent.getItemAtPosition(position).getClass().getName();//java.util.HashMap ��SimpleAdapter��data�е�HashMap
			}
		};
		listView.setOnItemClickListener(listener);
	}

	private void PrepareData() {
		data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item;
		item = new HashMap<String, Object>();
		item.put("����", "����С����");
		item.put("�Ա�", "��");
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("����", "����ͬѧ");
		item.put("�Ա�", "��");
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("����", "С��ʦ��");
		item.put("�Ա�", "Ů");
		data.add(item);
	}
}