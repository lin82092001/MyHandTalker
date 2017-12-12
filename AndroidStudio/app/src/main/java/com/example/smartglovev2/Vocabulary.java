package com.example.smartglovev2;

import java.util.ArrayList;

/**
 * Created by 林北94狂 on 2017/12/6.
 */

public class Vocabulary {

    public ArrayList<HandPattern> handPatterns = new ArrayList<HandPattern>();
    public ArrayList<HandMotionPattern> handMotionPatterns = new ArrayList<HandMotionPattern>();
    public ArrayList<CombinationPattern> combinationPatterns = new ArrayList<CombinationPattern>();
    public VoiceData VoiceData1= new VoiceData();

    public Vocabulary(String state)
    {
        if(state.equals("Ch"))
        {
            VoiceData1.hello=R.raw.hello;
            VoiceData1.i=R.raw.i;
            VoiceData1.you=R.raw.you;
            VoiceData1.thanks=R.raw.thanks;
            VoiceData1.welcome=R.raw.welcome;
            VoiceData1.come=R.raw.come;
            VoiceData1.love=R.raw.love;
            VoiceData1.lonely=R.raw.lonely;
            VoiceData1.help=R.raw.help;
            VoiceData1.admin=R.raw.admin;
            VoiceData1.protect=R.raw.protect;
            VoiceData1.taipei=R.raw.taipei;
            VoiceData1.taiwan=R.raw.taiwan;
            VoiceData1.technology=R.raw.technology;
            VoiceData1.university=R.raw.university;
            VoiceData1.need=R.raw.need;
            VoiceData1.coffee=R.raw.coffee;
            VoiceData1.sandwich=R.raw.sandwich;
            VoiceData1.study=R.raw.study;
        }
        else if(state.equals("En"))
        {
            VoiceData1.hello=R.raw.helloen;
            VoiceData1.i=R.raw.ien;
            VoiceData1.you=R.raw.youen;
            VoiceData1.thanks=R.raw.thanksen;
            VoiceData1.welcome=R.raw.welcomeen;
            VoiceData1.come=R.raw.comeen;
            VoiceData1.love=R.raw.loveen;
            VoiceData1.lonely=R.raw.lonelyen;
            VoiceData1.help=R.raw.helpen;
            VoiceData1.admin=R.raw.adminen;
            VoiceData1.protect=R.raw.protecten;
            VoiceData1.taipei=R.raw.taipeien;
            VoiceData1.taiwan=R.raw.taiwanen;
            VoiceData1.technology=R.raw.technologyen;
            VoiceData1.university=R.raw.universityen;
            VoiceData1.need=R.raw.needen;
            VoiceData1.coffee=R.raw.coffeeen;
            VoiceData1.sandwich=R.raw.sandwichen;
            VoiceData1.study=R.raw.studyen;
        }
    }

    // 靜態詞彙庫
    // 51種基本手型
    public void StaticVocabulary(){
        handPatterns.add(new HandPattern("零",		"Zero",			BasicPosture.ZERO,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.zero));
        handPatterns.add(new HandPattern("一",		"One",			BasicPosture.ONE,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.one));
        handPatterns.add(new HandPattern("二",		"Two",			BasicPosture.TWO,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.two));
        handPatterns.add(new HandPattern("三",		"Three",		BasicPosture.THREE,	 "outward",		BasicPosture.NUMBER,		"backward",	R.raw.three));
        handPatterns.add(new HandPattern("四",		"Four",			BasicPosture.FOUR,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.four));
        handPatterns.add(new HandPattern("五",		"Five",			BasicPosture.MALE,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.five));
        handPatterns.add(new HandPattern("六",		"Six",			BasicPosture.SIX,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.six));
        handPatterns.add(new HandPattern("七",		"Seven",		BasicPosture.SEVEN,	 "outward",		BasicPosture.NUMBER,		"backward",	R.raw.seven));
        handPatterns.add(new HandPattern("八",		"Eight",		BasicPosture.EIGHT,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.eight));
        handPatterns.add(new HandPattern("九",		"Nine",			BasicPosture.NINE,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.nine));
        handPatterns.add(new HandPattern("十",		"Ten",			BasicPosture.TEN,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.ten));
        handPatterns.add(new HandPattern("二十",		"Twenty",	BasicPosture.TWENTY,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.twenty));
        handPatterns.add(new HandPattern("三十",		"Thirty",	BasicPosture.THIRTY,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.thirty));
        handPatterns.add(new HandPattern("四十",		"Forty",	BasicPosture.FORTY,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.fortyy));
        handPatterns.add(new HandPattern("五十",		"Fifty",	BasicPosture.SUBORDINATE,	 "outward",	BasicPosture.NUMBER,		"backward",	R.raw.fifty));
        handPatterns.add(new HandPattern("六十",		"Sixty",	BasicPosture.LU,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.sixty));
        handPatterns.add(new HandPattern("七十",		"Seventy",	BasicPosture.TIGER,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.seventy));
        handPatterns.add(new HandPattern("九十",		"ninety",	BasicPosture.ZERO,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.ninety));
        handPatterns.add(new HandPattern("八十",	"Eighty",		BasicPosture.EIGHTY,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.eightyy));
        handPatterns.add(new HandPattern("一百",	"Hundred",		BasicPosture.HUNDRED,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.hundred));
        handPatterns.add(new HandPattern("一千",	"Thousand",		BasicPosture.THOUSAND,	 "forward",	BasicPosture.NUMBER,		"backward",	R.raw.thousand));
        handPatterns.add(new HandPattern("一萬",	"Ten Thousand",	BasicPosture.RECTANGLE,	 "forward",	BasicPosture.NUMBER,		"backward",	R.raw.tenthousandd));
        handPatterns.add(new HandPattern("女",		"girl",			BasicPosture.FEMALE,	 "forward",		BasicPosture.NUMBER,		"backward",R.raw.girl1));
        handPatterns.add(new HandPattern("手",		"hand",			BasicPosture.HAND,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.hand));
        handPatterns.add(new HandPattern("方",		"rectangle",	BasicPosture.RECTANGLE,	 "forward",	BasicPosture.NUMBER2,		"backward",	R.raw.rectangle));
        handPatterns.add(new HandPattern("句",		"sentence",		BasicPosture.SENTENCE,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.sentence));
        handPatterns.add(new HandPattern("兄",		"brother",		BasicPosture.BROTHER,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.brother));
        handPatterns.add(new HandPattern("民",		"people",		BasicPosture.PEOPLE,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.people));
        handPatterns.add(new HandPattern("同",		"together",		BasicPosture.TOGETHER,	 "forward",		BasicPosture.NUMBER,		"backward",R.raw.together));
        handPatterns.add(new HandPattern("守",		"keep",			BasicPosture.KEEP,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.keep));
        handPatterns.add(new HandPattern("男",		"male",			BasicPosture.MALE,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.male));
        handPatterns.add(new HandPattern("呂",		"Lu",			BasicPosture.LU,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.lululu));
        handPatterns.add(new HandPattern("姊",		"sister",		BasicPosture.SISTER,	 "forward",		BasicPosture.NUMBER,		"backward",R.raw.sister));
        handPatterns.add(new HandPattern("虎",		"tiger",		BasicPosture.TIGER,	 "forward",			BasicPosture.NUMBER2,		"backward",	R.raw.tiger));
        handPatterns.add(new HandPattern("果",		"fruit",		BasicPosture.FRUIT,	 "forward",			BasicPosture.NUMBER2,		"backward",		R.raw.fruit));
        handPatterns.add(new HandPattern("胡",		"nonsense",		BasicPosture.NONSENSE,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.nonsense));
        handPatterns.add(new HandPattern("很",		"very",			BasicPosture.Very,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.very));
        handPatterns.add(new HandPattern("飛機",	"airplane",		BasicPosture.AIRPLANE,	 "downward",	BasicPosture.NUMBER,		"backward",		R.raw.airplane));
        handPatterns.add(new HandPattern("隻",		"chih",			BasicPosture.CHIH,	 "outward",			BasicPosture.NUMBER2,		"backward",		R.raw.chih));
        handPatterns.add(new HandPattern("拳",		"fist",			BasicPosture.FIST,	 "forward",			BasicPosture.NUMBER,		"backward",R.raw.fist));
        handPatterns.add(new HandPattern("借",		"borrow",		BasicPosture.BORROW,	 "forward",		BasicPosture.NUMBER,		"backward",R.raw.borrow));
        handPatterns.add(new HandPattern("紳",		"gentle",		BasicPosture.GENTLE,	 "forward",		BasicPosture.NUMBER2,		"backward",R.raw.gentle));
        handPatterns.add(new HandPattern("副",		"subordinate",	BasicPosture.SUBORDINATE,	"outward",	BasicPosture.NUMBER2,		"backward",R.raw.subordinate));
        handPatterns.add(new HandPattern("棕",		"brown",		BasicPosture.BROWN,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.brown));
        handPatterns.add(new HandPattern("童",		"children",		BasicPosture.CHILDREN,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.boyscouts));
        handPatterns.add(new HandPattern("菜",		"vegetable",	BasicPosture.VEGETABLE,"forward",		BasicPosture.NUMBER,		"backward",	R.raw.vegetable));
        handPatterns.add(new HandPattern("筆",		"pen",			BasicPosture.PEN,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.pen));
        handPatterns.add(new HandPattern("像",		"similar",		BasicPosture.SIMILAR,"upward",			BasicPosture.NUMBER,		"backward",	R.raw.similar));
        handPatterns.add(new HandPattern("鴨",		"duck",			BasicPosture.DUCK,"forward",			BasicPosture.NUMBER,		"backward",	R.raw.duck));
        handPatterns.add(new HandPattern("錢",		"money",		BasicPosture.MONEY,	 "upward",		BasicPosture.NUMBER,		"backward",		R.raw.money1));
        handPatterns.add(new HandPattern("龍",		"dragon",		BasicPosture.DRAGON,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.dragon));
        handPatterns.add(new HandPattern("蟲",		"worm",			BasicPosture.WORM,	 "forward",		BasicPosture.NUMBER2,		"backward",	R.raw.bug));
        handPatterns.add(new HandPattern("臂",		"arm",			BasicPosture.ARM,	 "outward",			BasicPosture.NUMBER,		"backward",	R.raw.arm));
        handPatterns.add(new HandPattern("難",		"difficult",	BasicPosture.DIFFICULT,	 "forward",		BasicPosture.NUMBER,		"backward",	R.raw.difficult));
        handPatterns.add(new HandPattern("廁所",	"WC",			BasicPosture.WC,	 "forward",			BasicPosture.NUMBER,		"backward",	R.raw.wc));
        //手能生橋 1~3章 靜態詞彙區
        handPatterns.add(new HandPattern("我",		"I",			BasicPosture.ONE,	 "downward",		BasicPosture.HAND,		"backward",	VoiceData1.i));
        handPatterns.add(new HandPattern("你",		"You",			BasicPosture.ONE,	 "outward",		BasicPosture.HAND,		"backward",	VoiceData1.you));
        handPatterns.add(new HandPattern("他",		"He",			BasicPosture.ONE,	 "outward",		BasicPosture.MALE,		"inward",	R.raw.he));
        handPatterns.add(new HandPattern("她",		"She",			BasicPosture.ONE,	 "outward",		BasicPosture.FEMALE,	"forward",	R.raw.she));
        //handPatterns.add(new HandPattern("誰",		"Who",			BasicPosture.MALE,	 "outward",		BasicPosture.HAND,		"backward",	R.raw.who));
        handPatterns.add(new HandPattern("名字",	"Name",			BasicPosture.MALE,	 "outward",		BasicPosture.HAND,		"forward",	R.raw.name));
        handPatterns.add(new HandPattern("第一個",	"first",		BasicPosture.LU,	 "outward",		BasicPosture.ONE,		"inward",	R.raw.first));
        handPatterns.add(new HandPattern("第二個",	"second",		BasicPosture.LU,	 "outward",		BasicPosture.TWO,		"inward",	R.raw.second));
        handPatterns.add(new HandPattern("銀行",	"bank",			BasicPosture.MONEY,	 "forward",		BasicPosture.MONEY,	"forward",	R.raw.bank));
        handPatterns.add(new HandPattern("小",		"little",		BasicPosture.ONE,	 "outward",		BasicPosture.TWO,	"inward",	R.raw.little));
        handPatterns.add(new HandPattern("個",		"g",			BasicPosture.ONE,	 "forward",		BasicPosture.SIX,	"backward",	R.raw.g));
        handPatterns.add(new HandPattern("王",		"wang",			BasicPosture.ONE,	 "forward",		BasicPosture.THREE,	"inward",	R.raw.wang));
        handPatterns.add(new HandPattern("吳",		"wu",			BasicPosture.TOGETHER,	 "outward",		BasicPosture.HAND,	"backward",	R.raw.wu));
        handPatterns.add(new HandPattern("中午",	"noon",			BasicPosture.BROWN,"forward",		BasicPosture.HAND,	"backward",	R.raw.noon));
        handPatterns.add(new HandPattern("下午",	"afternoon",			BasicPosture.BROWN,"downward",	BasicPosture.HAND,	"backward",	R.raw.afternoon));
        handPatterns.add(new HandPattern("錶",		"watch",			BasicPosture.FIST,	 "downward",		BasicPosture.MONEY,	"downward",	R.raw.watch));
        //handPatterns.add(new HandPattern("房子",	"house",			BasicPosture.HAND,	 "forward",		BasicPosture.HAND,	"forward",	R.raw.house));
        handPatterns.add(new HandPattern("門",		"door",			BasicPosture.NONSENSE,	 "forward",		BasicPosture.NONSENSE,	"forward",	R.raw.door));
        //handPatterns.add(new HandPattern("人",		"people",			BasicPosture.ONE,	 "forward",		BasicPosture.ONE,	"forward",	R.raw.people));
        //重複你 handPatterns.add(new HandPattern("日",		"day",			BasicPosture.ONE,	 "outward",		BasicPosture.FEMALE,	"forward",	R.raw.she));
        //拍打handPatterns.add(new HandPattern("左",		"She",			BasicPosture.ONE,	 "outward",		BasicPosture.FEMALE,	"forward",	R.raw.she));
        //拍打handPatterns.add(new HandPattern("右",		"She",			BasicPosture.ONE,	 "outward",		BasicPosture.FEMALE,	"forward",	R.raw.she));
        handPatterns.add(new HandPattern("站",		"stand",			BasicPosture.HAND,	 "upward",		BasicPosture.TWO,	"backward",	R.raw.stand));
        handPatterns.add(new HandPattern("坐",		"sit",			BasicPosture.TWENTY,	 "downward",	BasicPosture.BROWN,	"downward",	R.raw.sit));
        handPatterns.add(new HandPattern("念書",	"study",			BasicPosture.HAND,	 "forward",		BasicPosture.HAND,	"forward",	VoiceData1.study));
        handPatterns.add(new HandPattern("工",		"v_work",			BasicPosture.TEN,	 "downward",		BasicPosture.TWO,	"inward",	R.raw.v_work));
        //表情handPatterns.add(new HandPattern("睡覺",	"sleep",			BasicPosture.ONE,	 "outward",		BasicPosture.FEMALE,	"forward",	R.raw.she));
        handPatterns.add(new HandPattern("聽",		"hear",			BasicPosture.HAND,	 "backward",		BasicPosture.RECTANGLE,	"forward",	R.raw.hear));
        handPatterns.add(new HandPattern("星期一",	"Monday",	BasicPosture.SEVEN	,"outward",	BasicPosture.ONE,	 "inward",		R.raw.monday));
        handPatterns.add(new HandPattern("星期二",	"Tuesday",	BasicPosture.SEVEN	,"outward",	BasicPosture.TWO,	 "inward",		R.raw.tuesday));
        handPatterns.add(new HandPattern("星期三",	"Wednesday",	BasicPosture.SEVEN	,"outward",	BasicPosture.THREE,	 "inward",		R.raw.wednesday));
        handPatterns.add(new HandPattern("星期四",	"Thursday",	BasicPosture.SEVEN	,"outward",	BasicPosture.FOUR,	 "inward",		R.raw.thursday));
        handPatterns.add(new HandPattern("星期五",	"Friday",	BasicPosture.SEVEN	,"outward",	BasicPosture.MALE,	 "forward",		R.raw.friday));
        handPatterns.add(new HandPattern("星期六",	"Saturday",	BasicPosture.SEVEN	,"outward",	BasicPosture.SIX,	 "inward",		R.raw.saturday));
        handPatterns.add(new HandPattern("星期日",	"Sunday",	BasicPosture.SEVEN	,"outward",	BasicPosture.SEVEN,	 "inward",		R.raw.sunday));




        handPatterns.add(new HandPattern("是",		"Yes",			BasicPosture.FOUR,	 "forward",		BasicPosture.HAND,		"backward",	R.raw.is));
        handPatterns.add(new HandPattern("第一個",	"First",		BasicPosture.LU,	 "outward",		BasicPosture.THREE,		"inward",	R.raw.first));
        handPatterns.add(new HandPattern("男前",	"mother_back",	BasicPosture.MALE		,"forward",	BasicPosture.HAND,	 "backward",		R.raw.nulll));
        //handPatterns.add(new HandPattern("父前",	"father_lead",	BasicPosture.SIX,		"outward",	BasicPosture.HAND,	 "backward",	R.raw.nulll));
        //handPatterns.add(new HandPattern("母前",	"mother_lead",	BasicPosture.MOTHER_LEAD,"outward",	BasicPosture.HAND,	 "backward",		R.raw.nulll));
        //handPatterns.add(new HandPattern("母後",	"mother_back",	BasicPosture.FEMALE		,"forward",	BasicPosture.HAND,	 "backward",		R.raw.nulll));
        handPatterns.add(new HandPattern("你後",	"mother_back",	BasicPosture.FIST		,"forward",	BasicPosture.HAND,	 "backward",		R.raw.nulll));
        handPatterns.add(new HandPattern("系統",	"System",	BasicPosture.THREE		,"forward",	BasicPosture.ONE,	 "downward",		R.raw.system));
        handPatterns.add(new HandPattern("多少1",		"price1",		BasicPosture.HAND,	 "backward",		BasicPosture.FOUR,		"upward",		R.raw.nulll));
        handPatterns.add(new HandPattern("多少2",		"price2",		BasicPosture.HAND,	 "backward",		BasicPosture.FIST,		"upward",		R.raw.nulll));
        //handPatterns.add(new HandPattern("生",	"Born",	BasicPosture.MALE		,"outward",	BasicPosture.MALE,	 "inward",		R.raw.born));
        //handPatterns.add(new HandPattern("兒子女兒1",	"Born",	BasicPosture.MALE		,"outward",	BasicPosture.MALE,	 "inward",		R.raw.nulll));


        handPatterns.add(new HandPattern("需1",	"need1", BasicPosture.HAND,	 "backward",		BasicPosture.FOUR	,"downward",	R.raw.nulll));
        handPatterns.add(new HandPattern("要2",	"need2",		BasicPosture.HAND	,	"outward",	BasicPosture.HAND,	 "inward",		R.raw.nulll));
        handPatterns.add(new HandPattern("科",	"Tech",		BasicPosture.SEVEN,	 "forward", BasicPosture.FIST	,"downward",		R.raw.nulll));
        handPatterns.add(new HandPattern("技",	"nology",	BasicPosture.SIX,		 "outward",	BasicPosture.FIST	,"downward",		R.raw.nulll));
        handPatterns.add(new HandPattern("科_",	"Tech",	BasicPosture.FIST	,"downward",	BasicPosture.SEVEN,	 "forward",		R.raw.nulll));
        handPatterns.add(new HandPattern("技_",	"nology",	BasicPosture.FIST	,"downward",	BasicPosture.SIX,	 "inward",		R.raw.nulll));
        handPatterns.add(new HandPattern("棕_",	"brown",	BasicPosture.FIST,	 "outward",		BasicPosture.TWO	,"forward",		R.raw.nulll));
    }

    public void MotionVocabulary(){
        handMotionPatterns.add(new HandMotionPattern(	"男生",		"Boy",
                BasicPosture.MALE,	"outward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 200,
                BasicPosture.HAND,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.boy));
        handMotionPatterns.add(new HandMotionPattern(	"女生",	"girl",
                BasicPosture.FEMALE,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                1000, -10000, -10000,
                BasicPosture.HAND,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.girl));
			/*handMotionPatterns.add(new HandMotionPattern(	"兒子2",		"Boy",
					BasicPosture.HAND,	"backward",
					-10000, -10000, -10000, -10000, -10000,
					-10000, -10000, -10000,
					BasicPosture.MALE,	"inward",
					-10000, -10000, -10000, -10000, -10000,
					-10000, -10000, 20,
					R.raw.nulll));
			handMotionPatterns.add(new HandMotionPattern(	"女兒2",	"girl",
					BasicPosture.HAND,	"backward",
					-10000, -10000, -10000, -10000, -10000,
					-10000, -10000, -10000,
					BasicPosture.FEMALE,	"forward",
					-10000, -10000, -10000, -10000, -10000,
					1000, -10000, -10000,
					R.raw.nulll));*/
        handMotionPatterns.add(new HandMotionPattern(	"謝謝",		"thanks",
                BasicPosture.FIST,	"outward",
                0, -10000, -10000, -10000, -10000,
                -10000, -10000,-10000,
                BasicPosture.FIST,	"inward",
                0, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.thanks));
        handMotionPatterns.add(new HandMotionPattern(	"攪拌",		"Stir",
                BasicPosture.TWO,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 80,
                BasicPosture.FIST,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.stir));
        handMotionPatterns.add(new HandMotionPattern(	"攪拌_",		"Stir",
                BasicPosture.FIST,	"outward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                BasicPosture.TWO,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 20,
                R.raw.nulll));
        handMotionPatterns.add(new HandMotionPattern(	" 台北",		"Taipei",
                BasicPosture.SIX,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 2,
                BasicPosture.SIX,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 2,
                VoiceData1.taipei));
        handMotionPatterns.add(new HandMotionPattern(	"大學",		"University",
                BasicPosture.ONE,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 2,
                BasicPosture.ONE,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 2,
                VoiceData1.university));

        handMotionPatterns.add(new HandMotionPattern(	" 新北",		"New Taipei City",
                BasicPosture.SEVEN,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000,80,
                BasicPosture.SEVEN,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 80,
                R.raw.newtaipeicity));
			/*handMotionPatterns.add(new HandMotionPattern(	"手語",	"Sign Language",
					BasicPosture.HAND,	"outward",
					-10000, -10000, -10000, -10000, -10000,
					1000, -10000, -10000,
					BasicPosture.HAND,	"inward",
					-10000, -10000, -10000, -10000, -10000,
					1000, -10000, -10000,
					R.raw.signlanguage));*/
        handMotionPatterns.add(new HandMotionPattern(	"保護",		"Protect",
                BasicPosture.HAND,	"outward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 15,
                BasicPosture.MALE,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.protect));
        handMotionPatterns.add(new HandMotionPattern(	"錄取",		"Admin",
                BasicPosture.HAND,	"upward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 2,
                BasicPosture.MALE,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.admin));
        handMotionPatterns.add(new HandMotionPattern(	"蟲",		"bug",
                BasicPosture.WORM,	"forward",
                -10000, -10000, -10000, -10000, 5,
                -10000, -10000, -10000,
                BasicPosture.NUMBER,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.bug));
        handMotionPatterns.add(new HandMotionPattern(	"幫忙",		"Help",
                BasicPosture.HAND,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 2,
                BasicPosture.MALE,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.help));
        handMotionPatterns.add(new HandMotionPattern(	"愛",		"Love",
                BasicPosture.HAND,	"downward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 20,
                BasicPosture.MALE,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.love));
        handMotionPatterns.add(new HandMotionPattern(	"孤單",		"Lonely",
                BasicPosture.HAND,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 40,
                BasicPosture.MALE,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.lonely));
        handMotionPatterns.add(new HandMotionPattern(	"來",		"come",
                BasicPosture.ONE,	"forward",
                -10000,-10000,-10000,-10000,-10000,
                -10000,-10000,6,
                BasicPosture.HAND, "backward" ,
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.come));
        handMotionPatterns.add(new HandMotionPattern(	"高興",		"happy",
                BasicPosture.HAND,	"outward",
                -10000,-10000,-10000,-10000,-10000,
                -10000,-10000,80,
                BasicPosture.HAND, "inward" ,
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, 80,
                R.raw.nulll));
        handMotionPatterns.add(new HandMotionPattern(	"迎接",		"come2",
                BasicPosture.HAND,	"upward",
                -10000,-10000,-10000,-10000,-10000,
                -10000,-100,-10000,
                BasicPosture.HAND, "upward" ,
                -10000, -10000, -10000, -10000, -10000,
                -10000, -100, -10000,
                R.raw.nulll));
        handMotionPatterns.add(new HandMotionPattern(	"台灣",		"taiwan",
                BasicPosture.HAND,	"backward",
                -10000,-10000,-10000,-10000,-10000,
                -10000,-1000,-10000,
                BasicPosture.FIST, "downward" ,
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -1,
                VoiceData1.taiwan));
        handMotionPatterns.add(new HandMotionPattern(	"領袖",		"leader",
                BasicPosture.HAND,	"backward",
                -10000,-10000,-10000,-10000,-10000,
                -10000,-1000,-10000,
                BasicPosture.MALE, "inward" ,
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -1,
                R.raw.nulll));
		/*	handMotionPatterns.add(new HandMotionPattern(	"們",		"People",
															BasicPosture.PEOPLE,	"forward",
															-10000, -10000, -10000, -10000, -10000,
															-10000, -10000, 600,
															BasicPosture.PEOPLE,	"forward",
															-10000, -10000, -10000, -10000, -10000,
															-10000, -10000, 600,														R.raw.people));*/
        handMotionPatterns.add(new HandMotionPattern(	"我們兩個",	"ours",
                BasicPosture.TWO,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                1000, -10000, -10000,
                BasicPosture.HAND,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.ours));
        handMotionPatterns.add(new HandMotionPattern(	"你們兩個",	"yours",
                BasicPosture.TWO,	"downward",
                -10000, -10000, -10000, -10000, -10000,
                1000, -10000,-10000,
                BasicPosture.HAND,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.yours));

        handMotionPatterns.add(new HandMotionPattern(	"結婚",	"Marry",
                BasicPosture.FEMALE,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                1000, -10000, -10000,
                BasicPosture.MALE,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.marry));
        handMotionPatterns.add(new HandMotionPattern(	"離婚",	"Divorce",
                BasicPosture.FEMALE,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                BasicPosture.MALE,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                1000, -10000, -10000,
                R.raw.disvorce));
        handMotionPatterns.add(new HandMotionPattern(	"朋友",	"Friend",
                BasicPosture.ZERO,	"downward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                BasicPosture.ZERO,	"upward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                R.raw.friend));
			/*handMotionPatterns.add(new HandMotionPattern(	"這裡",	"this",
															BasicPosture.ONE,	"backward",
															-10000, -10000, -10000, -10000, -10000,
															-10000, -10000, 1000,
															BasicPosture.HAND,	"backward",
															-10000, -10000, -10000, -10000, -10000,
															-10000, -10000, -10000,
															R.raw.thiss));
			handMotionPatterns.add(new HandMotionPattern(	"那裡",	"that",
															BasicPosture.ONE,	"downward",
															-10000, -10000, -10000, -10000, -10000,
															1000, -10000,-10000,
															BasicPosture.HAND,	"backward",
															-10000, -10000, -10000, -10000, -10000,
															-10000, -10000, -10000,
															R.raw.that));*/
        handMotionPatterns.add(new HandMotionPattern(	"三明治",	"sandwich",
                BasicPosture.THREE,	"forward",
                -10000, -10000, -10000, -10000, -10000,
                100, -10000,-10000,
                BasicPosture.HAND,	"inward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000, -10000,
                VoiceData1.sandwich));
        handMotionPatterns.add(new HandMotionPattern(	"價錢",	"price",
                BasicPosture.MONEY,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                1000, -10000, -10000,
                BasicPosture.HAND,	"backward",
                -10000, -10000, -10000, -10000, -10000,
                -10000, -10000,-10000,
                R.raw.price));
    }

    public void CombinationVocabulary() {
        //combinationPatterns.add(new	CombinationPattern("爸爸",	"Father",	"父前",	"誰", R.raw.father));
        //combinationPatterns.add(new	CombinationPattern("媽媽",	"Mother",	"母前",	"母後", R.raw.mother));
        combinationPatterns.add(new CombinationPattern("你好",	"Hello",	"你",	"你後", VoiceData1.hello));
        //combinationPatterns.add(new	CombinationPattern("翻譯",	"Translation",	"誰",	"男前", R.raw.translation));
        //combinationPatterns.add(new	CombinationPattern("兒子",	"Son",	"兒子女兒1",	"兒子2", R.raw.son));
        //combinationPatterns.add(new	CombinationPattern("女兒",	"Daughter",	"兒子女兒1",	"女兒2", R.raw.daughter));
        combinationPatterns.add(new CombinationPattern("需要",	"Need",	"需1",	"要2", VoiceData1.need));
        combinationPatterns.add(new CombinationPattern("科技",	"Technology",	"科",	"技", VoiceData1.technology));
        combinationPatterns.add(new CombinationPattern("咖啡",	"coffee",	"棕_",	"攪拌_", VoiceData1.coffee));
        //combinationPatterns.add(new	CombinationPattern("多少",	"how much",	"多少1",	"多少2", R.raw.howmuch));
        combinationPatterns.add(new CombinationPattern("歡迎",	"welcome",	"高興",	"迎接", VoiceData1.welcome));
        //combinationPatterns.add(new	CombinationPattern("總統",	"president",	"國家",	"領袖", R.raw.president));
    }

}
