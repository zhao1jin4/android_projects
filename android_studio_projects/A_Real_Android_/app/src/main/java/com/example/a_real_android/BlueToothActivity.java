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

import android.Manifest;
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
import android.content.pm.PackageManager;
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

import androidx.core.app.ActivityCompat;

public class BlueToothActivity extends Activity
{
	BluetoothAdapter adapter;//被内部类仿问时最好不声明private
	Set<BluetoothDevice> allClient=new HashSet<BluetoothDevice>();
	Map<String,String> availabelDevice=new HashMap<String,String> ();
	BluetoothDevice serverDevice;
	boolean isServer;
	TextView blueToothChatTextView;
	ListView scanedBluetoothListView;
	SimpleAdapter listAdapter;
	String selectBluetooth;

	List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();

	Handler handler=new Handler ()//会把当前线程的消息队列 关联到 自己的父线程的消息队列(UI线程)
	{
		//重写
		public void handleMessage(Message msg)//UI线程取出消息,执行这里的代码,即是在UI线程中执行的, Handler handler_cc=msg.getTarget();
		{
			if(msg.what == 1)
				blueToothChatTextView.append("\n"+msg.obj.toString());//msg.obj;//是obtainMessage(id,obj);放入的
		}
	};

	//设置->蓝牙->菜单后有 Visibility timeout设置时间
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth);

		adapter= BluetoothAdapter.getDefaultAdapter();//本机的蓝牙设备
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
					Toast.makeText(BlueToothActivity.this, "本机没有蓝牙设备", Toast.LENGTH_LONG).show();
					return;
				}

				if(! adapter.isEnabled())
				{
					Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);//有界面提示用户使用蓝牙设备,打开蓝牙权限请求,如选Yes也提示Visible on
					startActivity(intent);
				}
				Set<BluetoothDevice> devices=adapter.getBondedDevices();//本机已经配对的蓝牙设备
				StringBuffer all=new StringBuffer();
				for (Iterator iterator = devices.iterator(); iterator.hasNext();)
				{
					BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
					all.append(bluetoothDevice.getName()+"_"+bluetoothDevice.getAddress());//MAC地址
				}
				Toast.makeText(BlueToothActivity.this, "本机已经配对的蓝牙设备"+all.toString(), Toast.LENGTH_LONG).show();
			}
		});

		Button visibleBlueTooth = (Button) findViewById(R.id.visibleBlueToothButton);
		visibleBlueTooth.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);//可见的
				intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,120);//可见时间,单位秒,最多300
				startActivity(intent);//有Toast/(每次交互按钮允许或拒绝)提示用户开启时间
			}
		});

		IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);//只接收发现蓝牙的广播,ACTION_DISCOVERY_STARTED,ACTION_DISCOVERY_FINISHED
		this.registerReceiver(new BroadcastReceiver()
							  {
								  public void onReceive(Context context, Intent intent)
								  {
									  BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
									  Map<String, Object> newRow=new HashMap<String, Object>();
									  newRow.put("allBluetoothKey", device.getName());
									  listData.add(newRow);
									  listAdapter.notifyDataSetChanged();
									  //Toast.makeText(BlueToothActivity.this, "已扫描到蓝牙设备:"+device.getName()+"_"+device.getAddress(), Toast.LENGTH_LONG).show();

									  availabelDevice.put(device.getName(), device.getAddress());
								  }
							  }
				, filter);
		// adapter.cancelDiscovery();// 取消搜索

		Button scanBlueTooth = (Button) findViewById(R.id.scanBlueToothButton);
		scanBlueTooth.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				if(adapter!=null){
					//动态申请权限
					if(ActivityCompat.checkSelfPermission(BlueToothActivity.this, Manifest.permission.BLUETOOTH_SCAN)!= PackageManager.PERMISSION_GRANTED)
					{
						ActivityCompat.requestPermissions(BlueToothActivity.this,new String[]{Manifest.permission.BLUETOOTH_SCAN},1);
					}
					adapter.startDiscovery();//扫描其它蓝牙设备,是一个异步调用,每扫描到一个蓝牙,会发送一个广播,???哪里收？？
				}

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
							serverSocket = adapter.listenUsingRfcommWithServiceRecord("Server",UUID.randomUUID());//服务端
							//blueToothChatTextView.append("服务启动成功,正在监听...");//不能在子线程仿问主线程UI

							Message msg=new Message();//msg=handler.obtainMessage(id,obj);//建立消息的两种方
							msg.what=1;//为了有多个消息在handleMessage方法中区分
							msg.obj="服务启动成功,正在监听...";
							handler.sendMessage(msg);//发送到UI线程的消息队列

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
							.setMessage("请选择服务端的蓝牙设备");
					builder.create();
					builder.show();
					//Toast.makeText(BlueToothActivity.this, "请选择服务端的蓝牙设备", Toast.LENGTH_LONG).show();

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

				//动态申请权限
//				if(ActivityCompat.checkSelfPermission(BlueToothActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)
//				{
//					ActivityCompat.requestPermissions(BlueToothActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},1);
//				}
				//String meid=tm.getMeid();
				String imei=tm.getImei();//IMEI ,要READ_PHONE_STATE权限
				//String imei = tm.getDeviceId();//过时

				String sn = tm.getSimSerialNumber();//序列号

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
						socket = serverDevice.createRfcommSocketToServiceRecord(UUID.randomUUID());//客户端
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

	public void sendAll(String strSend)//向群中所有连接的客户端群发，通知改变
	{
		for (Iterator iterator = allClient.iterator(); iterator.hasNext();)
		{
			BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
			BluetoothSocket socket=null;
			try
			{
				socket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.randomUUID());//客户端
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
