package test.org.zhaojin.pull;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.zhaojin.xml.Person;
import org.zhaojin.xml.PullXMLService;

import android.content.Context;
import android.test.AndroidTestCase;

public class PullXMLTest extends AndroidTestCase {
	public void testParseXML() throws Exception////方法 名必须以test开头 
	{

		PullXMLService service=new PullXMLService();
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/test/org/zhaojin/pull/pullPerson.xml");
		List<Person> persons=service.parsePullXML(inputStream);
		Assert.assertEquals("liming", persons.get(0).getName());
	}
	public void  testWriteXML() throws Exception {
		PullXMLService service=new PullXMLService();
		List<Person> persons=new ArrayList<Person>();
		Person p=new Person();
		p.setAge((short)25);
		p.setId(1001);
		p.setName("中文名");
		persons.add(p);
		
		 // 在/data/<package>/files/目录中
		OutputStream output=this.getContext().openFileOutput("myPerson.xml",
				Context.MODE_PRIVATE |Context.MODE_APPEND
				);
		service.writePullXML(persons,output);
		
		File file=new File(this.getContext().getFilesDir(),"myPerson.xml");
		Assert.assertTrue(file.exists());
	}
}
