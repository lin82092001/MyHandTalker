package com.example.smartglovev2;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/*  宣告TextView_R與TextView_L物件 */

public class MainActivity extends AppCompatActivity									 		//	MainActivity類別
{																							//	進入MainActivity類別
	private final static String TAG = "MainActivity";
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	//  VOICE_RECOGNITION_REQUEST_CODE是自訂 因為當結果回傳時要告訴startActivityForResult是誰呼叫他
	private static final int UPDATE_SETTING_SUCCESS = 0x0001;
	private static final int UPDATE_SETTING_SU = 0x0002;
	public enum BT_STATE																	//	藍芽裝置狀態列舉(Program State enum)
	{																						//	進入BT_STATE列舉
		SELECT_BT_DEVICE, CONNECTING, CONNECTED												//	列出藍芽狀態
	};																						//	結束BT_STATE列舉
	//*****物件宣告******
	TextView TextView_R;																	//	宣告TextView物件TextView_R
	TextView TextView_L;																	//	宣告TextView物件TextView_L
	private TextView btinformation;															//	宣告btinformation物件為TextView，用於顯示藍芽資訊
	private TextView LHTV, RHTV;
	private TextView flage;
	private ListView mList;																	//	宣告ListView物件mList
	private ArrayList<TextView> left, right;												//	宣告ArrayList<TextView>物件left與right
	private Button conn;																	//	宣告連線按鈕物件(ui Button)
	// ui - bluetooth device list
	private ListView LHBTList;																//	建立LHBTList為ListView物件
	private ListView RHBTList;																//	建立RHBTList為ListView物件
	//*****bluetooth adapter*****
	private BluetoothAdapter BTadapter;														//	宣告BTadapter物件為藍芽BluetoothAdapter
	private ArrayAdapter<String> mDeviceArrayAdapter;										//	宣告mDeviceArrayAdapter為ArrayAdapter<String>
	// Program now state
	private BT_STATE state = BT_STATE.SELECT_BT_DEVICE;										//	宣告state變數為BT_STATE列舉型態
	// selected Left hand bluetooth device address
	private String LHAddress = "";															//	宣告LHAddress字串變數記錄左手藍芽裝置位置
	// selected right hand bluetooth device address
	private String RHAddress = "";															//	宣告RHAddress字串變數記錄右手藍芽裝置位置
	private boolean secure = true;															//	宣告secure為布林變數，初始值為true
	// Unique UUID for this application
	private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private static final UUID MY_UUID_INSECURE = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
	// left hand bluetooth socket , right hand bluetooth socket
	private BluetoothSocket LHBS, RHBS;														//	宣告左右手藍芽BluetoothSocket物件
	// communication Thread
	private Comm a, b;
	// 資料處理執行緒
	private DataProcess dataProcess;
	MyHandler handler = new MyHandler();
	private String word_a;
	//---Smart Glove V2編碼處理用變數---
	private TextView ShowWord;
	//切換語言
	public Switch lang;
	public static String langSetting = "";
	Vocabulary Vocabulary1;

	@Override
	protected void onStart() 																//	onStart方法
	{																						//	進入onStart方法
		super.onStart();																	//	APP啟動

		Set<BluetoothDevice> btBondedSet = BTadapter.getBondedDevices();
		if (btBondedSet.size() > 0) {
			for (BluetoothDevice device : btBondedSet) {
				mDeviceArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}

		if (this.BTadapter == null) {
			Toast.makeText(this, "本裝置不支援藍芽連線功能", Toast.LENGTH_LONG).show();

			this.finish();
			return;
		}
		if (requireBluetooth()) {
			showBTinformation();
		}

	}
	@Override
	protected void onCreate(Bundle savedInstanceState)										//  程式開啟時執行
	{																						//  進入onCreate程式
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);					//	設定應用程式視窗顯示模式為FEATURE_INDETERMINATE_PROGRESS
		setContentView(R.layout.activity_main);
		//*****連結物件*****()
		TextView_R=(TextView) findViewById(R.id.TextView_R);								//  將Layout物件TextView_R與該程式宣告物件TextView_R連結
		TextView_L=(TextView) findViewById(R.id.TextView_L);								//  將Layout物件TextView_L與該程式宣告物件TextView_L連結
		LHTV = (TextView) this.findViewById(R.id.LHTextView);
		RHTV = (TextView) this.findViewById(R.id.RHTextView);
		btinformation = (TextView) this.findViewById(R.id.BTinformationTextView);			//  將BTinformationTextView與該程式宣告物件btinformation連結
		Button speakButton = (Button) findViewById(R.id.BT_GV);								//  將Layout物件speakButton與該程式宣告物件BT_GV連結
		mList = (ListView) findViewById(R.id.LV_GV);										//  將Layout物件LV_GV與該程式宣告物件mList連結
		LHBTList = (ListView) this.findViewById(R.id.LHBTListView);							//  將LHBTListView與該程式物件LHBTList連結
		RHBTList = (ListView) this.findViewById(R.id.RHBTListView);							//  將RHBTListView與該程式物件RHBTList連結
		ShowWord = (TextView) this.findViewById(R.id.textView2);
		flage=(TextView)findViewById(R.id.flage);
		lang=(Switch)findViewById(R.id.btnchange);											//  將btnchange與該程式物件lang連結
		conn = (Button) findViewById(R.id.btnok);											//  將btnok與該程式物件conn連結

		conn.setOnClickListener(new OnClickListener()										//  若conn按鈕被按下
		{																					//  進入OnClickListener程式
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				switch (state) {
					case SELECT_BT_DEVICE:
						// 沒有選擇裝置，跳緊告
						if (LHAddress == "" || RHAddress == "") {
							new AlertDialog.Builder(MainActivity.this).setTitle("Warning").setMessage("沒有選擇藍芽裝置").setPositiveButton("確定", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
								}
							}).show();
							break;
						}

						// 選擇相同裝置也跳警告
						if (LHAddress.equals(RHAddress)) {
							new AlertDialog.Builder(MainActivity.this).setTitle("Warning").setMessage("不能選擇相同的藍芽裝置").setPositiveButton("確定", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method
									// stub
								}
							}).show();
							break;
						}

						// 宣告兩個藍芽裝置
						BluetoothDevice d1,
								d2;
						try {
							d1 = BTadapter.getRemoteDevice(LHAddress);
							d2 = BTadapter.getRemoteDevice(RHAddress);
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "裝置未開啟", Toast.LENGTH_LONG).show();
							return;
						}

						conn.setText("連線中...請稍後");

						findViewById(R.id.devicelayout).setVisibility(View.GONE);
						LHBTList.setEnabled(false);
						RHBTList.setEnabled(false);
						state = BT_STATE.CONNECTING;

						BTadapter.cancelDiscovery();

						BluetoothSocket tmp;
						try {
							if (secure) {
								tmp = d1.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
							} else {
								tmp = d1.createInsecureRfcommSocketToServiceRecord(MY_UUID_INSECURE);
							}
						} catch (IOException e) {
							Toast.makeText(getApplicationContext(), "連接到" + d1.getName() + "發生錯誤", Toast.LENGTH_LONG);
							((LinearLayout) findViewById(R.id.devicelayout)).setVisibility(View.VISIBLE);
							conn.setText("連線");
							return;
						}
						LHBS = tmp;

						tmp = null;
						try {
							if (secure) {
								tmp = d2.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
							} else {
								tmp = d2.createInsecureRfcommSocketToServiceRecord(MY_UUID_INSECURE);
							}
						} catch (IOException e) {
							Toast.makeText(getApplicationContext(), "連接到" + d2.getName() + "發生錯誤", Toast.LENGTH_LONG);
							((LinearLayout) findViewById(R.id.devicelayout)).setVisibility(View.VISIBLE);
							conn.setText("連線");
							return;
						}
						RHBS = tmp;

						try {
							LHBS.connect();
							RHBS.connect();
						} catch (IOException e) {
							Toast.makeText(getApplicationContext(), "連線失敗", Toast.LENGTH_LONG);
							((LinearLayout) findViewById(R.id.devicelayout)).setVisibility(View.VISIBLE);
							conn.setText("連線");
							try {
								LHBS.close();
							} catch (IOException e1) {
							}
							try {
								RHBS.close();
							} catch (IOException e1) {
							}
							return;
						}

						a = new Comm(LHBS, "Left");
						b = new Comm(RHBS, "Right");

						a.setView(left);
						b.setView(right);

						dataProcess = new DataProcess(a, b);

						a.start();
						b.start();
						dataProcess.start();
						state = BT_STATE.CONNECTED;
						conn.setText("關閉連線");
						ShowWord.setText("");
						break;
					case CONNECTING:
						break;
					case CONNECTED:
						dataProcess.isRun = false;
						a.isrun = false;
						b.isrun = false;

						a.interrupt();
						b.interrupt();
						dataProcess.interrupt();

						a = null;
						b = null;
						dataProcess = null;
						Vocabulary1 = null;

						((LinearLayout) findViewById(R.id.devicelayout)).setVisibility(View.VISIBLE);
						state = BT_STATE.SELECT_BT_DEVICE;
						conn.setText("連線");
						LHBTList.setEnabled(true);
						RHBTList.setEnabled(true);
						break;
				}
			}
		});																					//  結束OnClickListener程式

		lang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if(compoundButton.isChecked())
				{
					langSetting = "En";
				}
				else
				{
					langSetting = "Ch";
				}
			}
		});


		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() != 0)
		{																					//  進入if敘述
			speakButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)													//  onClick程式，speakButton被按下時觸發
				{																			//  進入onClick程式
					// TODO Auto-generated method stub
					startVoiceRecognitionActivity();										//  呼叫startVoiceRecognitionActivity副程式，開始語音辨識
				}																			//  結束onClick程式
			});
		}																					//  結束if敘述
		else
		{																					//  進入else敘述
			speakButton.setEnabled(false);
			speakButton.setText("Recognizer not present");
		}																					//  結束else敘述
		left = new ArrayList<TextView>();													//  建立left物件(ArrayList<TextView>型態)
		right = new ArrayList<TextView>();													//  建立right物件(ArrayList<TextView>型態)
		left.add(((TextView) findViewById(R.id.ltv1)));										//  新增項目至left ArrayList
		left.add(((TextView) findViewById(R.id.ltv2)));										//  新增項目至left ArrayList
		left.add(((TextView) findViewById(R.id.ltv3)));										//  新增項目至left ArrayList
		left.add(((TextView) findViewById(R.id.ltv4)));										//  新增項目至left ArrayList
		left.add(((TextView) findViewById(R.id.ltv5)));										//  新增項目至left ArrayList

		right.add((TextView) findViewById(R.id.rtv1));										//  新增項目至right ArrayList
		right.add((TextView) findViewById(R.id.rtv2));										//  新增項目至right ArrayList
		right.add((TextView) findViewById(R.id.rtv3));										//  新增項目至right ArrayList
		right.add((TextView) findViewById(R.id.rtv4));										//  新增項目至right ArrayList
		right.add((TextView) findViewById(R.id.rtv5));										//  新增項目至right ArrayList

		mDeviceArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		this.BTadapter = BluetoothAdapter.getDefaultAdapter();
		LHBTList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
				String info = ((TextView) v).getText().toString();
				String address = info.substring(info.length() - 17);
				Log.i(TAG, "click: " + address);
				BluetoothDevice device = BTadapter.getRemoteDevice(address);
				LHAddress = device.getAddress();
				LHTV.setText("左手: " + device.getName());
			}

		});

		RHBTList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
				String info = ((TextView) v).getText().toString();
				String address = info.substring(info.length() - 17);
				Log.i(TAG, "click: " + address);
				BluetoothDevice device = BTadapter.getRemoteDevice(address);
				RHAddress = device.getAddress();
				RHTV.setText("右手: " + device.getName());
			}

		});

		LHBTList.setAdapter(this.mDeviceArrayAdapter);
		RHBTList.setAdapter(this.mDeviceArrayAdapter);

	}																						//  結束onCreate程式

	@Override
	protected void onStop()
	{
		unregisterReceiver(mReceiver);
		super.onStop();
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}


	//*****其他副程式、類別*****
	private boolean requireBluetooth() {
		if (!BTadapter.isEnabled()) {
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			this.startActivityForResult(enableIntent, BluetoothControl.GET_REQUEST_ENABLE_BT());
			return false;
		}
		return true;
	}
	private void showBTinformation() 
	{
		String address = BTadapter.getAddress();
		String name = BTadapter.getName();
		String information = "藍芽資訊： " + name + " - " + address;
		btinformation.setText(information);
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		this.registerReceiver(mReceiver, filter);

		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		this.registerReceiver(mReceiver, filter);

		Log.i(TAG, " Receiver");
		BTadapter.startDiscovery();
		this.setProgressBarIndeterminateVisibility(true);
	}
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() 
	{
		@Override
		public void onReceive(Context context, Intent intent) 
		{
			String action = intent.getAction();

			if (BluetoothDevice.ACTION_FOUND.equals(action)) 
			{
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
					String message = "New Bluetooth Device Discovery : " + device.getName() + "\n" + device.getAddress();
					mDeviceArrayAdapter.add(device.getName() + "\n" + device.getAddress());
					Log.i(TAG, message);
				}
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				Log.i(TAG, "DISCOVERY Finished");
				setProgressBarIndeterminateVisibility(false);
			}
		}
	};
	public class Comm extends Thread {
		// 判斷是否開始執行
		public volatile boolean isrun = true;

		private ArrayList<TextView> information;

		private int[] finger;

		public String communicationNamne = "";

		public String receiveString = "";
		public String OrientationString = "";
		public int 	receiveMotionFAData, receiveMotionFBData, 
					receiveMotionFCData, receiveMotionFDData,
					receiveMotionFEData;
		public int 	receiveMotionAXData, receiveMotionAYData,
					receiveMotionAZData;
		
		
		/**
		 * 藍芽連線的 Socket
		 */
		private BluetoothSocket socket;
		/**
		 * 接收資料的串流
		 */
		private InputStream ips;

		/**
		 * 建構子，傳入 BluetoothSocket
		 *
		 * @param socket
		 */
		public Comm(BluetoothSocket socket, String name) {
			this.socket = socket;
			this.ips = null;
			this.communicationNamne = name;
			// 5跟手指頭
			finger = new int[5];
			for (int i = 0; i < 5; i++) {
				finger[i] = 0;
			}
			try {
				ips = socket.getInputStream();
			} catch (IOException e) {
			}
		}

		public void setView(ArrayList<TextView> tv) {
			this.information = tv;
			this.information.get(0).setMovementMethod(new ScrollingMovementMethod());
			this.information.get(1).setMovementMethod(new ScrollingMovementMethod());
		}

		@Override
		public void run() {
			byte[] buffer = new byte[1];
			int bytes = 0;
			String DisplayText = "";
			int DisplayTextCount = 0;
			String ChannelNameTemp = "";													//	暫存通道名稱
			String SensorDataTemp = "";														//	暫存感測器資料
			DataProcessing DataProcessing1 = new DataProcessing
								(0, 0, 10000, 0, 0, 10000, 0, 0, 10000, 0, 0, 10000, 0, 0, 10000,
								 9900, 9900, 9900 );
			if(this.communicationNamne.equals("Left".toString()) == true)
			{
				DataProcessing1.SetFAth1(650);
				DataProcessing1.SetFAth2(710);
				DataProcessing1.SetFBth1(640);
				DataProcessing1.SetFBth2(720);
				DataProcessing1.SetFCth1(640);
				DataProcessing1.SetFCth2(720);
				DataProcessing1.SetFDth1(645);
				DataProcessing1.SetFDth2(740);
				DataProcessing1.SetFEth1(620);
				DataProcessing1.SetFEth2(740);
			}
			else if(this.communicationNamne.equals("Right".toString()) == true)
			{
				DataProcessing1.SetFAth1(649);
				DataProcessing1.SetFAth2(703);
				DataProcessing1.SetFBth1(775);
				DataProcessing1.SetFBth2(800);
				DataProcessing1.SetFCth1(668);
				DataProcessing1.SetFCth2(750);
				DataProcessing1.SetFDth1(625);
				DataProcessing1.SetFDth2(700);
				DataProcessing1.SetFEth1(668);
				DataProcessing1.SetFEth2(760);
			}
			SensorData SensorData1 = new SensorData
								(0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			
			String FAOptputStr = "";
			String FBOptputStr = "";
			String FCOptputStr = "";
			String FDOptputStr = "";
			String FEOptputStr = "";
			String OrientationStr = "";

			while (this.isrun) {															//	若資料處理執行中
				try {

					if (this.ips.available() > 0) 											//	若接收到資料
					{																		//	進入if敘述
						bytes = this.ips.read(buffer);
						/****V2編碼顯示方式****/
						String BufferString = new String(buffer, "UTF-8");					//	將buffer轉換為字串
						if(	BufferString.equals("A") || 									//	若當前分析字元為A(通道名稱)
							BufferString.equals("B") || 									//	或當前分析字元為B(通道名稱)
							BufferString.equals("C") || 									//	或當前分析字元為C(通道名稱)
							BufferString.equals("D") || 									//	或當前分析字元為D(通道名稱)
							BufferString.equals("E") || 									//	或當前分析字元為E(通道名稱)
							BufferString.equals("F") || 									//	或當前分析字元為F(通道名稱)
							BufferString.equals("G") || 									//	或當前分析字元為G(通道名稱)
							BufferString.equals("H") || 									//	或當前分析字元為H(通道名稱)
							BufferString.equals("I") || 									//	或當前分析字元為I(通道名稱)
							BufferString.equals("J") || 									//	或當前分析字元為J(通道名稱)
							BufferString.equals("K") || 									//	或當前分析字元為K(通道名稱)
							BufferString.equals("L") )										//	或當前分析字元為L(通道名稱)
						{																	//	進入if敘述
							if( ChannelNameTemp.equals("A") )								//	若通道紀錄為"A"
							{																//	進入if敘述
								SensorData1.SetFA(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionFAData = SensorData1.GetdFA();
								if( SensorData1.GetFA() < DataProcessing1.GetFAth1() )		//	小於第一門檻值
								{															//	進入if敘述
									FAOptputStr = "00";										//	設定FAOptputStr
								}															//	結束if敘述
								else if( SensorData1.GetFA() < DataProcessing1.GetFAth2() )	//	小於第二門檻值
								{															//	進入else if敘述
									FAOptputStr = "01";										//	設定FAOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFA() < DataProcessing1.GetFAth3() )	//	小於第三門檻值
								{															//	進入else if敘述
									FAOptputStr = "11";										//	設定FAOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFA() > DataProcessing1.GetFAth3() )	//	大於第三門檻值
								{															//	進入else if敘述
									FAOptputStr = "01";										//	設定FAOptputStr
								}															//	結束else if敘述
							}																//	結束if敘述
							else if( ChannelNameTemp.equals("B") )							//	若通道紀錄為"B"
							{																//	進入else if敘述
								SensorData1.SetFB(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionFBData = SensorData1.GetdFB();
								if( SensorData1.GetFB() < DataProcessing1.GetFBth1() )		//	小於第一門檻值
								{															//	進入if敘述
									FBOptputStr = "00";										//	設定FBOptputStr
								}															//	結束if敘述
								else if( SensorData1.GetFB() < DataProcessing1.GetFBth2() )	//	小於第二門檻值
								{															//	進入else if敘述
									FBOptputStr = "01";										//	設定FBOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFB() < DataProcessing1.GetFBth3() )	//	小於第三門檻值
								{															//	進入else if敘述
									FBOptputStr = "11";										//	設定FBOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFB() > DataProcessing1.GetFBth3() )	//	大於第三門檻值
								{															//	進入else if敘述
									FBOptputStr = "01";										//	設定FBOptputStr
								}															//	結束else if敘述
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("C") )							//	若通道紀錄為"C"
							{																//	進入else if敘述
								SensorData1.SetFC(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionFCData = SensorData1.GetdFC();
								if( SensorData1.GetFC() < DataProcessing1.GetFCth1() )		//	小於第一門檻值
								{															//	進入if敘述
									FCOptputStr = "00";										//	設定FCOptputStr
								}															//	結束if敘述
								else if( SensorData1.GetFC() < DataProcessing1.GetFCth2() )	//	小於第二門檻值
								{															//	進入else if敘述
									FCOptputStr = "01";										//	設定FCOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFC() < DataProcessing1.GetFCth3() )	//	小於第三門檻值
								{															//	進入else if敘述
									FCOptputStr = "11";										//	設定FCOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFC() > DataProcessing1.GetFCth3() )	//	大於第三門檻值
								{															//	進入else if敘述
									FCOptputStr = "01";										//	設定FCOptputStr
								}															//	結束else if敘述
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("D") )							//	若通道紀錄為"D"
							{																//	進入else if敘述
								SensorData1.SetFD(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionFDData = SensorData1.GetdFD();
								if( SensorData1.GetFD() < DataProcessing1.GetFDth1() )		//	小於第一門檻值
								{															//	進入if敘述
									FDOptputStr = "00";										//	設定FDOptputStr
								}															//	結束if敘述
								else if( SensorData1.GetFD() < DataProcessing1.GetFDth2() )	//	小於第二門檻值
								{															//	進入else if敘述
									FDOptputStr = "01";										//	設定FDOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFD() < DataProcessing1.GetFDth3() )	//	小於第三門檻值
								{															//	進入else if敘述
									FDOptputStr = "11";										//	設定FDOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFD() > DataProcessing1.GetFDth3() )	//	大於第三門檻值
								{															//	進入else if敘述
									FDOptputStr = "01";										//	設定FDOptputStr
								}															//	結束else if敘述
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("E") )							//	若通道紀錄為"E"
							{																//	進入else if敘述
								SensorData1.SetFE(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionFEData = SensorData1.GetdFE();
								if( SensorData1.GetFE() < DataProcessing1.GetFEth1() )		//	小於第一門檻值
								{															//	進入if敘述
									FEOptputStr = "00";										//	設定FEOptputStr
								}															//	結束if敘述
								else if( SensorData1.GetFE() < DataProcessing1.GetFEth2() )	//	小於第二門檻值
								{															//	進入else if敘述
									FEOptputStr = "01";										//	設定FEOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFE() < DataProcessing1.GetFEth3() )	//	小於第三門檻值
								{															//	進入else if敘述
									FEOptputStr = "11";										//	設定FEOptputStr
								}															//	結束else if敘述
								else if( SensorData1.GetFE() > DataProcessing1.GetFEth3() )	//	大於第三門檻值
								{															//	進入else if敘述
									FEOptputStr = "01";										//	設定FEOptputStr
								}															//	結束else if敘述
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("F") )							//	若通道紀錄為"F"
							{																//	進入else if敘述
								SensorData1.SetBT(Integer.valueOf(SensorDataTemp));			//	填入數值
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("G") )							//	若通道紀錄為"G"
							{																//	進入else if敘述
								SensorData1.SetAX(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionAXData = SensorData1.GetdAX();
								if( SensorData1.GetAX() > DataProcessing1.GetAXth() )		//	若AX大於門檻值
								{															//	進入if敘述
									OrientationStr = "outward";
								}															//	結束if敘述
								else if( SensorData1.GetAX() < -DataProcessing1.GetAXth() )	//	若AX小於負門檻值
								{															//	進入else if敘述
									OrientationStr = "inward";
								}															//	結束else if敘述
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("H") )							//	若通道紀錄為"H"
							{																//	進入else if敘述
								SensorData1.SetAY(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionAYData = SensorData1.GetdAY();
								if( SensorData1.GetAY() > DataProcessing1.GetAYth() )		//	若AY大於門檻值
								{															//	進入if敘述
									OrientationStr = "forward";
								}															//	結束if敘述
								else if( SensorData1.GetAY() < -DataProcessing1.GetAYth() )	//	若AY小於負門檻值
								{															//	進入else if敘述
									OrientationStr = "backward";
								}															//	結束else if敘述
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("I") )							//	若通道紀錄為"I"
							{																//	進入else if敘述
								SensorData1.SetAZ(Integer.valueOf(SensorDataTemp));			//	填入數值
								receiveMotionAZData = SensorData1.GetdAZ();
								if( SensorData1.GetAZ() > DataProcessing1.GetAZth() )		//	若AZ大於門檻值
								{															//	進入if敘述
									OrientationStr = "downward";
								}															//	結束if敘述
								else if( SensorData1.GetAZ() < -DataProcessing1.GetAZth() )	//	若AZ小於負門檻值
								{															//	進入else if敘述
									OrientationStr = "upward";
								}															//	結束else if敘述
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("J") )							//	若通道紀錄為"J"
							{																//	進入else if敘述
								SensorData1.SetGX(Integer.valueOf(SensorDataTemp));			//	填入數值
							}																//	結束else if敘述
							else if( ChannelNameTemp.equals("K") )							//	若通道紀錄為"K"
							{
								SensorData1.SetGY(Integer.valueOf(SensorDataTemp));			//	填入數值
							}
							else if( ChannelNameTemp.equals("L") )							//	若通道紀錄為"L"
							{
								SensorData1.SetGZ(Integer.valueOf(SensorDataTemp));			//	填入數值
							}
							ChannelNameTemp = BufferString;									//	將通道名稱寫入ChannelNameTemp
							SensorDataTemp = "";											//	清空SensorDataTemp
						}																	//	結束if敘述
						else if(BufferString.equals("0") || 
								BufferString.equals("1") || 
								BufferString.equals("2") || 
								BufferString.equals("3") || 
								BufferString.equals("4") || 
								BufferString.equals("5") || 
								BufferString.equals("6") || 
								BufferString.equals("7") || 
								BufferString.equals("8") || 
								BufferString.equals("9") )									//	若當前分析字元為數值
						{																	//	進入else if敘述
							SensorDataTemp = SensorDataTemp + BufferString;					//	累計數值資料
						}																	//	結束else if敘述
						
						receiveString = FAOptputStr + FBOptputStr + FCOptputStr + FDOptputStr + FEOptputStr;
						OrientationString = OrientationStr;
						if(DisplayTextCount > 120)											//	若DisplayTextCount超過20字
						{
							DisplayText = "";
							DisplayTextCount = 0;
						}
						else
						{
							/*DisplayText =
								"FA:" + SensorData1.GetFA() + "FB:" + SensorData1.GetFB() + '\n' +
								"FC:" + SensorData1.GetFC() + "FD:" + SensorData1.GetFD() + '\n' +
								"FE:" + SensorData1.GetFE() + '\n' +
								"BT:" + SensorData1.GetBT() + '\n' +
								"AX:" + SensorData1.GetAX() + "AY:" + SensorData1.GetAY() + '\n' +
								"AZ:" + SensorData1.GetAZ() + '\n' +
								"GX:" + SensorData1.GetGX() + "GY:" + SensorData1.GetGY() + '\n' +
								"GZ:" + SensorData1.GetGZ()	+ '\n' + '\n' + '\n' + '\n';*/
							DisplayText =
								"FA:" + FAOptputStr + "FB:" + FBOptputStr + '\n' +
								"FC:" + FCOptputStr + "FD:" + FDOptputStr + '\n' +
								"FE:" + FEOptputStr + '\n' +
								"dFA:" + SensorData1.GetdFA() + "dFB:" + SensorData1.GetdFB()  + '\n' +
								"dFC:" + SensorData1.GetdFC() + "dFD:" + SensorData1.GetdFD()  + '\n' +
								"dFE:" + SensorData1.GetdFE() + '\n' +
								"dAX:" + SensorData1.GetdAX() + "dAY:" + SensorData1.GetdAY() + '\n' +
								"dAZ:" + SensorData1.GetdAZ() + '\n' +
								OrientationStr + '\n' +
								"GX:" + SensorData1.GetGX() + "GY:" + SensorData1.GetGY() + '\n' +
								"GZ:" + SensorData1.GetGZ()	+
								"dGX:" + SensorData1.GetdGX() + "dGY:" + SensorData1.GetdGY() + '\n' +
								"dGZ:" + SensorData1.GetdGZ() +
								'\n' + '\n' + '\n' + '\n';
								
							/*DisplayText = receiveString;*/
							//DisplayText = DisplayText + BufferString;
							//DisplayTextCount = DisplayTextCount + 1;
						}
						
						final String FinalDisplayText = this.communicationNamne+"\n" + DisplayText;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								information.get(0).setText(FinalDisplayText);

							}
						});
						
					}																		//	結束if敘述
				} catch (IOException e) {

				}
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.interrupt();
		}
	}
	
	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case UPDATE_SETTING_SUCCESS:
					// 在這邊寫要操作UI(Activity)的部分，就會動作了。
					String mes=(String) msg.obj;
					TextView_L.setText(mes);
				case UPDATE_SETTING_SU:
					String mes1=(String) msg.obj;
					TextView_R.setText(mes1);
			}
			super.handleMessage(msg);
		}
	}
	public void say(final int id){
		new Runnable() {
			@Override
			public void run() {
				try {
					final MediaPlayer player = MediaPlayer.create(MainActivity.this, id);
					if (player != null) {
						player.stop();
					}
					if (!player.isPlaying())
					{player.prepare();
						player.start();
					}
					player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
							player.release();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
	public class DataProcess extends Thread {
		private Comm left, right;
		public boolean isRun = true;
		private String LH, RH;
		private String LHOrientation, RHOrientation;
		private int leftHandMotionFAData, leftHandMotionFBData, 
					leftHandMotionFCData, leftHandMotionFDData,
					leftHandMotionFEData;
		private int leftHandMotionAXData, leftHandMotionAYData,
					leftHandMotionAZData;
		private int rightHandMotionFAData, rightHandMotionFBData, 
					rightHandMotionFCData, rightHandMotionFDData,
					rightHandMotionFEData;
		private int rightHandMotionAXData, rightHandMotionAYData,
					rightHandMotionAZData;
		
		private String lastPattern;

		public DataProcess(Comm left, Comm right) {
			this.left = left;
			this.right = right;
			Vocabulary1 = new Vocabulary(langSetting);
			//Vocabulary1.Voc();
			Vocabulary1.StaticVocabulary();
			Vocabulary1.MotionVocabulary();
			Vocabulary1.CombinationVocabulary();

		}

		@Override
		public void run() {
			super.run();
			int MatchTimes = 0;
			int SameWordCount = 0;
			String ResultStr = "";
			while (this.isRun) {
				// 如果左右手的 Socket 都在執行的話，就開始判斷
				if (left.isrun && right.isrun) {
					//Log.i(TAG, "detect");
					LH = left.receiveString;
					RH = right.receiveString;
					LHOrientation = left.OrientationString;
					RHOrientation = right.OrientationString;
					leftHandMotionFAData = left.receiveMotionFAData;
					leftHandMotionFBData = left.receiveMotionFBData; 
					leftHandMotionFCData = left.receiveMotionFCData;
					leftHandMotionFDData = left.receiveMotionFDData;
					leftHandMotionFEData = left.receiveMotionFEData;
					leftHandMotionAXData = left.receiveMotionAXData;
					leftHandMotionAYData = left.receiveMotionAYData;
					leftHandMotionAZData = left.receiveMotionAZData;
					
					rightHandMotionFAData = right.receiveMotionFAData;
					rightHandMotionFBData = right.receiveMotionFBData; 
					rightHandMotionFCData = right.receiveMotionFCData;
					rightHandMotionFDData = right.receiveMotionFDData;
					rightHandMotionFEData = right.receiveMotionFEData;
					rightHandMotionAXData = right.receiveMotionAXData;
					rightHandMotionAYData = right.receiveMotionAYData;
					rightHandMotionAZData = right.receiveMotionAZData;
					if (RH != "" && LH != "") 
					{
						boolean findPattern = false;
						String nowPattern = "";
						for(int loopnum1 = 0; loopnum1 < Vocabulary1.handMotionPatterns.size(); loopnum1++)
						{
							if(Vocabulary1.handMotionPatterns.get(loopnum1).isMatch(	LH, LHOrientation,
																			leftHandMotionFAData, leftHandMotionFBData, leftHandMotionFCData, leftHandMotionFDData, leftHandMotionFEData,
																			leftHandMotionAXData, leftHandMotionAYData, leftHandMotionAZData,
																			RH, RHOrientation,
																			rightHandMotionFAData, rightHandMotionFBData, rightHandMotionFCData, rightHandMotionFDData, rightHandMotionFEData,
																			rightHandMotionAXData, rightHandMotionAYData, rightHandMotionAZData
																			) == true)
							{
								MatchTimes = MatchTimes + 1;
								if(MatchTimes >= 0)
								{
									MatchTimes = 0;
									//if(ResultStr.equals(handMotionPatterns.get(loopnum1).ChineseWord.toString()))		//	若比劃詞彙相同
									//{
										SameWordCount = SameWordCount + 1;
										if(SameWordCount >= 0)															//	重複比劃相同詞彙
										{
											SameWordCount = 0;
											if(ShowWord.getText().equals(Vocabulary1.handMotionPatterns.get(loopnum1).ChineseWord.toString()) == false)
											{
												say(Vocabulary1.handMotionPatterns.get(loopnum1).mp3ID);
												final String OutputStr = Vocabulary1.handMotionPatterns.get(loopnum1).ChineseWord.toString();
												//	https://stackoverflow.com/questions/3134683/android-toast-in-a-thread
												MainActivity.this.runOnUiThread(new Runnable() {				//	控制UI須新增runOnUiThread
													public void run() {
														Toast.makeText(MainActivity.this, OutputStr, Toast.LENGTH_SHORT).show();
														ShowWord.setText(OutputStr);
													}
												});
											}
										}
									//}
									//else
									//{
										for(int loopnum2 = 0; loopnum2 < Vocabulary1.combinationPatterns.size(); loopnum2++)
										{
											if(Vocabulary1.combinationPatterns.get(loopnum2).isMatch(ResultStr, Vocabulary1.handMotionPatterns.get(loopnum1).ChineseWord.toString()
																						) == true)
											{
												say(Vocabulary1.combinationPatterns.get(loopnum2).mp3ID);
												final String OutputStr = Vocabulary1.combinationPatterns.get(loopnum2).ChineseWord.toString();
												//	https://stackoverflow.com/questions/3134683/android-toast-in-a-thread
												MainActivity.this.runOnUiThread(new Runnable() {				//	控制UI須新增runOnUiThread
												  public void run() {
													Toast.makeText(MainActivity.this, OutputStr, Toast.LENGTH_SHORT).show();
													ShowWord.setText(OutputStr);
												  }
												});
											}
										}
										ResultStr = Vocabulary1.handMotionPatterns.get(loopnum1).ChineseWord.toString();
									//}
									
								}
							}
						}
						if(( Math.abs(leftHandMotionAXData) < 500) && ( Math.abs(leftHandMotionAYData) < 500) && ( Math.abs(leftHandMotionAZData) < 500) &&
								( Math.abs(rightHandMotionAXData) < 500) && ( Math.abs(rightHandMotionAYData) < 500) && ( Math.abs(rightHandMotionAZData) < 500)) {
							for (int loopnum1 = 0; loopnum1 < Vocabulary1.handPatterns.size(); loopnum1++) {
								if (Vocabulary1.handPatterns.get(loopnum1).isMatch(LH, LHOrientation, RH, RHOrientation) == true) {
									MatchTimes = MatchTimes + 1;
									if (MatchTimes >= 0) {
										MatchTimes = 0;
										//if (ResultStr.equals(handPatterns.get(loopnum1).ChineseWord.toString()))        //	若比劃詞彙相同
										//{
											SameWordCount = SameWordCount + 1;
											if (SameWordCount >= 0)                                                    //	重複比劃相同詞彙
											{
												SameWordCount = 0;
												if(ShowWord.getText().equals( Vocabulary1.handPatterns.get(loopnum1).ChineseWord.toString()) == false)
												{
													say(Vocabulary1.handPatterns.get(loopnum1).mp3ID);
													final String OutputStr = Vocabulary1.handPatterns.get(loopnum1).ChineseWord.toString();
													//	https://stackoverflow.com/questions/3134683/android-toast-in-a-thread
													MainActivity.this.runOnUiThread(new Runnable() {                //	控制UI須新增runOnUiThread
														public void run() {
															Toast.makeText(MainActivity.this, OutputStr, Toast.LENGTH_SHORT).show();
															ShowWord.setText(OutputStr);
														}
													});
												}
											}
										//} else {
											for (int loopnum2 = 0; loopnum2 < Vocabulary1.combinationPatterns.size(); loopnum2++) {
												if (Vocabulary1.combinationPatterns.get(loopnum2).isMatch(ResultStr, Vocabulary1.handPatterns.get(loopnum1).ChineseWord.toString()
												) == true) {
													say(Vocabulary1.combinationPatterns.get(loopnum2).mp3ID);
													final String OutputStr = Vocabulary1.combinationPatterns.get(loopnum2).ChineseWord.toString();
													//https://stackoverflow.com/questions/3134683/android-toast-in-a-thread
													MainActivity.this.runOnUiThread(new Runnable() {                //	控制UI須新增runOnUiThread
														public void run() {
															Toast.makeText(MainActivity.this, OutputStr, Toast.LENGTH_SHORT).show();
															ShowWord.setText(OutputStr);
														}
													});
												}
											}
											ResultStr = Vocabulary1.handPatterns.get(loopnum1).ChineseWord.toString();
										//}

									}
								}
							}
						}
						//   this is word_a
						try{
							// 透過Message來傳遞值做對應操作UI(Activity)的部分即可執行。
							Message m = new Message();
							//	m.what = UPDATE_SETTING_SUCCESS;
							m=handler.obtainMessage(UPDATE_SETTING_SUCCESS,word_a);
							handler.sendMessage(m);

							// 在這邊寫要操作UI(Activity)的部分，不會動作，還有可能程式會當掉。
						}catch (Exception e)
						{
							e.printStackTrace();

							// 在這邊寫要操作UI(Activity)的部分，不會動作，還有可能程式會當掉。
						}
						if (!findPattern) {
							lastPattern = "";
						}
						else
						{

							word_a=lastPattern;
						}
					}
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void startVoiceRecognitionActivity()											//  startVoiceRecognitionActivity副程式
	{																						//  進入startVoiceRecognitionActivity副程式
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);				//  通過Intent傳遞語音辨識的模式
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		//  設定語言模式和自由形式的語音辨識
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo");			//  提示語音開始
		startActivityForResult(intent, VoiceRecognition.GetVOICE_RECOGNITION_REQUEST_CODE());
		//  開始執行我們的Intent、語音辨識
	}																						//  結束startVoiceRecognitionActivity副程式
}																							//  結束MainActivity類別
