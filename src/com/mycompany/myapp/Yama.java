package com.mycompany.myapp;
import java.util.*;

public class Yama
{
	public List<Pi> yama = null;
	Yama(){
		yama = new ArrayList<Pi>();
	}
	Pi get(){
		Pi pi = yama.get(0);
		yama.remove(pi);
		return pi;
	}
	
	int set(){
		if(yama == null){ return -1; }
		
		for(int iKind=0;iKind<Pi.KIND_NUM;iKind++){
			int max = Pi.NUMBER_MAX;
			if(iKind==Pi.KIND_JIHAI) max = Pi.NUMBER_MAX_JIHAI;
			for(int iNum=1;iNum<=max;iNum++){
				for(int iDuplicate=0;iDuplicate<Pi.DUPLICATE_CNT;iDuplicate++){
					Pi pi = new Pi();
					pi.kind=iKind;
					pi.number=iNum;
					yama.add(pi);
				}
			}
		}
		Collections.shuffle(yama);
		Collections.shuffle(yama);
		return 0;
	}
	
}
