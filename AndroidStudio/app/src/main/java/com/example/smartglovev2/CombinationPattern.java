package com.example.smartglovev2;

/**
 * Created by jimmy on 2017/7/23.
 */

import java.util.regex.Pattern;																//	引入java.util.regex.Pattern函式庫

public class CombinationPattern 															//	CombinationPattern類別
{																							//	進入CombinationPattern類別
	public String ChineseWord;																//	宣告ChineseWord(中文詞彙)
	public String EnglishWord;																//	宣告EnglishWord(英文詞彙)
	
	private Pattern LeadingWord1;
	private Pattern LeadingWord2;
	public int mp3ID;																		//	宣告mp3ID
	public CombinationPattern(	String ChineseWord, String EnglishWord,
								String LeadingWord1, String LeadingWord2,
								int mp3ID)
	{
		this.ChineseWord = ChineseWord;														//	設定ChineseWord初始化
		this.EnglishWord = EnglishWord;														//	設定EnglishWord初始化
		this.LeadingWord1 = Pattern.compile(LeadingWord1);
		this.LeadingWord2 = Pattern.compile(LeadingWord2);
		this.mp3ID = mp3ID;
	}
	
	public boolean isMatch(	String InputLeadingWord1, String InputLeadingWord2)
	{
		boolean LeadingWord1PatternMatchResult, LeadingWord2PatternMatchResult;
		LeadingWord1PatternMatchResult = this.LeadingWord1.matcher(InputLeadingWord1).matches();
		LeadingWord2PatternMatchResult = this.LeadingWord2.matcher(InputLeadingWord2).matches();
		return LeadingWord1PatternMatchResult && LeadingWord2PatternMatchResult;
	}
}																							//	結束CombinationPattern類別
