package org.zhaojin.webview_sensor_drag_anim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewUIActivity extends Activity
{
	  WebView webView=null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        
        webView=(WebView)this.findViewById(R.id.webView);
        webView.addJavascriptInterface(new MyJavaScriptPlugin(),"myContact");//JS��ʹ��myContact.xx
		webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/index.html") ;//android_asset��ӦassetsĿ¼
    }

	class MyJavaScriptPlugin 
	{
		   public void queryAllContacts()//��JS���� 
		    {
		    	JSONArray array=new JSONArray();
		    	try {
		    		for (int i=0;i<3;i++)
		    		{
		    			JSONObject obj=new JSONObject();
		    			obj.put("id", 10+i);
		    			obj.put("name", "����"+i);
		    			obj.put("mobile", "1302018111"+i);
		    			array.put(obj);
		    		}
		    		String param=array.toString();
			    	webView.loadUrl("javascript:show('"+param+"')");
		    	} catch (JSONException e) {
		    		e.printStackTrace();
		    	}
		    }
		   public void call(String mobile)//��JS���� 
		   {
			   Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mobile));
			   startActivity(intent);
		   }
	}
}
