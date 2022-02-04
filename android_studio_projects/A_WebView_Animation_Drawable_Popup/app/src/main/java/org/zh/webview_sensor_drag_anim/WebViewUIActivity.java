package org.zh.webview_sensor_drag_anim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zh.webview_sensor_drag_anim.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class WebViewUIActivity extends Activity
{
	WebView webView=null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		webView=(WebView)this.findViewById(R.id.webView);

		//从API17开始，JS只能访问带有 @JavascriptInterface注解的Java函数。
		webView.addJavascriptInterface(new MyJavaScriptPlugin(),"myContact");//JS中使用myContact.xx

		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/index.html") ;//android_asset对应assets目录
	}

	class MyJavaScriptPlugin
	{
		@JavascriptInterface
		public void queryAllContacts()//被JS调用
		{
			JSONArray array=new JSONArray();
			try {
				for (int i=0;i<3;i++)
				{
					JSONObject obj=new JSONObject();
					obj.put("id", 10+i);
					obj.put("name", "张三"+i);
					obj.put("mobile", "1302018111"+i);
					array.put(obj);
				}
				String param=array.toString();
				//webView.loadUrl("javascript:show('"+param+"')");//报错
				// java.lang.Throwable: A WebView method was called on thread 'JavaBridge'. All WebView methods must be called on the same thread. (Expected Looper Looper (main, tid 2) {3afa8a9} called on Looper (JavaBridge, tid 10687) {2d03620}, FYI main Looper is Looper (main, tid 2) {3afa8a9})
				webView.post(new Runnable() {
					@Override
					public void run() {
						webView.loadUrl("javascript:show('"+param+"')");
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@JavascriptInterface
		public void call(String mobile)//被JS调用
		{
			Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mobile));
			startActivity(intent);
		}
	}
}
