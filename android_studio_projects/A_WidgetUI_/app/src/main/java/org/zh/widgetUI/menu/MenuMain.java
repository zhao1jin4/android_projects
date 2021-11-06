package org.zh.widgetUI.menu;

import org.zh.widgetUI.R;

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
	public boolean onCreateOptionsMenu(Menu menu) { //按手机上的menu菜单时,编码菜单,
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM0, 4, "显示button1");//显示在屏幕的最下方中间的n行
		menu.add(0, ITEM1, 2, "显示button2");
		MenuItem item=menu.findItem(ITEM1);
		//item.setIcon(icon) //可以设置menu按钮的背景
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
		setTitle("button1 可见");
		button1.setVisibility(View.VISIBLE);
		btn_xmlMenu.setVisibility(View.INVISIBLE);
	}

	private void actionClickMenuItem2(){
		setTitle("button2 可见");
		button1.setVisibility(View.INVISIBLE);
		btn_xmlMenu.setVisibility(View.VISIBLE);
	}

	//android:onClick="showPopup",菜单位于按钮附近
	public void showPopup(View v)
	{
		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.popup, popup.getMenu());
		popup.show();
	}
}