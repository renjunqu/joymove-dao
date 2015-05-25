package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.math.BigDecimal;

@Alias("JOYOrder")
public class JOYOrder {
	
	
	public Integer id;
	public String mobileNo;
	public Integer carId;
	public Date startTime;
	public Date stopTime;
	public Integer delMark;
	public Integer rentStatus;
	public Integer type;
	public Integer batonMode;
	public String  destination;
	public Integer    state;
	public String   carVinNum;
	
	
	public static int DEL_MARK=1;
	public static int NON_DEL_MARK = 0;
	
	public static int state_busy = 1;
	public static int state_wait_pay = 2;
	public static int state_pay_over = 3;
	
	public double getTotalFee() {
		Date start = this.startTime;
		Date stop = null;
		if(this.state==state_busy) {
			stop = new Date(System.currentTimeMillis());
		} else {
			stop = this.stopTime;
		}
		System.out.println("stop is "+stop.getTime());
		System.out.println("start is "+start.getTime());
		System.out.println("diff is "+(stop.getTime() - start.getTime()));

		double result =  stop.getTime() - start.getTime();
		result = result/(100000.0 * 60);
		return result;
	}




}
