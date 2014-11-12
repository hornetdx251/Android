package com.mycompany.myapp;
import java.util.*;
import android.util.*;

public class ShantenCounter
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
	
	int mentu;		//メンツ数
	int toitu;		//トイツ数
	int kouho;		//ターツ数
	int fuurosuu;   //フーロ数
	int temp;		//シャンテン数（計算用）
	int syanten_normal;	//シャンテン数（結果用）

	//メンツ抜き関数
	void mentu_cut(int i){
		
		// Skip if Zero.
		for(;tehai[i]!=0;i++){
			// Skip.
		}
		
		//メンツを抜き終わったのでターツ抜きへ
		if(i>=38){
			taatu_cut(1);
			return;
		}
		
		//コーツ
		if(tehai[i]>=3)
		{
			// Log.d("jong","mentsu(kotsu)");
			mentu++;
			tehai[i]-=3;
			mentu_cut(i);
			tehai[i]+=3;
			mentu--;
		}
		
		//シュンツ
		if(tehai[i+1]!=0 && tehai[i+2]!=0 && i<30)
		{
			// Log.d("jong","mentsu(shuntsu)");
			mentu++;
			tehai[i]--;tehai[i+1]--;tehai[i+2]--;
			mentu_cut(i);
			tehai[i]++;tehai[i+1]++;tehai[i+2]++;
			mentu--;
		}
		
		//メンツ無しと仮定
		mentu_cut(i+1);
	}
	
	//ターツ抜き関数
	void taatu_cut(int i){
		
		// Skip if Zero.
		for(;tehai[i]!=0;i++){
			// Skip.
		}
		
		//抜き出し終了
		if(i>=38) 
		{
			// Calcurrate Shantenn
			temp=8-mentu*2-kouho-toitu;
			
			// Log.d("jong","1 serch pattern is finished. (shanten=" + temp + ")------------------");
			
			// If calcurated shanten is minimum,
			//  store this shanten-su.
			if(temp<syanten_normal) { syanten_normal=temp; }
			return;
		}
		
		if(mentu+kouho<4)
		{            
			//トイツ
			if(tehai[i]==2)
			{
				// Log.d("jong","ta-tsu(toitsu)");
				kouho++;
				tehai[i]-=2;
				taatu_cut(i);
				tehai[i]+=2;
				kouho--;
			}

			//ペンチャンorリャンメン
			if(tehai[i+1]!=0 && i<30)
			{
				// Log.d("jong","ko-tsu(penchan or ryanmen)");
				kouho++;
				tehai[i]--;tehai[i+1]--;
				taatu_cut(i);
				tehai[i]++;tehai[i+1]++;
				kouho--;
			}

			//カンチャン
			if(tehai[i+2]!=0 && i<30 && i%10<=8)
			{
				// Log.d("jong","ko-tsu(kanchan)");
				kouho++;
				tehai[i]--;tehai[i+2]--;
				taatu_cut(i);
				tehai[i]++;tehai[i+2]++;
				kouho--;
			}
		}
		taatu_cut(i+1);
	}
	
	//コンストラクタ
	public ShantenCounter(){fuurosuu=0;};
	
	
	public int getSyanten(List<Pi> tehai_list){
		// clear state.
		clear();
		
		// set 0 temporary.
		set_fuurosuu(0);
		
		// set tehai.
		for(Pi pi : tehai_list){
			int histIndex = (pi.kind) * 10 + pi.number;
			tehai[histIndex]++;
		}
		
		// for debug
		/*
		for(int i=0;i<NUM_KIND_OF_PI;i++){
			Log.d("jong","index=" + i + " [" + tehai[i] + "]");
		}*/
		
		// return 0;
		// return normal shanten.
		return NormalSyanten();
	}
	
	//通常手シャンテン
	public int NormalSyanten(){
		
		//初期化
		mentu=0;
		toitu=0;
		kouho=0;
		temp=0;
		syanten_normal=8;
		
		for(int i=1;i<38;i++)
		{
			//頭抜き出し
			if(2 <= tehai[i])
			{            
				toitu++;
				tehai[i] -= 2;
				mentu_cut(1);
				tehai[i] += 2;
				toitu--;
			}
		}
		//フーロなしなら
		if(fuurosuu == 0)
			mentu_cut(1);   //頭無しと仮定して計算

		//死に孤立字牌(これバグるからだめ)
		// boolean ji=false;
		//for(int i=31;i<38;i++){if(tehai[i]==4)ji=true;}
		// return syanten_normal-fuurosuu*2+ji;	//最終的な結果
		return syanten_normal-fuurosuu*2;
	}
	
	//国士シャンテン
	public int KokusiSyanten(){
		return 0;
	}
	
	//チートイシャンテン
	public int TiitoituSyanten(){
		return 0;
	}

	//手牌セット（int[38]以上、赤統合済み）
	public void set_tehai(int t[]){
		for(int i=0;i<38;i++){
			tehai[i]=t[i];
		}
		tehai[38]=-1;
	}
	
	//フーロ数
	public void set_fuurosuu(int a){fuurosuu=a;}
	
	;//中身クリア
	public void clear(){
		for(int i=0;i<38;i++){
			tehai[i]=0;
		}
		tehai[38]=-1;
		fuurosuu=0;		
	}

}
