package com.example.smartglovev2;

/**
 * Created by jimmy on 2017/7/14.
 */

import android.util.Log;

import java.util.regex.Pattern;																//	引入java.util.regex.Pattern函式庫

public class HandMotionPattern 																//	HandMotionPattern類別
{																							//	進入HandMotionPattern類別
	public String ChineseWord;																//	宣告ChineseWord(中文詞彙)
	public String EnglishWord;																//	宣告EnglishWord(英文詞彙)
	
	private Pattern leftHandPosturePattern;													//	宣告leftHandPosturePattern(左手手部Pattern)物件
	private Pattern leftHandOrientation;													//	宣告leftHandOrientation(左手面向)
	private int leftHandMotionFAth, leftHandMotionFBth, 
				leftHandMotionFCth, leftHandMotionFDth,
				leftHandMotionFEth;
	private int leftHandMotionAXth, leftHandMotionAYth,
				leftHandMotionAZth;
	
	private Pattern rightHandPosturePattern;												//	宣告rightHandPosturePattern(右手手部Pattern)物件
	private Pattern rightHandOrientation;													//	宣告rightHandOrientation(右手面向)
	private int rightHandMotionFAth, rightHandMotionFBth, 
				rightHandMotionFCth, rightHandMotionFDth,
				rightHandMotionFEth;
	private int rightHandMotionAXth, rightHandMotionAYth,
				rightHandMotionAZth;
	
	public int mp3ID;																		//	宣告mp3ID

	public HandMotionPattern(	String ChineseWord, String EnglishWord,
								String lefthand, String leftHandOrientation,
								int leftHandMotionFAth, int leftHandMotionFBth, int leftHandMotionFCth, int leftHandMotionFDth, int leftHandMotionFEth,
								int leftHandMotionAXth, int leftHandMotionAYth, int leftHandMotionAZth,
								String righthand, String rightHandOrientation,
								int rightHandMotionFAth, int rightHandMotionFBth, int rightHandMotionFCth, int rightHandMotionFDth, int rightHandMotionFEth,
								int rightHandMotionAXth, int rightHandMotionAYth, int rightHandMotionAZth,
								int mp3ID)
	//	HandMotionPattern建構子
	{																						//	進入HandMotionPattern建構子
		this.ChineseWord = ChineseWord;														//	設定ChineseWord初始化
		this.EnglishWord = EnglishWord;														//	設定EnglishWord初始化
		
		this.leftHandPosturePattern = Pattern.compile(lefthand);							//	將lefthand字串轉換成leftHandPosturePattern
		this.leftHandOrientation = Pattern.compile(leftHandOrientation);
		this.leftHandMotionFAth = leftHandMotionFAth;
		this.leftHandMotionFBth = leftHandMotionFBth;
		this.leftHandMotionFCth = leftHandMotionFCth;
		this.leftHandMotionFDth = leftHandMotionFDth;
		this.leftHandMotionFEth = leftHandMotionFEth;
		this.leftHandMotionAXth = leftHandMotionAXth;
		this.leftHandMotionAYth = leftHandMotionAYth;
		this.leftHandMotionAZth = leftHandMotionAZth;
		
		this.rightHandPosturePattern = Pattern.compile(righthand);							//	將righthand字串轉換成rightHandPosturePattern
		this.rightHandOrientation = Pattern.compile(rightHandOrientation);					//	將rightHandOrientation字串轉換成rightHandOrientation
		this.rightHandMotionFAth = rightHandMotionFAth;
		this.rightHandMotionFBth = rightHandMotionFBth;
		this.rightHandMotionFCth = rightHandMotionFCth;
		this.rightHandMotionFDth = rightHandMotionFDth;
		this.rightHandMotionFEth = rightHandMotionFEth;
		this.rightHandMotionAXth = rightHandMotionAXth;
		this.rightHandMotionAYth = rightHandMotionAYth;
		this.rightHandMotionAZth = rightHandMotionAZth;
		
		this.mp3ID = mp3ID;																	//	設定mp3ID初始化
	}																						//	結束HandMotionPattern建構子
	
	public boolean isMatch(	String leftHandPosture, String leftHandOri,
							int leftHandMotionFA, int leftHandMotionFB, int leftHandMotionFC, int leftHandMotionFD, int leftHandMotionFE,
							int leftHandMotionAX, int leftHandMotionAY, int leftHandMotionAZ,
							String rightHandPosture, String rightHandOri,
							int rightHandMotionFA, int rightHandMotionFB, int rightHandMotionFC, int rightHandMotionFD, int rightHandMotionFE,
							int rightHandMotionAX, int rightHandMotionAY, int rightHandMotionAZ
							)
	//	isMatch方法
	{																						//	進入isMatch方法
		boolean leftHandPosturePatternMatchResult, leftHandOrientationMatchResult ;
		leftHandPosturePatternMatchResult = isLeftHandPosturePatternMatch(leftHandPosture);
		leftHandOrientationMatchResult = isLeftHandOrientationPatternMatch(leftHandOri);	//	呼叫isLeftHandOrientationPatternMatch方法
		
		boolean leftHandMotionFAMatchResult, leftHandMotionFBMatchResult, 
				leftHandMotionFCMatchResult, leftHandMotionFDMatchResult,
				leftHandMotionFEMatchResult;
		boolean leftHandMotionAXMatchResult, leftHandMotionAYMatchResult,
				leftHandMotionAZMatchResult;
		leftHandMotionFAMatchResult = checkHigherThreshold(leftHandMotionFA, leftHandMotionFAth);
		leftHandMotionFBMatchResult = checkHigherThreshold(leftHandMotionFB, leftHandMotionFBth);
		leftHandMotionFCMatchResult = checkHigherThreshold(leftHandMotionFC, leftHandMotionFCth);
		leftHandMotionFDMatchResult = checkHigherThreshold(leftHandMotionFD, leftHandMotionFDth);
		leftHandMotionFEMatchResult = checkHigherThreshold(leftHandMotionFE, leftHandMotionFEth);
		leftHandMotionAXMatchResult = checkHigherThreshold(leftHandMotionAX, leftHandMotionAXth);
		leftHandMotionAYMatchResult = checkHigherThreshold(leftHandMotionAY, leftHandMotionAYth);
		leftHandMotionAZMatchResult = checkHigherThreshold(leftHandMotionAZ, leftHandMotionAZth);
		
		boolean rightHandPosturePatternMatchResult, rightHandOrientationMatchResult;
		rightHandPosturePatternMatchResult = isRightHandPosturePatternMatch(rightHandPosture);
		//	呼叫isRightHandPosturePatternMatch方法
		rightHandOrientationMatchResult = isRightHandOrientationPatternMatch(rightHandOri);	//	呼叫isRightHandOrientationPatternMatch方法
		boolean rightHandMotionFAMatchResult, rightHandMotionFBMatchResult, 
				rightHandMotionFCMatchResult, rightHandMotionFDMatchResult,
				rightHandMotionFEMatchResult;
		boolean rightHandMotionAXMatchResult, rightHandMotionAYMatchResult,
				rightHandMotionAZMatchResult;
		rightHandMotionFAMatchResult = checkHigherThreshold(rightHandMotionFA, rightHandMotionFAth);
		rightHandMotionFBMatchResult = checkHigherThreshold(rightHandMotionFB, rightHandMotionFBth);
		rightHandMotionFCMatchResult = checkHigherThreshold(rightHandMotionFC, rightHandMotionFCth);
		rightHandMotionFDMatchResult = checkHigherThreshold(rightHandMotionFD, rightHandMotionFDth);
		rightHandMotionFEMatchResult = checkHigherThreshold(rightHandMotionFE, rightHandMotionFEth);
		rightHandMotionAXMatchResult = checkHigherThreshold(rightHandMotionAX, rightHandMotionAXth);
		rightHandMotionAYMatchResult = checkHigherThreshold(rightHandMotionAY, rightHandMotionAYth);
		rightHandMotionAZMatchResult = checkHigherThreshold(rightHandMotionAZ, rightHandMotionAZth);
		
		return 	leftHandPosturePatternMatchResult && leftHandOrientationMatchResult &&
				leftHandMotionFAMatchResult && leftHandMotionFBMatchResult && 
				leftHandMotionFCMatchResult && leftHandMotionFDMatchResult &&
				leftHandMotionFEMatchResult &&
				leftHandMotionAXMatchResult && leftHandMotionAYMatchResult &&
				leftHandMotionAZMatchResult &&
				rightHandPosturePatternMatchResult && rightHandOrientationMatchResult &&
				rightHandMotionFAMatchResult && rightHandMotionFBMatchResult && 
				rightHandMotionFCMatchResult && rightHandMotionFDMatchResult &&
				rightHandMotionFEMatchResult &&
				rightHandMotionAXMatchResult && rightHandMotionAYMatchResult &&
				rightHandMotionAZMatchResult
				;
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
	
	private boolean checkHigherThreshold(int Data, int Threshold)							//	checkHigherThreshold方法
	{																						//	進入checkHigherThreshold方法
		if( Data > Threshold)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}																						//	結束checkHigherThreshold方法
	
}																							//	結束HandMotionPattern類別

