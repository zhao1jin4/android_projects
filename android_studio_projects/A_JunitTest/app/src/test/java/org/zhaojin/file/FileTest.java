package test.org.zhaojin.file;

import java.io.File;

import junit.framework.Assert;
import android.os.Environment;
import android.test.AndroidTestCase;

public class FileTest extends AndroidTestCase {
	public void testSDCard()
	{
		//判断sdcard是否存在于手机上
		boolean mounted=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		File dir=null;
		if(mounted)
			dir=Environment.getExternalStorageDirectory();//得到SD卡的目录
		Assert.assertTrue(mounted);


	}
}
