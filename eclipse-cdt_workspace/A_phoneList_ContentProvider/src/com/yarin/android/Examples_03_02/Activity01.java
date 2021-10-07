 package com.yarin.android.Examples_03_02;  
  

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;
  
 public class Activity01 extends Activity  
 {  
	 
	 
	 class MyContentObserver extends ContentObserver
		{

			public MyContentObserver(Handler handler) {
				super(handler);
			}

			@Override
			public void onChange(boolean selfChange) {
				//super.onChange(selfChange);
				Log.w("ContentProviderTest", "onChange-------");
			}
		}
	 
     public void onCreate(Bundle savedInstanceState)  
     {  
         TextView tv = new TextView(this);  
         String string = "";       
         super.onCreate(savedInstanceState);   
         
         
         //联系人存放在/data/data/com.android.providers.contacts/databases/contacts2.db文件中的raw_contacts和data表中
         ContentResolver contentResolver = getContentResolver();    
         Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);  //能得到ID,和名字,因只查raw_contacts表
         while(cursor.moveToNext())   
         {   
             int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);//或者使用PhoneLookup.DISPLAY_NAME       
             String contact = cursor.getString(nameFieldColumnIndex);   
             
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)); 
  			Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, //data表为电话的记录
  							  null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null);  
  			String allNumber="";
  			while(phones.moveToNext())
  			{
				String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				allNumber=allNumber+phoneNumber+",";
			}
  			phones.close();
  			
  			String allEmail="";
  			Cursor emails = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,  
				       				 ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);  
			while (emails.moveToNext())
			{  
		        String email = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
		        allEmail=allEmail+email+",";
			} 
			emails.close();
			
            string += ( contact+":number="+allNumber+"Email="+allEmail+"\n");  
         }  
         cursor.close();  
         

         
         //设置TextView显示的内容  
         tv.setText(string);  
         //显示到屏幕  
         setContentView(tv);  
         
         
         //---------------对uri监听
         Uri uri=Uri.parse("content://this.is.provider.unique.id/person");
         this.getContentResolver().registerContentObserver(uri, true,new MyContentObserver(new Handler()));
         
         
       // startManagingCursor(cursor); 
         
         
     }  
 } 