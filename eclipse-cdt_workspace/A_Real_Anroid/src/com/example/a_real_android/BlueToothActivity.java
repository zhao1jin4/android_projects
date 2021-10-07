package com.example.a_real_android;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BlueToothActivity extends Activity
{
	BluetoothAdapter adapter;//���ڲ������ʱ��ò�����private 
	Set<BluetoothDevice> allClient=new HashSet<BluetoothDevice>();
	Map<String,String> availabelDevice=new HashMap<String,String> ();
	BluetoothDevice serverDevice;	
	boolean isServer;
	TextView blueToothChatTextView;
	ListView scanedBluetoothListView;
	SimpleAdapter listAdapter;
	String selectBluetooth;
	
	List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
	
	 Handler handler=new Handler ()//��ѵ�ǰ�̵߳���Ϣ���� ������ �Լ��ĸ��̵߳���Ϣ����(UI�߳�)
	 {
	 	//��д
	 	public void handleMessage(Message msg)//UI�߳�ȡ����Ϣ,ִ������Ĵ���,������UI�߳���ִ�е�, Handler handler_cc=msg.getTarget();
	 	{
	 		if(msg.what == 1)
	 			blueToothChatTextView.append("\n"+msg.obj.toString());//msg.obj;//��obtainMessage(id,obj);�����
	 	}
	 };
	
	//����->����->�˵����� Visibility timeout����ʱ��
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        
        adapter= BluetoothAdapter.getDefaultAdapter();//�����������豸
        blueToothChatTextView = (TextView) findViewById(R.id.blueToothChatTextView);
         
        listAdapter = new SimpleAdapter(this, listData, android.R.layout.simple_list_item_1, new String[] { "allBluetoothKey" },  new int[] { android.R.id.text1 });
		   
        scanedBluetoothListView = (ListView) findViewById(R.id.scanedBluetoothListView);
//        scanedBluetoothListView.setItemsCanFocus(true);
//        scanedBluetoothListView.setSelected(true);
//        scanedBluetoothListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        scanedBluetoothListView.setAdapter(listAdapter);
        scanedBluetoothListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		for (int i=0;i<parent.getChildCount();i++)
        		{
        			View temp=parent.getChildAt(i);
        			temp.setSelected(false);
        			temp.setBackgroundColor(Color.WHITE);
        		}
        		view.setSelected(true);
        		view.setBackgroundColor(Color.BLUE);
        		TextView t=(TextView)view;
        		selectBluetooth=t.getText().toString();
			}
		});
        
        
        Button viewBlueTooth = (Button) findViewById(R.id.viewBlueToothButton);
        viewBlueTooth.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		if(adapter==null)
			        		{
			        			Toast.makeText(BlueToothActivity.this, "����û�������豸", Toast.LENGTH_LONG).show();
			        			return;
			        		}
			        		
			        		if(! adapter.isEnabled())
		        			{
		        				Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);//�н�����ʾ�û�ʹ�������豸,������Ȩ������,��ѡYesҲ��ʾVisible on
		        				startActivity(intent);
		        			}
		        			Set<BluetoothDevice> devices=adapter.getBondedDevices();//�����Ѿ���Ե������豸
		        			StringBuffer all=new StringBuffer();
		        			for (Iterator iterator = devices.iterator(); iterator.hasNext();)
		        			{
								BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
								all.append(bluetoothDevice.getName()+"_"+bluetoothDevice.getAddress());//MAC��ַ
							}
		        			Toast.makeText(BlueToothActivity.this, "�����Ѿ���Ե������豸"+all.toString(), Toast.LENGTH_LONG).show();			        			
			        	}
			        });
        
        Button visibleBlueTooth = (Button) findViewById(R.id.visibleBlueToothButton);
        visibleBlueTooth.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);//�ɼ���
			        		intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,120);//�ɼ�ʱ��,��λ��,���300
	        				startActivity(intent);//��Toast��ʾ�û�����ʱ��
			        	}
			        });
        
        IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);//ֻ���շ��������Ĺ㲥,ACTION_DISCOVERY_STARTED,ACTION_DISCOVERY_FINISHED
        this.registerReceiver(new BroadcastReceiver() 
        		{
		    		public void onReceive(Context context, Intent intent) 
		    		{ 
		    			BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		    			Map<String, Object> newRow=new HashMap<String, Object>();
		    			newRow.put("allBluetoothKey", device.getName());
		    			listData.add(newRow);
		    			listAdapter.notifyDataSetChanged();
		    			//Toast.makeText(BlueToothActivity.this, "��ɨ�赽�����豸:"+device.getName()+"_"+device.getAddress(), Toast.LENGTH_LONG).show();
		    			
		    			availabelDevice.put(device.getName(), device.getAddress());
		    		}
		        }
		        , filter);
    	// adapter.cancelDiscovery();// ȡ������
        
        Button scanBlueTooth = (Button) findViewById(R.id.scanBlueToothButton);
        scanBlueTooth.setOnClickListener(new Button.OnClickListener() 
			        {
			        	public void onClick(View v) 
			        	{
			        		if(adapter!=null)
			        			adapter.startDiscovery();//ɨ�����������豸,��һ���첽����,ÿɨ�赽һ������,�ᷢ��һ���㲥
			        	}
			        });
       
		 
        //==========group chat
       
        Button blueToothServerButton = (Button)findViewById(R.id.blueToothServerButton);
        blueToothServerButton.setOnClickListener(new Button.OnClickListener() 
        {
        	public void onClick(View v) 
        	{
        		 isServer=true;
        		 Runnable run1=new Runnable(){ 
        		

					public void run() { 
						
		    			BluetoothServerSocket  serverSocket=null;
		    			try{
		    				serverSocket = adapter.listenUsingRfcommWithServiceRecord("Server",UUID.randomUUID());//�����
		    				//blueToothChatTextView.append("���������ɹ�,���ڼ���...");//���������̷߳������߳�UI
		    				 
		    				Message msg=new Message();//msg=handler.obtainMessage(id,obj);//������Ϣ�����ַ�
		    				msg.what=1;//Ϊ���ж����Ϣ��handleMessage����������
		    				msg.obj="���������ɹ�,���ڼ���...";
		    				handler.sendMessage(msg);//���͵�UI�̵߳���Ϣ����
		    				
			    			while(true)
			    			{
								BluetoothSocket receiveSocket   = serverSocket.accept();
								BluetoothDevice clientDev=receiveSocket.getRemoteDevice();
								if( ! allClient.contains(clientDev) )
									allClient.add(clientDev);
								BufferedReader reader=new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
								
								StringBuffer receiveStr=new StringBuffer();
								String line;
								while((line=reader.readLine())!=null)
								{
									receiveStr.append(line);
									receiveStr.append("\n");
								}
								sendAll(receiveStr.toString());
								blueToothChatTextView.setText(blueToothChatTextView.getText()+receiveStr.toString());
			    			}
			    			
			        	} catch (IOException e) {
							e.printStackTrace();
						}finally{
							try { serverSocket.close(); 
							} catch (IOException e) { e.printStackTrace(); }
						}
		    			
					}
         	 		
         	 	};
         	 	
         	 	new Thread(run1).start();
         	 	
       		}
        });
        Button blueToothClientButton = (Button)findViewById(R.id.blueToothClientButton);
        blueToothClientButton.setOnClickListener(new Button.OnClickListener() 
        {
        	public void onClick(View v) 
        	{
        		isServer=false;
        		 if(selectBluetooth==null)
        		 {
        				AlertDialog.Builder builder=	new AlertDialog.Builder(BlueToothActivity.this)
            			.setMessage("��ѡ�����˵������豸");
            			builder.create();
            			builder.show();
            			//Toast.makeText(BlueToothActivity.this, "��ѡ�����˵������豸", Toast.LENGTH_LONG).show();
            			
        		 }else
        		 {
        			 serverDevice= adapter.getRemoteDevice(availabelDevice.get(selectBluetooth));
        		 }
       		}
        });
        Button blueToothSendButton = (Button)findViewById(R.id.blueToothSendButton);
        blueToothSendButton.setOnClickListener(new Button.OnClickListener() 
        {
        	public void onClick(View v) 
        	{ 
        		TelephonyManager tm = (TelephonyManager) BlueToothActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
    			String imei = tm.getDeviceId();//IMEI ,Ҫandroid.permission.READ_PHONE_STATE
    			String sn = tm.getSimSerialNumber();//���к�
    			
    			EditText blueToothChatEditText = (EditText) findViewById(R.id.blueToothChatEditText);
        		String strSend=sn+":"+blueToothChatEditText.getText().toString();
        		blueToothChatEditText.setText("");
        		 
        		if(isServer) //server->all client
        		 {
        			blueToothChatTextView.setText(blueToothChatTextView.getText()+"\n"+strSend);
        			sendAll(strSend);
        		 }else //client->server
        		 {
        			 BluetoothSocket socket=null;
        			 try{
	        			socket = serverDevice.createRfcommSocketToServiceRecord(UUID.randomUUID());//�ͻ���
	 					socket.connect();
	 					OutputStream output=socket.getOutputStream();
	 					output.write(strSend.getBytes()); 
	 				} catch (IOException e) {
	 					e.printStackTrace();
	 				}finally{
	 					try { socket.close(); 
	 					} catch (IOException e) { e.printStackTrace(); }
	 				}
	 			 } 
        	}
        });
    }
    
    public void sendAll(String strSend)//��Ⱥ���������ӵĿͻ���Ⱥ����֪ͨ�ı�
    {
    	for (Iterator iterator = allClient.iterator(); iterator.hasNext();)
			{
				BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
				BluetoothSocket socket=null;
				try 
				{
					socket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.randomUUID());//�ͻ���
					socket.connect();
					OutputStream output=socket.getOutputStream();
					output.write(strSend.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try { socket.close(); 
					} catch (IOException e) { e.printStackTrace(); }
				}
			
			} 
 	 	
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
