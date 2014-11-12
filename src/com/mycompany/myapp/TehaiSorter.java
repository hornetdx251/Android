package com.mycompany.myapp;
import java.util.Comparator;

public class TehaiSorter implements Comparator<Pi>
{
	@Override
	public int compare(Pi p1, Pi p2)
	{
		if(p1.kind==p2.kind){
			if(p1.number>p2.number){
				return 1;
			}else{
				return -1;
			}
		}else{
			if(p1.kind>p2.kind){
				return 1;
			}else{
				return -1;
			}
		}
	}
}
