package org.zh.file;

import java.io.File;

import android.content.Context;
import android.os.Environment;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FileTest   {

	@Test
	public void testSDCard()
	{
		Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();//生成代码使用的
		Context appContext=InstrumentationRegistry.getInstrumentation().getContext();

		//判断sdcard是否存在于手机上
		boolean mounted=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		File dir=null;
		if(mounted)
			//dir=Environment.getExternalStorageDirectory();//得到SD卡的目录,过时用context.getCacheDir()等
			dir=context.getCacheDir();
		Assert.assertTrue(mounted);


	}
}
