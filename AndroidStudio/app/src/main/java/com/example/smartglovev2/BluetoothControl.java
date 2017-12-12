package com.example.smartglovev2;

/**
 * Created by jimmy on 2017/7/14.
 */

//import android.app.AlertDialog;																//	引入android.app.AlertDialog函式庫
import android.bluetooth.BluetoothAdapter;													//	引入android.bluetooth.BluetoothAdapter函式庫
import android.content.BroadcastReceiver;
import android.content.Context;
import android.widget.ArrayAdapter;															//	引入android.widget.ArrayAdapter函式庫
import android.widget.TextView;
import android.bluetooth.BluetoothDevice;													//	引入android.bluetooth.BluetoothDevice函式庫
/*import android.bluetooth.BluetoothSocket;													//	引入android.bluetooth.BluetoothSocket函式庫
import android.content.BroadcastReceiver;													//	引入android.content.BroadcastReceiver(廣播接收元件)函式庫
import android.content.Context;																//	引入android.content.Context函式庫
import android.content.DialogInterface;*/														//	引入android.content.DialogInterface函式庫
import android.content.Intent;																//	引入android.content.Intent函式庫
import android.content.IntentFilter;														//	引入android.content.IntentFilter函式庫
//import android.widget.Toast;																//	引入android.widget.Toast函式庫
import android.util.Log;																	//	引入android.util.Log函式庫
//import java.util.Set;																		//	引入java.util.Set函式庫

public class BluetoothControl extends MainActivity											//	BluetoothControl類別
{																							//	進入BluetoothControl類別
	public enum BT_STATE																	//	藍芽裝置狀態列舉(Program State enum)
	{																						//	進入BT_STATE列舉
		SELECT_BT_DEVICE, CONNECTING, CONNECTED												//	列出藍芽狀態
	};																						//	結束BT_STATE列舉
	// Program now state
	public static BT_STATE state = BT_STATE.SELECT_BT_DEVICE;								//	宣告state變數為BT_STATE列舉型態
	//private TextView btinformation;
	private final static String TAG = "BluetoothControl";									//	宣告TAG字串，用於Log紀錄
	private static final int REQUEST_ENABLE_BT = 1;											//	宣告REQUEST_ENABLE_BT控制變數，
	//*****bluetooth adapter*****
	public static BluetoothAdapter BTadapter;												//	宣告BTadapter物件為藍芽BluetoothAdapter
	public ArrayAdapter<String> mDeviceArrayAdapter;									//	宣告mDeviceArrayAdapter為ArrayAdapter<String>
	
	// selected Left hand bluetooth device address
	public static  String LHAddress = "";													//	宣告LHAddress字串變數記錄左手藍芽裝置位置
	// selected right hand bluetooth device address
	public static  String RHAddress = "";													//	宣告RHAddress字串變數記錄右手藍芽裝置位置
	
	public BluetoothControl(/*Context context*/)																//	BluetoothControl建構子
	{																						//	進入BluetoothControl建構子
		//mDeviceArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
	}																						//	結束BluetoothControl建構子
	
	//***公有變數存取方法***
	public static int GET_REQUEST_ENABLE_BT()												//	GET_REQUEST_ENABLE_BT方法
	{																						//	進入GET_REQUEST_ENABLE_BT方法
		return REQUEST_ENABLE_BT;															//	回傳REQUEST_ENABLE_BT數值
	}																						//	結束GET_REQUEST_ENABLE_BT方法

}																							//	結束BluetoothControl類別