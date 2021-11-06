package org.zh.oauth1;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

import org.zh.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class OAuth1MainActivity extends Activity
{
	/*
官方的说明
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
            //把RequestToken写入consumer,返回accessToken的URL
            //回调对应Android的Activity的URL，是在用户输入用户名，密码后发启动哪个Activity
            //Signpost即会调　intent.setData(Uri.parse("myproto://good"));

            String reqToken= consumer.getToken();//返回RequestToken
            System.out.println(reqToken);
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));//启动流览器
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| //如果Activity已经在History stack 的顶部,那么不启动Activity
                    Intent.FLAG_ACTIVITY_NO_HISTORY);//新的Activity不保存在History stack中
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
