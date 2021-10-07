package org.zhaojin.net;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class TestPostMethod extends TestCase {
	// extends AndroidTestCase {
	public void testPostJDK() throws Exception {
		// Bitmap map = BitmapFactory.decodeByteArray(data, offset, length);

		URL url = new URL("http://127.0.0.1:8080/test/index.jsp");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		String param = "name=" + URLEncoder.encode("张三", "UTF-8") + "&age=25";
		byte[] data = param.getBytes();
		conn.setRequestProperty("Content-Length", Integer.toString(data.length));
		OutputStream output = conn.getOutputStream();
		output.write(data);
		output.flush();
		output.close();
		int response = conn.getResponseCode();

		assertTrue(response == 200);

		/*
		 * 在 Tomcat <% String name=request.getParameter("name"); if(name!=null)
		 * { String result=new String(name.getBytes("iso8859-1"),"UTF-8");
		 * out.print(result); System.out.print(result); } %>
		 */

	}

	public void testPostByHttpClient() throws Exception {

		BasicNameValuePair pair = new org.apache.http.message.BasicNameValuePair(
				"name", "李四");
		List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
		param.add(pair);
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(param,
				"UTF-8");
		HttpPost post = new HttpPost("http://127.0.0.1:8080/test/index.jsp");
		post.setEntity(formEntity);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(post);
		String str=EntityUtils.toString(response.getEntity());
		int code = response.getStatusLine().getStatusCode();
		assertEquals(200, code);

	}


}
