package com.example.smartglovev2;

/**
 * Created by jimmy on 2017/7/14.
 */

import android.app.Activity;																//	引入android.app.Activity函式庫
import android.content.Intent;																//	引入android.content.Intent函式庫
import android.speech.RecognizerIntent;														//	引入android.speech.RecognizerIntent函式庫

import android.util.Log;																	//	引入android.util.Log函式庫

public class VoiceRecognition extends MainActivity 											//	VoiceRecognition類別
{																							//	進入VoiceRecognition類別
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	//  VOICE_RECOGNITION_REQUEST_CODE是自訂 因為當結果回傳時要告訴startActivityForResult是誰呼叫他
	Activity myMainActivity, mContext;														//	宣告myMainActivity為Activity物件
	public VoiceRecognition(Activity mainActivity) {
       super();
       this.myMainActivity = mainActivity;    
   }
	public static int GetVOICE_RECOGNITION_REQUEST_CODE()									//	GetVOICE_RECOGNITION_REQUEST_CODE方法
	{																						//	進入GetVOICE_RECOGNITION_REQUEST_CODE方法
		return VoiceRecognition.VOICE_RECOGNITION_REQUEST_CODE;								//	回傳VOICE_RECOGNITION_REQUEST_CODE
	}																						//	結束GetVOICE_RECOGNITION_REQUEST_CODE方法
	public void startVoiceRecognitionActivity()												//  startVoiceRecognitionActivity副程式
	{																						//  進入startVoiceRecognitionActivity副程式
		Intent RecognizerIntent1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);	//  通過Intent傳遞語音辨識的模式
		RecognizerIntent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		//  設定語言模式和自由形式的語音辨識
		RecognizerIntent1.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo");
		//  提示語音開始
		startActivityForResult(RecognizerIntent1, VoiceRecognition.GetVOICE_RECOGNITION_REQUEST_CODE());
		//  開始執行我們的Intent、語音辨識
	}																						//  結束startVoiceRecognitionActivity副程式
}																							//	結束VoiceRecognition類別

