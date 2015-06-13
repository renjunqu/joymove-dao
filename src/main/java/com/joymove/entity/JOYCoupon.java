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
	public Integer orderId;//用在了哪个订单上





	public JSONObject toJSON(){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("couponId",this.id);
		jsonObj.put("couponBalance",this.couponNum);
		//SimpleDateFormat   dateFormatter   =   new   SimpleDateFormat   ("yyyy-MM-dd   HH:mm:ss     ");
		//String dateStr = dateFormatter.format(new   Date(System.currentTimeMillis()));
		jsonObj.put("couponExpDate",this.couponExpDate.getTime()/1000);
		Date overDueTime = this.overdueTime;
		if(overDueTime!=null) {
			jsonObj.put("overdueTime",this.overdueTime.getTime()/1000);
		} else {
			jsonObj.put("overdueTime",this.couponExpDate.getTime()/1000 + 24*3600*365);
		}
		return jsonObj;
	}















	
	
}
