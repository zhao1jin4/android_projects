package org.zhaojin.oauth1;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

import org.zhaojin.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class OAuth1MainActivity extends Activity 
{
	/*
�ٷ���˵��
Google Android
IMPORTANT: Do NOT use the DefaultOAuth* implementations on Android,
	Instead, use the CommonsHttpOAuth* classes, 
	used with Apache Commons HTTP 

	 */

	OAuthConsumer consumer;
	OAuthProvider provider;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauth1);
        consumer= new CommonsHttpOAuthConsumer(Constant.KEY,Constant.SECRET);
        provider= new CommonsHttpOAuthProvider(Constant.REQUEST_URL,Constant.ACCESS_URL,Constant.AUTH_URL);
       
        try {
			String url=provider.retrieveRequestToken(consumer, "myproto://good");
			//��RequestTokenд��consumer,����accessToken��URL
			//�ص���ӦAndroid��Activity��URL�������û������û���������������ĸ�Activity
			//Signpost�������intent.setData(Uri.parse("myproto://good"));
			
			String reqToken= consumer.getToken();//����RequestToken
			System.out.println(reqToken);
			Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));//����������
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| //���Activity�Ѿ���History stack �Ķ���,��ô������Activity
							Intent.FLAG_ACTIVITY_NO_HISTORY);//�µ�Activity��������History stack��
			this.startActivity(intent);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
