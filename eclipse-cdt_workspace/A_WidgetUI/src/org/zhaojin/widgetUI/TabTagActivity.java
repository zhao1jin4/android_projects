package org.zhaojin.widgetUI;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

//���Լ��¼ӵ�
public class TabTagActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_tag);
		setTitle("Tab ��ǩ��ʽ");
		
		TabHost tabHost = (TabHost) this.findViewById(R.id.myTabhost);
		tabHost.setup();//��ȥ�� android:id="@android:id/tabcontent" , android:id="@android:id/tabs"
		
		TabSpec spec1=tabHost.newTabSpec("tag1");
		spec1.setIndicator("��һҳ",getResources().getDrawable(R.drawable.ic_launcher));//����һ���ͼ��,minSdkVersion="15" ʱ��Ч��
		spec1.setContent(R.id.page1);
		tabHost.addTab(spec1);
		
		TabSpec spec2=tabHost.newTabSpec("tag2");
		
		View tabHeader=getLayoutInflater().inflate(R.layout.tab_custom_item,null);//�Զ��屳��
		TextView textView=(TextView)tabHeader.findViewById(R.id.tagText);
		textView.setText("��̬����"); //û��ѡ�еĸ�����
		
		spec2.setIndicator(tabHeader);
		
		spec2.setContent(R.id.page2);
		tabHost.addTab(spec2);
		
		tabHost.setCurrentTab(0);
		
	}
}
