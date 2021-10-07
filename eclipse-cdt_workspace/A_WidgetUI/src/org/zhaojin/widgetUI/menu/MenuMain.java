package org.zhaojin.widgetUI.menu;

import org.zhaojin.widgetUI.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;


public class MenuMain extends Activity {
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	Button button1;
	Button btn_xmlMenu;
	Button btn_actionModeMenu;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        
        button1 = (Button) findViewById(R.id.button1);
        button1.setVisibility(View.INVISIBLE);
        
        btn_xmlMenu = (Button) findViewById(R.id.btn_xmlMenu);
		btn_xmlMenu.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MenuMain.this, MenuXML.class);
				startActivity(intent);
			}
		});
		
		btn_actionModeMenu= (Button) findViewById(R.id.btn_actionModeMenu);
		btn_actionModeMenu.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MenuMain.this, MenuActionMode.class);
				startActivity(intent);
			}
		});
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) { //���ֻ��ϵ�menu�˵�ʱ,����˵�,
		super.onCreateOptionsMenu(menu);  
		menu.add(0, ITEM0, 4, "��ʾbutton1");//��ʾ����Ļ�����·��м��n��
		menu.add(0, ITEM1, 2, "��ʾbutton2");
		MenuItem item=menu.findItem(ITEM1);
		//item.setIcon(icon) //��������menu��ť�ı���
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ITEM0: 
			actionClickMenuItem1();
		break;
		case ITEM1: 
			actionClickMenuItem2(); break;

		}
		//return super.onOptionsItemSelected(item);
		return true;
	}
	 
	private void actionClickMenuItem1(){
		setTitle("button1 �ɼ�");
		button1.setVisibility(View.VISIBLE);
		btn_xmlMenu.setVisibility(View.INVISIBLE);
	}
	 
	private void actionClickMenuItem2(){
		setTitle("button2 �ɼ�");
		button1.setVisibility(View.INVISIBLE);
		btn_xmlMenu.setVisibility(View.VISIBLE);
	}
	
	//android:onClick="showPopup",�˵�λ�ڰ�ť����
	public void showPopup(View v) 
	{
	    PopupMenu popup = new PopupMenu(this, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.popup, popup.getMenu());
	    popup.show();
	}
}