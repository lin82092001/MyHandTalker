package com.example.smartglovev2;

/**
 * Created by jimmy on 2017/7/17.
 */

import java.util.ArrayList;																	//	引入android.util.ArrayList函式庫

public class SensorData 																	//	SensorData類別
{																							//	進入SensorData類別
	private int FA = 0;																		//	宣告FA私有變數
	private int FB = 0;																		//	宣告FB私有變數
	private int FC = 0;																		//	宣告FC私有變數
	private int FD = 0;																		//	宣告FD私有變數
	private int FE = 0;																		//	宣告FE私有變數
	private int BT = 0;																		//	宣告BT私有變數
	private int AX = 0;																		//	宣告AX私有變數
	private int AY = 0;																		//	宣告AY私有變數
	private int AZ = 0;																		//	宣告AZ私有變數
	private int GX = 0;																		//	宣告GX私有變數
	private int GY = 0;																		//	宣告GY私有變數
	private int GZ = 0;																		//	宣告GZ私有變數
	
	private int dFA = 0;																	//	宣告dFA私有變數
	private int dFB = 0;																	//	宣告dFB私有變數
	private int dFC = 0;																	//	宣告dFC私有變數
	private int dFD = 0;																	//	宣告dFD私有變數
	private int dFE = 0;																	//	宣告dFE私有變數
	private int dBT = 0;																	//	宣告dBT私有變數
	private int dAX = 0;																	//	宣告dAX私有變數
	private int dAY = 0;																	//	宣告dAY私有變數
	private int dAZ = 0;																	//	宣告dAZ私有變數
	private int dGX = 0;																	//	宣告dGX私有變數
	private int dGY = 0;																	//	宣告dGY私有變數
	private int dGZ = 0;																	//	宣告dGZ私有變數
	
	public SensorData(	int FA, int FB, int FC, int FD, int FE,
						int dFA, int dFB, int dFC, int dFD, int dFE,
						int BT,
						int AX, int AY, int AZ,
						int dAX, int dAY, int dAZ,
						int GX, int GY, int GZ,
						int dGX, int dGY, int dGZ)
	{
		this.FA = FA;
		this.FB = FB;
		this.FC = FC;
		this.FD = FD;
		this.FE = FE;
		this.BT = BT;
		this.AX = AX;
		this.AY = AY;
		this.AZ = AZ;
		this.GX = GX;
		this.GY = GY;
		this.GZ = GZ;
	}
	
	public int GetFA()																		//	GetFA方法
	{																						//	進入GetFA方法
		return FA;																			//	回傳FA
	}																						//	結束GetFA方法
	public void SetFA(int InputData)														//	SetFA方法
	{																						//	進入SetFA方法
		SetdFA(InputData);																	//	SetFA方法
		FA = InputData;																		//	設定FA數值
	}																						//	結束SetFA方法
	public int GetFB()																		//	GetFB方法
	{																						//	進入GetFB方法
		return FB;																			//	回傳FB
	}																						//	結束GetFB方法
	public void SetFB(int InputData)														//	SetFB方法
	{																						//	進入SetFB方法
		SetdFB(InputData);																	//	SetFB方法		
		FB = InputData;																		//	設定FB數值
	}																						//	結束SetFB方法
	public int GetFC()																		//	GetFC方法
	{																						//	進入GetFC方法
		return FC;																			//	回傳FC
	}																						//	結束GetFC方法
	public void SetFC(int InputData)														//	SetFC方法
	{																						//	進入SetFC方法
		SetdFC(InputData);																	//	SetFC方法
		FC = InputData;																		//	設定FC數值
	}																						//	結束SetFC方法
	public int GetFD()																		//	GetFD方法
	{																						//	進入GetFD方法
		return FD;																			//	回傳FD
	}																						//	結束GetFD方法
	public void SetFD(int InputData)														//	SetFD方法
	{																						//	進入SetFD方法
		SetdFD(InputData);																	//	SetFD方法
		FD = InputData;																		//	設定FD數值
	}																						//	結束SetFD方法
	public int GetFE()																		//	GetFE方法
	{																						//	進入GetFE方法
		return FE;																			//	回傳FE
	}																						//	結束GetFE方法
	public void SetFE(int InputData)														//	SetFE方法
	{																						//	進入SetFE方法
		SetdFE(InputData);																	//	SetFE方法
		FE = InputData;																		//	設定FE數值
	}																						//	結束SetFE方法
	public int GetBT()																		//	GetFA方法
	{																						//	進入GetBT方法
		return BT;																			//	回傳BT
	}																						//	結束GetBT方法
	public void SetBT(int InputData)														//	SetBT方法
	{																						//	進入SetBT方法
		BT = InputData;																		//	設定BT數值
	}																						//	結束SetBT方法
	public int GetAX()																		//	GetAX方法
	{																						//	進入GetAX方法
		return AX;																			//	回傳AX
	}																						//	結束GetAX方法
	public void SetAX(int InputData)														//	SetAX方法
	{																						//	進入SetAX方法
		SetdAX(UINT16toINT16(InputData));
		AX = UINT16toINT16(InputData);
	}																						//	結束SetAX方法
	public int GetAY()																		//	GetAY方法
	{																						//	進入GetAY方法
		return AY;																			//	回傳FA
	}																						//	結束GetAY方法
	public void SetAY(int InputData)														//	SetAY方法
	{																						//	進入SetAY方法
		SetdAY(UINT16toINT16(InputData));
		AY = UINT16toINT16(InputData);
	}																						//	結束SetAY方法
	public int GetAZ()																		//	GetAZ方法
	{																						//	進入GetAZ方法
		return AZ;																			//	回傳AZ
	}																						//	結束GetAZ方法
	public void SetAZ(int InputData)														//	SetAZ方法
	{																						//	進入SetAZ方法
		SetdAZ(UINT16toINT16(InputData));
		AZ = UINT16toINT16(InputData);
	}																						//	結束SetAZ方法
	public int GetGX()																		//	GetGX方法
	{																						//	進入GetGX方法
		return GX;																			//	回傳GX
	}																						//	結束GetGX方法
	public void SetGX(int InputData)														//	SetGX方法
	{																						//	進入SetGX方法
		GX = InputData;																		//	設定GX數值
	}																						//	結束SetGX方法
	public int GetGY()																		//	GetGY方法
	{																						//	進入GetGY方法
		return GY;																			//	回傳GY
	}																						//	結束GetGY方法
	public void SetGY(int InputData)														//	SetGY方法
	{																						//	進入SetGY方法
		GY = InputData;																		//	設定GY數值
	}																						//	結束SetGY方法
	public int GetGZ()																		//	GetGZ方法
	{																						//	進入GetGZ方法
		return GZ;																			//	回傳GZ
	}																						//	結束GetGZ方法
	public void SetGZ(int InputData)														//	SetGZ方法
	{																						//	進入SetGZ方法
		GZ = InputData;																		//	設定GZ數值
	}																						//	結束SetGZ方法
	//***計算差分數值***
	//Set差分方法為private
	public int GetdFA()																		//	GetdFA方法
	{																						//	進入GetdFA方法
		return dFA;																			//	回傳dFA
	}																						//	結束GetdFA方法
	private void SetdFA(int InputData)														//	SetdFA方法
	{																						//	進入SetdFA方法
		dFA = InputData - GetFA();															//	設定dFA數值
	}																						//	結束SetdFA方法
	public int GetdFB()																		//	GetdFB方法
	{																						//	進入GetdFB方法
		return dFB;																			//	回傳dFB
	}																						//	結束GetdFB方法
	private void SetdFB(int InputData)														//	SetdFB方法
	{																						//	進入SetdFB方法
		dFB = InputData - GetFB();															//	設定dFB數值
	}																						//	結束SetdFB方法
	public int GetdFC()																		//	GetdFC方法
	{																						//	進入GetdFC方法
		return dFC;																			//	回傳dFC
	}																						//	結束GetdFC方法
	private void SetdFC(int InputData)														//	SetdFC方法
	{																						//	進入SetdFC方法
		dFC = InputData- GetFC();															//	設定dFC數值
	}																						//	結束SetdFC方法
	public int GetdFD()																		//	GetdFD方法
	{																						//	進入GetdFD方法
		return dFD;																			//	回傳dFD
	}																						//	結束GetdFD方法
	private void SetdFD(int InputData)														//	SetdFD方法
	{																						//	進入SetdFD方法
		dFD = InputData- GetFD();															//	設定dFD數值
	}																						//	結束SetdFD方法
	public int GetdFE()																		//	GetdFE方法
	{																						//	進入GetdFE方法
		return dFE;																			//	回傳dFE
	}																						//	結束GetdFE方法
	private void SetdFE(int InputData)														//	SetdFE方法
	{																						//	進入SetdFE方法
		dFE = InputData- GetFE();															//	設定dFE數值
	}																						//	結束SetdFE方法
	public int GetdBT()																		//	GetdFA方法
	{																						//	進入GetdBT方法
		return dBT;																			//	回傳dBT
	}																						//	結束GetdBT方法
	private void SetdBT(int InputData)														//	SetdBT方法
	{																						//	進入SetdBT方法
		dBT = InputData;																	//	設定dBT數值
	}																						//	結束SetdBT方法
	public int GetdAX()																		//	GetdAX方法
	{																						//	進入GetdAX方法
		return dAX;																			//	回傳dAX
	}																						//	結束GetdAX方法
	private void SetdAX(int InputData)														//	SetdAX方法
	{																						//	進入SetdAX方法
		dAX = InputData- GetAX();															//	設定dAX數值
	}																						//	結束SetdAX方法
	public int GetdAY()																		//	GetdAY方法
	{																						//	進入GetdAY方法
		return dAY;																			//	回傳dFA
	}																						//	結束GetdAY方法
	private void SetdAY(int InputData)														//	SetdAY方法
	{																						//	進入SetdAY方法
		dAY = InputData- GetAY();															//	設定dAY數值
	}																						//	結束SetdAY方法
	public int GetdAZ()																		//	GetdAZ方法
	{																						//	進入GetdAZ方法
		return dAZ;																			//	回傳dAZ
	}																						//	結束GetdAZ方法
	private void SetdAZ(int InputData)														//	SetdAZ方法
	{																						//	進入SetdAZ方法
		dAZ = InputData- GetAZ();															//	設定dAZ數值
	}																						//	結束SetdAZ方法
	public int GetdGX()																		//	GetdGX方法
	{																						//	進入GetdGX方法
		return dGX;																			//	回傳dGX
	}																						//	結束GetdGX方法
	private void SetdGX(int InputData)														//	SetdGX方法
	{																						//	進入SetdGX方法
		dGX = InputData- GetGX();															//	設定dGX數值
	}																						//	結束SetdGX方法
	public int GetdGY()																		//	GetdGY方法
	{																						//	進入GetdGY方法
		return dGY;																			//	回傳dGY
	}																						//	結束GetdGY方法
	private void SetdGY(int InputData)														//	SetdGY方法
	{																						//	進入SetdGY方法
		dGY = InputData- GetGY();															//	設定dGY數值
	}																						//	結束SetdGY方法
	public int GetdGZ()																		//	GetdGZ方法
	{																						//	進入GetdGZ方法
		return dGZ;																			//	回傳dGZ
	}																						//	結束GetdGZ方法
	private void SetdGZ(int InputData)														//	SetdGZ方法
	{																						//	進入SetdGZ方法
		dGZ = InputData- GetGZ();															//	設定dGZ數值
	}																						//	結束SetdGZ方法
	private int UINT16toINT16(int InputData)												//	UINT16toINT16方法
	{																						//	進入UINT16toINT16方法
		int OutputData;
		if(InputData>32767)
		{
			OutputData =  InputData - 65536;
		}
		else
		{
			OutputData = InputData;															//	寫入OutputData
		}
		return OutputData;
	}																						//	結束UINT16toINT16方法
}																							//	結束SensorData類別
