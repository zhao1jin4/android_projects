package test.org.zhaojin.file;

import java.io.File;

import junit.framework.Assert;
import android.os.Environment;
import android.test.AndroidTestCase;

public class FileTest extends AndroidTestCase {
	public void testSDCard()
	{
		//�ж�sdcard�Ƿ�������ֻ���
		boolean mounted=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		File dir=null;
		if(mounted)
			dir=Environment.getExternalStorageDirectory();//�õ�SD����Ŀ¼
		Assert.assertTrue(mounted);
		
		
	}
}
