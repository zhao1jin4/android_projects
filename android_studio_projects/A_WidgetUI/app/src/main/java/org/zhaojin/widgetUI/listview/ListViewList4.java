package org.zhaojin.widgetUI.listview;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewList4 extends Activity {
	ListView listView;
	private String[] data = { "eoeInstaller", "eoeDouban", "eoeWhere",
			"eoeInfoAssistant", "eoeDakarGame", "eoeTrack", "eoeInstaller", "eoeDouban", "eoeWhere",
			"eoeInfoAssistant", "eoeDakarGame", "eoeTrack"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data));

		setContentView(listView);
		listView.setOnScrollListener(new AbsListView.OnScrollListener(){
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
								 int visibleItemCount, int totalItemCount) {
			}
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState)
				{
					case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
						break;
					case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
						break;
					case AbsListView.OnScrollListener.SCROLL_STATE_FLING://表示滚动后正在做 惯性 滚动

						//ListView的一个格显示进度
						Toast.makeText(ListViewList4.this, "获得数据",Toast.LENGTH_LONG).show();
						break;
				}
			}});
	}
}
