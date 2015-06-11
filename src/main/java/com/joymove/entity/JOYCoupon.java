package com.joymove.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.json.simple.JSONObject;

@Alias("JOYCoupon")
public class JOYCoupon  extends  JOYBase {

	public static String tableName = "JOY_Coupon";


	public JOYCoupon() {
	}

	public static int  DELMARK=1;
	public static int NON_DELMARK=0;
	
	public Integer id;
	public String mobileNo;
	public BigDecimal couponNum;
	public Date couponExpDate;
	public Date overdueTime;
	public Integer delMark;
	
	
















	public Integer getDelMark() {
		return delMark;
	}












	public void setDelMark(Integer delMark) {
		this.delMark = delMark;
	}














	
	
}
