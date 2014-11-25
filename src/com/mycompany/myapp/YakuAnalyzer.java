package com.mycompany.myapp;

public class YakuAnalyzer
{
	public static final int NUM_KIND_OF_PI=40;
	// 0  : undef
	// 1  : 1m
	// 9  : 9m
	// 10 : undef
	// 11 : 1p
	// 19 : 9p
	// 20 : undef
	// 21 : 1s
	// 29 : 9s
	// 30 : undef
	// 31 : ton
	// 32 : nan
	// 33 : sha
	// 37 : chn
	// 38 : -1 (EOF)
	// 39 : ?

	int tehai[] = new int[NUM_KIND_OF_PI];
	int fuuro[] = new int[20];   //フーロ
	/*
	 フーロ牌はint型の配列fuuro[20]に、n+1番目のフーロ牌について
	 [0+4*n]　フーロの種類（0＝フーロなし、1＝ポン、2＝チー、3＝アンカン、4＝ミンカン　5＝カカン）
	 [1+4*n]　そのフーロメンツのうち最も小さい牌番号
	 [2+4*n]　そのフーロメンツのうち鳴いた牌の牌番号
	 [3+4*n]　その人から相対的に見た、鳴いた他家の番号。1＝下家、2＝対面、3＝上家
	*/
	public void setTehai(int[] _tehai,int[] _fuuro, int _agarihai, boolean _ron){
		for(int i = 0; i < NUM_KIND_OF_PI; i++){
			tehai[i]=_tehai[i];
		}
		for(int i = 0; i < 20; i++){
			fuuro[i] = _fuuro[i];
		}
		agarihai = _agarihai;
		ron = _ron;
	}
	public static final int PON = 0;
	public static final int CHII = 1;
	public static final int ANKAN = 2;
	public static final int MINKAN = 3;
	public static final int ANKO = 4;
	public static final int SHUNTU = 5;
	public static final int TOITU = 6;
	
	//４メンツ１頭
	int kiriwake[] = new int[10]; //結果用グローバル変数
	/*
	 ※n+1番目のメンツについて
	 kiriwake[n]=そのメンツのうち最も小さい牌番号
	 kiriwake[n+1]=メンツの種類
	*/
	
	int p_kiriwake;  //kiriwake[10]のポインタ用変数
	int agarihai;//あがり牌の番号
	boolean ron;//ロンあがりならTRUEになるフラグ
	
	/***************************************************************************************/
	void MentuKiriwake()
	{
		int i;
		//ポインタ初期化
		p_kiriwake=0;
		//フーロ牌を統合
		//当HPの牌の表現方法、ルールについて参照
		for(i=0;i<=20;i+=4){
			if(fuuro[i]!=0){
				kiriwake[i]=fuuro[i];
				kiriwake[i+1]=fuuro[i+1];
				p_kiriwake+=2;
			}
			else{
				break;
			}
		}
		//頭抜き出し
		//あがった状態でしか呼び出さない点に注意
		for(i=0;i<=37;i++){
			for(;tehai[i]==0;i++);if(i>=38) continue;
			if(tehai[i] >=2){
				tehai[i] -=2;
				//データを格納しポインタを進める
				kiriwake[p_kiriwake]=TOITU;kiriwake[p_kiriwake+1] =i;p_kiriwake+=2;
				//メンツ抜き出し関数へ
				KiriwakeNukidasi();
				p_kiriwake-=2;kiriwake[p_kiriwake]=0;kiriwake[p_kiriwake+1]=0;
				tehai[i] += 2;
			}
		}
	}
	/***************************************************************************************/
	//メンツ抜き出し関数
	void KiriwakeNukidasi()
	{
		int i;
		for(i=0;i<38;i++){
			for(;tehai[i]==0;i++);
			if(i>=38){
				if(kiriwake[9]!=0){//４メンツ１頭とれてるなら
					/********************************/
					/* ここで切り分けが終わります　　    */
					/* ここから符計算等に入ります　　    */
					/* 構成が関係する役はメンツごと　    */
					/* にここで判定すること　　　　　    */
					/********************************/
					
				}
				return;
			}
			//アンコ抜き出し
			if(tehai[i] >=3){
				tehai[i] -=3;
				//ロンのあがり牌ならミンコウである
				if(i==agarihai&& ron==true){
					kiriwake[p_kiriwake]=PON;
				}
				else{
					kiriwake[p_kiriwake]=ANKO;
				}
				kiriwake[p_kiriwake+1] =i; p_kiriwake+=2;
				KiriwakeNukidasi();//再帰
				p_kiriwake-=2; kiriwake[p_kiriwake]=0; kiriwake[p_kiriwake+1]=0;
				tehai[i] += 3;
			}
			//シュンツ抜き出し
			if(tehai[i] != 0 && tehai[i+1] != 0 && tehai[i+2] != 0 && i < 30){
				tehai[i]--; tehai[i+1]--; tehai[i+2]--;
				kiriwake[p_kiriwake]=SHUNTU; kiriwake[p_kiriwake+1] =i; p_kiriwake+=2;
				KiriwakeNukidasi();//再帰
				p_kiriwake-=2; kiriwake[p_kiriwake]=0; kiriwake[p_kiriwake+1]=0;
				tehai[i]++; tehai[i+1]++; tehai[i+2]++;
			}	            
		}
	}
}
