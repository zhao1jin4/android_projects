package org.zhaojin.ksoap2;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import org.zhaojin.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//����OK
public class KSoap2MainActivity extends Activity 
{
	//���� http://localhost:8080/J_CXF_Spring/ws/HelloWorld?wsdl
	public     String URL="http://10.103.35.146:8080/J_CXF_Spring/ws/HelloWorld";//����ʹ��127.0.0.��localhost
	public     String ACTION="";//�����ɵ�WSDL <soap:operation soapAction="" 
	public static final String NAMESPACE="http://server.spring.cxf.zhaojin.org/";
	public static final String METHOD="sayHi";
	
	 Button btn= null;
	 TextView txt=null;
	 String  resultTxt;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ksoap2);
      
        txt=(TextView)this.findViewById(R.id.werserviceTxt);
        btn= (Button)this.findViewById(R.id.werserviceBtn);
       
        btn.setOnClickListener(new Button.OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				final CountDownLatch latch=new CountDownLatch(1);
				Runnable run=new Runnable()
					{
						@Override
						public void run()//����������������߳���,����
						{
					        SoapObject obj=new SoapObject(NAMESPACE,METHOD);
					        obj.addProperty("arg0", "���� Ksoap2");//����������CXF�粻����,��arg0 , ����
					        
					        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);//CXFӦ����1.1�汾
					        envelope.bodyOut=obj;
					        envelope.setOutputSoapObject(obj);//������һ����
					        envelope.dotNet=false;//����C# .Net
					        
					        HttpTransportSE transport=new HttpTransportSE(URL);
					        transport.debug=true;
					        try {
								transport.call(ACTION, envelope);
							} catch (IOException e) {
								e.printStackTrace();
							} catch (XmlPullParserException e) {
								e.printStackTrace();
							}
					        SoapObject result=(SoapObject)envelope.bodyIn;//�õ����ؽ��
					        resultTxt=result.getProperty(0).toString();
					        
					        //txt.setText(result.getProperty(0).toString());//�����߳��в��ܷ���
					        //Toast.makeText(KSoap2MainActivity.this,"����:"+result.getProperty(0), Toast.LENGTH_LONG);//�����߳��в��ܷ���
					    	
					        latch.countDown();
						}
					};
				Thread t=new Thread(run);
				t.start();
			
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				txt.setText(resultTxt);
			}
		});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
