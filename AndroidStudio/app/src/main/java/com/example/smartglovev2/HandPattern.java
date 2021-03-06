package com.example.smartglovev2;

/**
 * Created by jimmy on 2017/7/14.
 */

import android.util.Log;

import java.util.regex.Pattern;																//	引入java.util.regex.Pattern函式庫

public class HandPattern 																	//	HandPattern類別
{																							//	進入HandPattern類別
	public String ChineseWord;																//	宣告ChineseWord(中文詞彙)
	public String EnglishWord;																//	宣告EnglishWord(英文詞彙)
	private Pattern leftHandPosturePattern;													//	宣告leftHandPosturePattern(左手手部Pattern)物件
	private Pattern leftHandOrientation;													//	宣告leftHandOrientation(左手面向)
	private Pattern rightHandPosturePattern;												//	宣告rightHandPosturePattern(右手手部Pattern)物件
	private Pattern rightHandOrientation;													//	宣告rightHandOrientation(右手面向)
	public int mp3ID;																		//	宣告mp3ID

	public HandPattern(	String ChineseWord, String EnglishWord,
						String lefthand, String leftHandOrientation,
						String righthand, String rightHandOrientation,
						int mp3ID)
	//	HandPattern建構子
	{																						//	進入HandPattern建構子
		this.ChineseWord = ChineseWord;														//	設定ChineseWord初始化
		this.EnglishWord = EnglishWord;														//	設定EnglishWord初始化
		
		this.leftHandPosturePattern = Pattern.compile(lefthand);							//	將lefthand字串轉換成leftHandPosturePattern
		this.leftHandOrientation = Pattern.compile(leftHandOrientation);
		
		this.rightHandPosturePattern = Pattern.compile(righthand);							//	將righthand字串轉換成rightHandPosturePattern
		this.rightHandOrientation = Pattern.compile(rightHandOrientation);
		
		this.mp3ID = mp3ID;																	//	設定mp3ID初始化
		
	}																						//	結束HandPattern建構子
	
	public boolean isMatch(	String leftHandPosture, String leftHandOri,
							String rightHandPosture, String rightHandOri)
	//	isMatch方法
	{																						//	進入isMatch方法
		boolean leftHandPosturePatternMatchResult, rightHandPosturePatternMatchResult;		//	宣告leftHandPosturePatternMatchResult、rightHandPosturePatternMatchResult變數
		leftHandPosturePatternMatchResult = isLeftHandPosturePatternMatch(leftHandPosture);	//	呼叫isLeftHandPosturePatternMatch方法
		rightHandPosturePatternMatchResult = isRightHandPosturePatternMatch(rightHandPosture);
		//	呼叫isRightHandPosturePatternMatch方法
		
		boolean leftHandOrientationMatchResult, rightHandOrientationMatchResult;			//	宣告leftHandOrientationMatchResult與rightHandOrientationMatchResult布林變數
		leftHandOrientationMatchResult = isLeftHandOrientationPatternMatch(leftHandOri);	//	呼叫isLeftHandOrientationPatternMatch方法
		rightHandOrientationMatchResult = isRightHandOrientationPatternMatch(rightHandOri);	//	呼叫isRightHandOrientationPatternMatch方法
		
		return 	leftHandPosturePatternMatchResult && rightHandPosturePatternMatchResult &&
				leftHandOrientationMatchResult && rightHandOrientationMatchResult;
		//	回傳AND運算結果
	}																						//	結束isMatch方法
	
	//***LeftHand***
	private boolean isLeftHandPosturePatternMatch(String leftHandPosture)					//	isLeftHandPosturePatternMatch方法
	{																						//	進入isLeftHandPosturePatternMatch方法
		boolean leftHandPosturePatternMatcher;												//	宣告leftHandPosturePatternMatcher布林變數
		leftHandPosturePatternMatcher = this.leftHandPosturePattern.matcher(leftHandPosture).matches();
		//	leftHandPosturePatternMatcher為matches方法所傳回之布林變數
		return leftHandPosturePatternMatcher;												//	回傳leftHandPosturePatternMatcher布林變數
	}																						//	結束isLeftHandPosturePatternMatch方法
	
	private boolean isLeftHandOrientationPatternMatch(String leftHandOrientation)			//	isLeftHandOrientationPatternMatch方法
	{																						//	進入isLeftHandOrientationPatternMatch方法
		boolean isLeftHandOrientationPatternMatcher;										//	宣告isLeftHandOrientationPatternMatcher布林變數
		isLeftHandOrientationPatternMatcher = this.leftHandOrientation.matcher(leftHandOrientation).matches();
		//	isLeftHandOrientationPatternMatcher為matches方法所傳回之布林變數
		return isLeftHandOrientationPatternMatcher;											//	回傳isLeftHandOrientationPatternMatcher布林變數
	}																						//	結束isLeftHandOrientationPatternMatch方法
	
	//***RightHand***
	private boolean isRightHandPosturePatternMatch(String rightHandPosture)					//	isRightHandPosturePatternMatch方法
	{																						//	進入isRightHandPosturePatternMatch方法
		boolean rightHandPosturePatternMatcher;												//	宣告rightHandPosturePatternMatcher布林變數
		rightHandPosturePatternMatcher = this.rightHandPosturePattern.matcher(rightHandPosture).matches();
		//	rightHandPosturePatternMatcher為matches方法所傳回之布林變數
		return rightHandPosturePatternMatcher;												//	回傳rightHandPosturePatternMatcher布林變數
	}																						//	結束isRightHandPosturePatternMatch方法
	
	private boolean isRightHandOrientationPatternMatch(String rightHandOrientation)			//	isRightHandOrientationPatternMatch方法
	{																						//	進入isRightHandOrientationPatternMatch方法
		boolean isRightHandOrientationPatternMatcher;										//	宣告isRightHandOrientationPatternMatcher布林變數
		isRightHandOrientationPatternMatcher = this.rightHandOrientation.matcher(rightHandOrientation).matches();
		//	isRightHandOrientationPatternMatcher為matches方法所傳回之布林變數
		return isRightHandOrientationPatternMatcher;										//	回傳isRightHandOrientationPatternMatcher布林變數
	}																						//	結束isRightHandOrientationPatternMatch方法
	
}																							//	結束HandPattern類別

