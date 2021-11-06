package org.zh.pull;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import  org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.zh.xml.Person;
import org.zh.xml.PullXMLService;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

@RunWith(AndroidJUnit4.class)
public class PullXMLTest   {

	@Test
	public void testParseXML() throws Exception
	{

		PullXMLService service=new PullXMLService();
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/test/org/zh/pull/pullPerson.xml");
		List<Person> persons=service.parsePullXML(inputStream);
		Assert.assertEquals("liming", persons.get(0).getName());
	}

	@Test
	public void  testWriteXML() throws Exception {
		PullXMLService service=new PullXMLService();
		List<Person> persons=new ArrayList<Person>();
		Person p=new Person();
		p.setAge((short)25);
		p.setId(1001);
		p.setName("李四");
		persons.add(p);

		 Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		 Context context=InstrumentationRegistry.getInstrumentation().getContext();
		 //  /data/<package>/files/
		OutputStream output=context.openFileOutput("myPerson.xml",
				Context.MODE_PRIVATE |Context.MODE_APPEND
				);
		service.writePullXML(persons,output);
		
		File file=new File(context.getFilesDir(),"myPerson.xml");
		Assert.assertTrue(file.exists());
	}
}
