package com.mycompany.myapp;

public class Pi
{
	public static final int KIND_MANZ = 0;
	public static final int KIND_PINZ = 1;
	public static final int KIND_SOUZ = 2;
	public static final int KIND_JIHAI = 3;
	public static final int KIND_NUM = 4;
	public static final int NUMBER_MAX = 9;
	public static final int NUMBER_MAX_JIHAI = 7;
	public static final int DUPLICATE_CNT = 4;
	
	public int kind;
	public int number;
	
	String getKindString(){
		String str = "";
		switch(kind){
			case KIND_MANZ:
				str="M";
				break;
			case KIND_PINZ:
				str="P";
				break;
			case KIND_SOUZ:
				str="S";
				break;
			case KIND_JIHAI:
				str="J";
				break;
			default:
				str="INVALID_KIND";
			 break;
		}
		return str;
	}
	
	String getNameString(){
		String name="";
		if(kind!=KIND_JIHAI){
			name = number + "-" + getKindString();
		}else{
			switch(number){
				case 1:
					name = "TON";
					break;
				case 2:
					name = "NAN";
					break;
				case 3:
					name = "SHA";
					break;
				case 4:
					name = "PEI";
					break;
				case 5:
					name = "HAK";
					break;
				case 6:
					name = "HAT";
					break;
				case 7:
					name = "CHN";
					break;
				default:
					name = "INVALID ACCESS";
				break;
			}
		}
		return name;
	}
	String getNameString2(){
		String name="";
		if(kind!=KIND_JIHAI){
			name = number + getKindString();
		}else{
			switch(number){
				case 1:
					name = "TO";
					break;
				case 2:
					name = "NA";
					break;
				case 3:
					name = "SH";
					break;
				case 4:
					name = "PE";
					break;
				case 5:
					name = "  ";
					break;
				case 6:
					name = "HT";
					break;
				case 7:
					name = "CH";
					break;
				default:
					name = "INVALID ACCESS";
					break;
			}
		}
		return name;
	}
	
}
