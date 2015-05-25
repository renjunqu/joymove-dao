package com.joymove.util;

public class MBKOrderTime {

	public static String getTime(long time) {
		  String str = "" ;
		  time = time / 1000;
		  int s = (int) (time % 60);
		  int m = (int) (time / 60 % 60);
		  int h = (int) (time / 3600);
		  str = h+"";
		  return str ;
		 }  
	
	public static Integer getMoney(String time,int tt1){
	 	int ss = 0;
	 
	  if(time != null && !time.equals("") && tt1 > 0){
		  int parseInt = Integer.parseInt(time);
		  if (parseInt > 1 && parseInt < 2) {
			  ss = tt1 -1;
		  }else if(parseInt >= 2 && parseInt <=5){
			  ss = tt1 -5;
		  }else if(parseInt > 5){
			  ss = tt1 -15;
		  }else {
			  ss = tt1 -1;
		  }
	  }
	  return ss;
 }
}
