package com.mycompany.myapp;
import com.mycompany.myapp.Pi;
import java.util.*;

public class Tehai
{
	List<Pi> tehai = new ArrayList<Pi>();
	public Pi tsumo = new Pi();
	public int status;
	public static final int STAT_AGARI = 2;
	public static final int STAT_TENPAI = 1;
	public static final int STAT_NONE = 0;
	public static final int TEHAI_MAX_CNT = 13;
	void add(Pi pi){
		tehai.add(pi);
	}
	void set(int index,Pi pi){
		tehai.set(index,pi);
	}
	boolean isTenpai(){
		return false;
	}
	public int getShanten(){
		ShantenCounter counter = new ShantenCounter();
		return counter.getSyanten(tehai);
	}
	public int getShanten(Pi tsumo){
		ShantenCounter counter = new ShantenCounter();
		List<Pi> temp = tehai;
		temp.add(tsumo);
		return counter.getSyanten(temp);
	}
}
