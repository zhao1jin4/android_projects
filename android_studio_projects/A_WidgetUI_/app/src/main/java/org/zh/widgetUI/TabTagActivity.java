package org.zh.widgetUI;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import org.zh.widgetUI.R;

//我自己新加的
public class TabTagActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_tag);
		setTitle("Tab 标签形式");

		TabHost tabHost = (TabHost) this.findViewById(R.id.myTabhost);
		tabHost.setup();//会去读 android:id="@android:id/tabcontent" , android:id="@android:id/tabs"

		TabSpec spec1=tabHost.newTabSpec("tag1");
		spec1.setIndicator("第一页",getResources().getDrawable(R.mipmap.ic_launcher));//标题一起的图标,minSdkVersion="15" 时无效果
		spec1.setContent(R.id.page1);
		tabHost.addTab(spec1);

		TabSpec spec2=tabHost.newTabSpec("tag2");

		View tabHeader=getLayoutInflater().inflate(R.layout.tab_custom_item,null);//自定义背景
		TextView textView=(TextView)tabHeader.findViewById(R.id.tagText);
		textView.setText("动态标题"); //没有选中的高亮区

		spec2.setIndicator(tabHeader);

		spec2.setContent(R.id.page2);
		tabHost.addTab(spec2);

		tabHost.setCurrentTab(0);

	}
}
