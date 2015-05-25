package com.joymove.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.json.simple.JSONObject;

@Alias("JOYCoupon")
public class JOYCoupon {
	
	public static int  DELMARK=1;
	public static int NON_DELMARK=0;
	
	public Integer couponId;
	public String mobileNo;
	public BigDecimal couponNum;
	public Date couponExpDate;
	public Date overdueTime;
	public Integer delMark;
	
	




	

	public JSONObject toJSON(){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("couponId",this.couponId);
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












	public Integer getDelMark() {
		return delMark;
	}












	public void setDelMark(Integer delMark) {
		this.delMark = delMark;
	}












	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((couponExpDate == null) ? 0 : couponExpDate.hashCode());
		result = prime * result
				+ ((couponId == null) ? 0 : couponId.hashCode());
		result = prime * result
				+ ((couponNum == null) ? 0 : couponNum.hashCode());
		result = prime * result + ((delMark == null) ? 0 : delMark.hashCode());
		result = prime * result
				+ ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result
				+ ((overdueTime == null) ? 0 : overdueTime.hashCode());
		return result;
	}












	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JOYCoupon other = (JOYCoupon) obj;
		if (couponExpDate == null) {
			if (other.couponExpDate != null)
				return false;
		} else if (!couponExpDate.equals(other.couponExpDate))
			return false;
		if (couponId == null) {
			if (other.couponId != null)
				return false;
		} else if (!couponId.equals(other.couponId))
			return false;
		if (couponNum == null) {
			if (other.couponNum != null)
				return false;
		} else if (!couponNum.equals(other.couponNum))
			return false;
		if (delMark == null) {
			if (other.delMark != null)
				return false;
		} else if (!delMark.equals(other.delMark))
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
			return false;
		if (overdueTime == null) {
			if (other.overdueTime != null)
				return false;
		} else if (!overdueTime.equals(other.overdueTime))
			return false;
		return true;
	}
	
	
}
