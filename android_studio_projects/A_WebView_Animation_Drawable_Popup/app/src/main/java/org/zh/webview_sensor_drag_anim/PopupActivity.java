package org.zh.webview_sensor_drag_anim;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;


public class PopupActivity  extends Activity{
	PopupWindow popup;
	View window;
	Button button;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.popup_parent);

		window=this.findViewById(R.id.popupWindow);
		button=(Button)this.findViewById(R.id.popupBtn);

		//LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutInflater inflater = this.getLayoutInflater();
		View view=inflater.inflate(R.layout.popup,null);

		popup=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		//popup=new PopupWindow(view,300,300);//对按钮

		popup.setFocusable(true);
		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

		//popup.setBackgroundDrawable(new BitmapDrawable());//BitmapDrawable空参数过时的,选择其它地方,popup自动消失
		popup.setBackgroundDrawable(new BitmapDrawable(getResources(),bitmap));


		Button dispearButton=(Button)	view.findViewById(R.id.dispearPopupBtn);
		dispearButton.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v) {
				if(popup.isShowing())
					popup.dismiss();
			}
		});
		popup.setAnimationStyle(R.style.popupAnim);
	}
	public void popupWindow(View v)
	{
		//button 要放在外面,生命周期
		//popup.showAsDropDown(button, button.getWidth()/2, -button.getHeight()/2);//显示一个组件在旁边,当是右键菜单
		popup.showAtLocation(window, Gravity.BOTTOM|Gravity.LEFT, 10, 10);//使用父容器来定位

	}
}
