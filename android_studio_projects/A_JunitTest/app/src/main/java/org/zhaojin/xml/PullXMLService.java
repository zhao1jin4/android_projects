package org.zhaojin.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class PullXMLService 
{
	public List<Person> parsePullXML(InputStream inputStream) throws Exception 
	{
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inputStream, "UTF-8");
		int eventType = parser.getEventType();

		List<Person> persons = null;
		Person p = null;
		while (eventType != XmlPullParser.END_DOCUMENT) {

			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				persons = new ArrayList<Person>();
				break;
			case XmlPullParser.START_TAG:
				String tagName = parser.getName();
				if ("person".equals(tagName)) {
					p = new Person();
					p.setId(Integer.parseInt(parser.getAttributeValue(0)));
				}
				if ("name".equals(tagName)) {
					p.setName(parser.nextText());
				}

				break;
			case XmlPullParser.END_TAG:
				String endTagName = parser.getName();
				if ("person".equals(endTagName))
					persons.add(p);
				break;
			}

			eventType = parser.next();
		}
		return persons;

	}

	public void writePullXML(List<Person> persons,OutputStream out) throws Exception
	{

		
		XmlSerializer serializer=Xml.newSerializer();
		serializer.setOutput(out,"UTF-8");
		serializer.startDocument("UTF-8",true);
		serializer.startTag(null, "persons");
		for (Person p:persons) {
			serializer.startTag(null, "person");
			serializer.attribute(null,"id",Integer.toString(p.getId()));
			
			serializer.startTag(null, "name").text(p.getName()).endTag(null, "name");
			
			serializer.endTag(null, "person");
			
		}
		serializer.endTag(null, "persons");
		serializer.endDocument();
		out.close();
	}
}
