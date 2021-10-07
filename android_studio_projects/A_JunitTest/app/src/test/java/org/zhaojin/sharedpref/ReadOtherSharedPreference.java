package test.org.zhaojin.sharedpref;

import java.util.Map;

import junit.framework.Assert;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.test.AndroidTestCase;
import android.util.Log;

public class ReadOtherSharedPreference  extends AndroidTestCase{
	public void testReadA_SharedPreferences() throws Exception
	{

		//如果自已是Activity还可以用

		//A_ShredPreferences 必须 是MODE_WORLD_READABLE
		Context otherContext=getContext().createPackageContext("us.imnet.iceskysl.db",Context.CONTEXT_IGNORE_SECURITY);
		SharedPreferences p= otherContext.getSharedPreferences("SETTING_Infos", Context.MODE_PRIVATE);
		Map all=p.getAll();
		Assert.assertTrue(all.size()>0);
	}
}
