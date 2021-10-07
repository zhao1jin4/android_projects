package org.zhaojin.oauth1;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;

import org.zhaojin.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;

public class MyActivity extends Activity 
{
	OAuthProvider provider;
	OAuthConsumer consumer;
	
    @Override
	protected void onNewIntent(Intent intent) //��ӦlaunchMode="singleTask" 
    {
		super.onNewIntent(intent);
		Uri uri=intent.getData();
		String oauth_verifier=uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
		try {
			provider.retrieveAccessToken(consumer, oauth_verifier);//����Access����
			String accessToken=consumer.getToken();
			String accessTokenSecret=consumer.getTokenSecret();
		} catch ( Exception e) 
		{
			e.printStackTrace();
		}
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}